/*
 * Copyright 2021 The Chromium Authors. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be
 * found in the LICENSE file.
 */
package com.beansoft.intellij.openapi.util;

import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.util.system.CpuArch;

/**
 * Fix Deprecated warning of is32Bit and is64Bit.
 * Provides information about operating system, system-wide settings, and Java Runtime.
 * @author beansoft
 * @date 2021-05-27
 */
@SuppressWarnings("unused")
public final class SystemInfo {
  public static final String OS_NAME = SystemInfoRt.OS_NAME;
  public static final String OS_VERSION = SystemInfoRt.OS_VERSION;
  public static final String OS_ARCH = System.getProperty("os.arch");

  public static final boolean isWindows = SystemInfoRt.isWindows;
  public static final boolean isMac = SystemInfoRt.isMac;
  public static final boolean isLinux = SystemInfoRt.isLinux;
  public static final boolean isFreeBSD = SystemInfoRt.isFreeBSD;
  public static final boolean isSolaris = SystemInfoRt.isSolaris;
  public static final boolean isUnix = SystemInfoRt.isUnix;

  /** may be inaccurate; please use {@link CpuArch} instead */
  //@Deprecated
  //@ApiStatus.ScheduledForRemoval(inVersion = "2022.1")
  public static final boolean is32Bit = CpuArch.CURRENT == CpuArch.X86;

  /**  may be inaccurate; please use {@link CpuArch} instead */
  //@Deprecated
  //@ApiStatus.ScheduledForRemoval(inVersion = "2022.1")
  public static final boolean is64Bit = CpuArch.CURRENT == CpuArch.ARM64 || CpuArch.CURRENT == CpuArch.X86_64;


  public static String getOsNameAndVersion() {
    return (isMac ? "macOS" : OS_NAME) + ' ' + OS_VERSION;
  }
}
