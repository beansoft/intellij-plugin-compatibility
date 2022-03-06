/*
 * Copyright 2021 The Chromium Authors. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be
 * found in the LICENSE file.
 */
package com.beansoft.intellij.util;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.util.Condition;
import org.jetbrains.annotations.NotNull;

/**
 * Port from IDEA 2021 to supports 2020.3.
 * @author beansoft
 * @date 2021/11/29
 */
public class ModalityUiUtil {
  /**
   * Use this method when access any PSI, VirtualFiles, project/module model or other project settings, otherwise using
   * a corresponding method from UiUtil is allowed.<p/>

   * Causes {@code runnable.run()} to be executed asynchronously on the
   * AWT event dispatching thread under Write Intent lock, when IDE is in the specified modality
   * state (or a state with less modal dialogs open).
   * <p>
   * Please use this method instead of {@link javax.swing.SwingUtilities#invokeLater(Runnable)} or {@link com.intellij.util.ui.UIUtil} methods
   * for the reasons described in {@link ModalityState} documentation.
   *
   * @param runnable the runnable to execute.
   * @param modalityState    the state in which the runnable will be executed.
   */
  public static void invokeLaterIfNeeded(@NotNull Runnable runnable, @NotNull ModalityState modalityState) {
    Application app = ApplicationManager.getApplication();
    if (app.isDispatchThread()) {
      runnable.run();
    }
    else {
      app.invokeLater(runnable, modalityState);
    }
  }

  /**
   * Use this method when access any PSI, VirtualFiles, project/module model or other project settings, otherwise using
   * a corresponding method from UiUtil is allowed.<p/>

   * Causes {@code runnable.run()} to be executed asynchronously on the
   * AWT event dispatching thread under Write Intent lock, when IDE is in the specified modality
   * state(or a state with less modal dialogs open) - unless the expiration condition is fulfilled.
   * This will happen after all pending AWT events have been processed.
   * <p>
   * Please use this method instead of {@link javax.swing.SwingUtilities#invokeLater(Runnable)} or {@link com.intellij.util.ui.UIUtil} methods
   * for the reasons described in {@link ModalityState} documentation.
   *
   * @param runnable the runnable to execute.
   * @param modalityState    the state in which the runnable will be executed.
   * @param expired  condition to check before execution.
   */
  public static void invokeLaterIfNeeded(@NotNull Runnable runnable, @NotNull ModalityState modalityState, @NotNull Condition expired) {
    Application app = ApplicationManager.getApplication();
    if (app.isDispatchThread()) {
      runnable.run();
    }
    else {
      app.invokeLater(runnable, modalityState, expired);
    }
  }
}
