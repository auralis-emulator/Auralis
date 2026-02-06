// Copyright 2023 Auralis Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package com.auralis.emulator.features.settings.model

interface AbstractSetting {
    val key: String?
    val section: String?
    val isRuntimeEditable: Boolean
    val valueAsString: String
    val defaultValue: Any
}
