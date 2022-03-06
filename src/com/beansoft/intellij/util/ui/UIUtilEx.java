package com.beansoft.intellij.util.ui;

import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * Port some features from 212
 * @author beansoft
 * @see https://github.com/JetBrains/intellij-community/blob/master/platform/util/ui/src/com/intellij/util/ui/UIUtil.java
 */
public class UIUtilEx {
    private static final Key<Boolean> IS_SHOWING = Key.create("Component.isShowing");
    private static final Key<Boolean> HAS_FOCUS = Key.create("Component.hasFocus");

    /**
     * Checks if a component is showing in a more general sense than UI visibility,
     * sometimes it's useful to limit various activities by checking the visibility of the real UI,
     * but it could be unneeded in headless mode or in other scenarios.
     * @see UIUtil#hasFocus(Component)
     */
    @ApiStatus.Experimental
    public static boolean isShowing(@NotNull Component component) {
        if (Boolean.getBoolean("java.awt.headless") || component.isShowing()) {
            return true;
        }

        while (component != null) {
            JComponent jComponent = component instanceof JComponent ? (JComponent)component : null;
            if (jComponent != null && Boolean.TRUE.equals(jComponent.getClientProperty(IS_SHOWING))) {
                return true;
            }
            component = component.getParent();
        }

        return false;
    }

    /**
     * Checks if a component is focused in a more general sense than UI focuses,
     * sometimes useful to limit various activities by checking the focus of real UI,
     * but it could be unneeded in headless mode or in other scenarios.
     * @see UIUtil#isShowing(Component)
     */
//    @ApiStatus.Experimental
    public static boolean hasFocus(@NotNull Component component) {
        if (GraphicsEnvironment.isHeadless() || component.hasFocus()) {
            return true;
        }

        JComponent jComponent = component instanceof JComponent ? (JComponent)component : null;
        return jComponent != null && Boolean.TRUE.equals(jComponent.getClientProperty(HAS_FOCUS));
    }
}
