package com.beansoft.intellij.ide.ui

import com.intellij.openapi.util.SystemInfoRt
import com.intellij.ui.JreHiDpiUtil
import com.intellij.ui.scale.JBUIScale
import kotlin.math.roundToInt

class UISettingsEx {
    companion object {
        /**
         * Copied from com.intellij.ide.ui.UISettingsState#defFontSize
         * Returns the default font size scaled by #defFontScale
         *
         * @see com.intellij.ide.ui.UISettingsState.defFontSize
         * @return the default scaled font size
         */
        @JvmStatic
        val defFontSize: Int
            get() = (JBUIScale.DEF_SYSTEM_FONT_SIZE * defFontScale).roundToInt()

        /**
         * Returns the default font scale, which depends on the HiDPI mode (see [com.intellij.ui.scale.ScaleType]).
         * <p>
         * The font is represented:
         * - in relative (dpi-independent) points in the JRE-managed HiDPI mode, so the method returns 1.0f
         * - in absolute (dpi-dependent) points in the IDE-managed HiDPI mode, so the method returns the default screen scale
         *
         * @return the system font scale
         */
        @JvmStatic
        val defFontScale: Float
            get() = when {
                JreHiDpiUtil.isJreHiDPIEnabled() -> 1f
                else -> JBUIScale.sysScale()
            }

        /*
        解决 212 问题 找不到方法
        Notes 2022.1.2 is binary incompatible with IntelliJ IDEA Ultimate IU-221.4501.155 due to the following problem
        Invocation of unresolved method UISettings.restoreFontSize(int, Float)
         */
        @JvmStatic
        fun restoreFontSize(readSize: Int, readScale: Float?): Int {
            var size = readSize
            if (readScale == null || readScale <= 0) {
                // Reset font to default on switch from IDE-managed HiDPI to JRE-managed HiDPI. Doesn't affect OSX.
                if (!SystemInfoRt.isMac && JreHiDpiUtil.isJreHiDPIEnabled()) {
                    size = defFontSize// 221 下面的UISettings.defFontSize变成了Float类型
                }
            }
            else if (readScale != defFontScale) {
                size = ((readSize / readScale) * defFontScale).roundToInt()
            }
//            if (JBUIScale.SCALE_VERBOSE) LOG.info("Loaded: fontSize=$readSize, fontScale=$readScale; restored: fontSize=$size, fontScale=${UISettings.defFontScale}")
            return size
        }
    }
}