package beansoft.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Using asyncTask to avoid "Synchronous execution on EDT" issue. EDT means Event Disptach Thread(The AWT/Swing UI Thread).
 * @author beansoft
 * @date 2021-05-28
 */
public class ThreadUtil {
    public static void asyncWriteTask(Runnable action) throws ProcessCanceledException {
        ApplicationManager.getApplication().invokeAndWait(action, ApplicationManager.getApplication().getDefaultModalityState());
    }

    /**
     * Requests pooled thread to execute the action.
     * <p>
     * This pool is an
     * <ul>
     * <li>Unbounded.</li>
     * <li>Application-wide, always active, non-shutdownable singleton.</li>
     * </ul>
     * You can use this pool for long-running and/or IO-bound tasks.
     *
     * @param action to be executed
     * @return future result
     */
    public static void asyncTask(Runnable action) {
        ApplicationManager.getApplication().executeOnPooledThread(action);
    }

    public static void invokeLater(Runnable runnable) {
        ApplicationManager.getApplication().invokeLater(runnable);
    }

    /**
     * Please use Application.invokeLater() with a modality state (or GuiUtils, or TransactionGuard methods), unless you work with Swings internals
     * and 'runnable' deals with Swings components only and doesn't access any PSI, VirtualFiles, project/module model or other project settings. For those, use GuiUtils, application.invoke* or TransactionGuard methods.<p/>
     *
     * On AWT thread, invoked runnable immediately, otherwise do {@link SwingUtilities#invokeLater(Runnable)} on it.
     */
    public static void invokeLaterIfNeeded(@NotNull Runnable runnable) {
        UIUtil.invokeLaterIfNeeded(runnable);
    }

    /**
     * Please use Application.invokeAndWait() with a modality state (or GuiUtils, or TransactionGuard methods), unless you work with Swings internals
     * and 'runnable' deals with Swings components only and doesn't access any PSI, VirtualFiles, project/module model or other project settings.<p/>
     *
     * Invoke and wait in the event dispatch thread
     * or in the current thread if the current thread
     * is event queue thread.
     * DO NOT INVOKE THIS METHOD FROM UNDER READ ACTION.
     *
     * @param runnable a runnable to invoke
     * @see #invokeAndWaitIfNeeded(ThrowableRunnable)
     */
    public static void invokeAndWaitIfNeeded(@NotNull Runnable runnable) {
        UIUtil.invokeAndWaitIfNeeded(runnable);
    }

}
