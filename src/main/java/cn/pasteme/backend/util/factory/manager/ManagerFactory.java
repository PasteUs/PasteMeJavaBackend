package cn.pasteme.backend.util.factory.manager;

import cn.pasteme.common.manager.PasteManager;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.manager.TemporaryManager;
import cn.pasteme.common.utils.Checker;
import org.springframework.stereotype.Component;

/**
 * @author Moyu
 * @version 1.0.0
 */
@Component
public class ManagerFactory {

    private final PermanentManager permanentManager;

    private final TemporaryManager temporaryManager;

    private final Checker checker;

    public ManagerFactory(PermanentManager permanentManager, TemporaryManager temporaryManager, Checker checker) {
        this.permanentManager = permanentManager;
        this.temporaryManager = temporaryManager;
        this.checker = checker;
    }

    public PasteManager getPasteManager(String key) {
        if (checker.containAlpha(key)) {
            return temporaryManager;
        } else {
            return permanentManager;
        }
    }
}
