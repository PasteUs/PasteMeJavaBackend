package cn.pasteme.backend.service.impl;

import cn.pasteme.backend.service.PasteService;
import cn.pasteme.common.dto.PasteRequestDTO;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.utils.result.Response;
import org.springframework.stereotype.Service;

/**
 * @author moyu
 * @version 1.0.0
 */
@Service
public class PasteServiceImpl implements PasteService {

    @Override
    public Response<PasteResponseDTO> get(PasteRequestDTO pasteRequestDTO) {
        return null;
    }

    @Override
    public Boolean delete(String key) {
        return null;
    }

    @Override
    public String createPermanent(PasteRequestDTO pasteDTO) {
        return null;
    }

    @Override
    public String createTemporary(PasteRequestDTO pasteDTO) {
        return null;
    }
}
