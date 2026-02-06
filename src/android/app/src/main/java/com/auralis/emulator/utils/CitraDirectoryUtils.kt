// Copyright Auralis Emulator Project / Auralis Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package com.auralis.emulator.utils

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.auralis.emulator.AuralisApplication

object AuralisDirectoryUtils {
    const val CITRA_DIRECTORY = "CITRA_DIRECTORY"
    const val LIME3DS_DIRECTORY = "LIME3DS_DIRECTORY"
    val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(AuralisApplication.appContext)

    fun needToUpdateManually(): Boolean {
        val directoryString = preferences.getString(CITRA_DIRECTORY, "")
        val limeDirectoryString = preferences.getString(LIME3DS_DIRECTORY,"")
        return (directoryString != "" && limeDirectoryString != "" && directoryString != limeDirectoryString)
    }

    fun attemptAutomaticUpdateDirectory() {
        val directoryString = preferences.getString(CITRA_DIRECTORY, "")
        val limeDirectoryString = preferences.getString(LIME3DS_DIRECTORY,"")
        if (needToUpdateManually()) {
            return;
        }
       if (directoryString == "" && limeDirectoryString != "") {
            // Upgrade from Auralis to Auralis
           PermissionsHandler.setAuralisDirectory(limeDirectoryString)
            removeLimeDirectoryPreference()
            DirectoryInitialization.resetAuralisDirectoryState()
            DirectoryInitialization.start()

       } else if (directoryString != "" && directoryString == limeDirectoryString) {
            // Both the Auralis and Auralis directories are the same,
            // so delete the obsolete Auralis value.
            removeLimeDirectoryPreference()
        }
    }

    fun removeLimeDirectoryPreference() {
        preferences.edit().remove(LIME3DS_DIRECTORY).apply()
    }
}
