package cn.pasteme.backend.service.impl;

import cn.pasteme.backend.service.PasteService;
import cn.pasteme.backend.util.factory.manager.ManagerCreateFactory;
import cn.pasteme.common.dto.PasteRequestDTO;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.manager.TemporaryManager;
import cn.pasteme.common.utils.Checker;
import cn.pasteme.common.utils.TempKeyGenerator;
import cn.pasteme.common.utils.result.Response;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @version 1.0.1
 */
@Service
public class PasteServiceImpl implements PasteService {

    private final Checker checker;

    private final TemporaryManager temporaryManager;

    private final PermanentManager permanentManager;

    private final ManagerCreateFactory managerCreateFactory;

    public PasteServiceImpl(Checker checker, TemporaryManager temporaryManager, PermanentManager permanentManager, ManagerCreateFactory managerCreateFactory) {
        this.checker = checker;
        this.temporaryManager = temporaryManager;
        this.permanentManager = permanentManager;
        this.managerCreateFactory = managerCreateFactory;
    }

    @Override
    public Response<PasteResponseDTO> get(PasteRequestDTO pasteRequestDTO) {
        String key = pasteRequestDTO.getKey();

        return managerCreateFactory.getPasteManager(key).get(key);
    }

    @Override
    public Boolean delete(String key) {
        return managerCreateFactory.getPasteManager(key).erase(key).isSuccess();
    }

    @Override
    public String createPermanent(PasteRequestDTO pasteDTO) throws Exception {
        Response<String> response = permanentManager.save(pasteDTO);

        if (response.isSuccess()) {
            return response.getData();
        }
        throw new Exception("create permanent failed");
    }

    @Override
    public String createTemporary(PasteRequestDTO pasteDTO) throws Exception {
        String key = null;

        if (pasteDTO.getKey() == null || pasteDTO.getKey().isBlank()) {
            boolean flag = true;
            int times = 100;

            while (flag && times > 0) {
                times --;
                key = TempKeyGenerator.generator();
                if (temporaryManager.countByKey(key).getData() == 0) {
                    flag = false;
                }
            }

            if (flag) {
                throw new Exception("Random key generates an exception");
            }
            pasteDTO.setKey(key);
            temporaryManager.save(pasteDTO);

        } else {
            key = pasteDTO.getKey();
            if (temporaryManager.countByKey(key).getData() == 0) {
                temporaryManager.save(pasteDTO);
            } else {
                throw new Exception("The key are repeated");
            }
        }

        return key;
    }
}
