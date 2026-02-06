// Copyright Auralis Emulator Project / Auralis Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package com.auralis.emulator.utils

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.auralis.emulator.fragments.AuralisDirectoryDialogFragment
import com.auralis.emulator.fragments.CopyDirProgressDialog
import com.auralis.emulator.model.SetupCallback
import com.auralis.emulator.viewmodel.HomeViewModel

/**
 * Auralis directory initialization ui flow controller.
 */
class AuralisDirectoryHelper(private val fragmentActivity: FragmentActivity, private val lostPermission: Boolean) {
    fun showAuralisDirectoryDialog(result: Uri, callback: SetupCallback? = null, buttonState: () -> Unit) {
        val citraDirectoryDialog = AuralisDirectoryDialogFragment.newInstance(
            fragmentActivity,
            result.toString(),
            AuralisDirectoryDialogFragment.Listener { moveData: Boolean, path: Uri ->
                val previous = PermissionsHandler.citraDirectory
                // Do noting if user select the previous path.
                if (path == previous && !lostPermission) {
                    return@Listener
                }

                val takeFlags = Intent.FLAG_GRANT_WRITE_URI_PERMISSION or
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                fragmentActivity.contentResolver.takePersistableUriPermission(
                    path,
                    takeFlags
                )
                if (!moveData || previous.toString().isEmpty()) {
                    initializeAuralisDirectory(path)
                    buttonState()
                    val viewModel = ViewModelProvider(fragmentActivity)[HomeViewModel::class.java]
                    viewModel.setUserDir(fragmentActivity, path.path!!)
                    viewModel.setPickingUserDir(false)
                    return@Listener
                }

                // If user check move data, show copy progress dialog.
                CopyDirProgressDialog.newInstance(fragmentActivity, previous, path, callback)
                    ?.show(fragmentActivity.supportFragmentManager, CopyDirProgressDialog.TAG)
            })
        citraDirectoryDialog.show(
            fragmentActivity.supportFragmentManager,
            AuralisDirectoryDialogFragment.TAG
        )
    }

    companion object {
        fun initializeAuralisDirectory(path: Uri) {
            PermissionsHandler.setAuralisDirectory(path.toString())
            DirectoryInitialization.resetAuralisDirectoryState()
            DirectoryInitialization.start()
        }
    }
}
