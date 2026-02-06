// Copyright 2023 Auralis Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package com.auralis.emulator.ui.main

interface ThemeProvider {
    /**
     * Provides theme ID by overriding an activity's 'setTheme' method and returning that result
     */
    var themeId: Int
}
