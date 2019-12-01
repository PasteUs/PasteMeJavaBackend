package cn.pasteme.backend.service.impl;

import cn.pasteme.backend.exception.DuplicateException;
import cn.pasteme.backend.exception.ManipulationException;
import cn.pasteme.backend.service.PasteService;
import cn.pasteme.backend.util.factory.manager.ManagerFactory;
import cn.pasteme.common.dto.PasteRequestDTO;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.manager.TemporaryManager;
import cn.pasteme.common.utils.Checker;
import cn.pasteme.backend.util.TempKeyGenerator;
import cn.pasteme.common.utils.result.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @version 1.0.1
 */
@Service
public class PasteServiceImpl implements PasteService {

    private final TemporaryManager temporaryManager;

    private final PermanentManager permanentManager;

    private final ManagerFactory managerFactory;

    public PasteServiceImpl(@Autowired TemporaryManager temporaryManager,
                            @Autowired PermanentManager permanentManager,
                            @Autowired ManagerFactory managerFactory) {
        this.temporaryManager = temporaryManager;
        this.permanentManager = permanentManager;
        this.managerFactory = managerFactory;
    }

    @Override
    public Response<PasteResponseDTO> get(PasteRequestDTO pasteRequestDTO) {
        String key = pasteRequestDTO.getKey();

        return managerFactory.getPasteManager(key).get(key);
    }

    @Override
    public Boolean delete(String key) {
        return managerFactory.getPasteManager(key).erase(key).isSuccess();
    }

    @Override
    public String createPermanent(PasteRequestDTO pasteDTO) throws ManipulationException {
        Response<String> response = permanentManager.save(pasteDTO);

        if (response.isSuccess()) {
            return response.getData();
        }
        throw new ManipulationException("Create permanent failed");
    }

    @Override
    public String createTemporary(PasteRequestDTO pasteDTO) throws ManipulationException, DuplicateException {
        String key = null;

        if (pasteDTO.getKey() == null || pasteDTO.getKey().isBlank()) {
            boolean flag = true;
            int times = 100;

            while (flag && times > 0) {
                times--;
                key = TempKeyGenerator.generator(TempKeyGenerator.CURRENT_LENGTH);
                if (temporaryManager.countByKey(key).getData() == 0) {
                    flag = false;
                }
            }

            if (flag) {
                throw new ManipulationException("Random key generates an exception");
            }
            pasteDTO.setKey(key);
            temporaryManager.save(pasteDTO);

        } else {
            key = pasteDTO.getKey();
            if (temporaryManager.countByKey(key).getData() == 0) {
                temporaryManager.save(pasteDTO);
            } else {
                throw new DuplicateException("Duplicate key");
            }
        }

        return key;
    }
}
