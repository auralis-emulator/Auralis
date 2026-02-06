// Copyright Auralis Emulator Project / Auralis Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package com.auralis.emulator.model

import androidx.annotation.Keep
import java.io.IOException

class GameInfo(path: String) {
    @Keep
    private val pointer: Long

    init {
        pointer = initialize(path)
    }

    protected external fun finalize()

    external fun getTitle(): String

    external fun isValid(): Boolean

    external fun isEncrypted(): Boolean

    external fun getTitleID(): Long

    external fun getRegions(): String

    external fun getCompany(): String

    external fun getIcon(): IntArray?

    external fun isSystemTitle(): Boolean

    external fun getIsVisibleSystemTitle(): Boolean

    external fun getFileType(): String

    external fun getIsInsertable(): Boolean

    companion object {
        @JvmStatic
        private external fun initialize(path: String): Long
    }
}
