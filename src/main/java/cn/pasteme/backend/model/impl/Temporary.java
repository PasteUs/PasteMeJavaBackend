package cn.pasteme.backend.model.impl;

import cn.pasteme.backend.model.Paste;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Lucien on 2019/9/27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Temporary extends Paste {

    @Override
    public Paste get(String key) {
        // TODO
        return null;
    }

    @Override
    public String save() {
        // TODO
        return null;
    }

    @Override
    public Boolean delete(String key) {
        // TODO
        return null;
    }

    @Override
    public Boolean isExist(String key) {
        // TODO
        return null;
    }

    @Override
    public Boolean isDeleted(String key) {
        // TODO
        return null;
    }
}
