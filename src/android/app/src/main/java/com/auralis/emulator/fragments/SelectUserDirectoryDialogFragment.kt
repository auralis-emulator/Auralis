// Copyright Auralis Emulator Project / Auralis Emulator Project
// Licensed under GPLv2 or any later version
// Refer to the license.txt file included.

package com.auralis.emulator.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.auralis.emulator.R
import com.auralis.emulator.ui.main.MainActivity
import com.auralis.emulator.utils.PermissionsHandler
import com.auralis.emulator.viewmodel.HomeViewModel

class SelectUserDirectoryDialogFragment(titleOverride: Int? = null, descriptionOverride: Int? = null) : DialogFragment() {
    private lateinit var mainActivity: MainActivity

    private val title = titleOverride ?: R.string.select_citra_user_folder
    private val description = descriptionOverride ?: R.string.selecting_user_directory_without_write_permissions

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mainActivity = requireActivity() as MainActivity

        isCancelable = false

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(description)
            .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int ->
                PermissionsHandler.compatibleSelectDirectory(mainActivity.openAuralisDirectoryLostPermission)
            }
            .show()
    }

    companion object {
        const val TAG = "SelectUserDirectoryDialogFragment"

        fun newInstance(activity: FragmentActivity, titleOverride: Int? = null, descriptionOverride: Int? = null):
                SelectUserDirectoryDialogFragment {
            ViewModelProvider(activity)[HomeViewModel::class.java].setPickingUserDir(true)
            return SelectUserDirectoryDialogFragment(titleOverride, descriptionOverride)
        }
    }
}
