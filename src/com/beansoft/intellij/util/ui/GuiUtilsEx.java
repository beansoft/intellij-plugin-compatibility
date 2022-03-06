package com.beansoft.intellij.util.ui;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import org.jetbrains.annotations.NotNull;

/**
 * Fix issue:
 * Deprecated method GuiUtils.invokeLaterIfNeeded(...) is invoked in WriteSafeDebounceTask$1.run().
 * This method will be removed in 2022.1
 * @date 2022-1-31
 */
public class GuiUtilsEx {
    /**
     * @deprecated Use ModalityUiUtil instead
     */
    @Deprecated
//    @ApiStatus.ScheduledForRemoval(inVersion = "2022.1")
    public static void invokeLaterIfNeeded(@NotNull Runnable runnable, @NotNull ModalityState modalityState) {
        Application app = ApplicationManager.getApplication();
        if (app.isDispatchThread()) {
            runnable.run();
        }
        else {
            app.invokeLater(runnable, modalityState);
        }
    }


}
