package cn.pasteme.backend.service;

import cn.pasteme.common.dto.PasteContentDTO;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.manager.TemporaryManager;
import cn.pasteme.common.utils.Md5;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author: 白振宇
 * @Date： 2019/10/2 0:52
 */
@Service
public class PasteService {

    private final PermanentManager permanentManager;
    private final TemporaryManager temporaryManager;

    public PasteService(PermanentManager permanentManager, TemporaryManager temporaryManager) {
        this.permanentManager = permanentManager;
        this.temporaryManager = temporaryManager;
    }

    public PasteContentDTO getPasteByKey(String id, String pwd) {
        PasteContentDTO pasteContentDTO;
        try {
            long key = Long.valueOf(id);
            pasteContentDTO = permanentManager.getPasteContentByKey(key, pwd);
        } catch (NumberFormatException e) {
            pasteContentDTO = temporaryManager.getPasteContentByKey(id, pwd);
        }
        return pasteContentDTO;
    }
}
