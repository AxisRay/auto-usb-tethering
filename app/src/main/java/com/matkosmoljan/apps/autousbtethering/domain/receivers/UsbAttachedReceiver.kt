package com.matkosmoljan.apps.autousbtethering.domain.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.matkosmoljan.apps.autousbtethering.domain.TetherSwitch
import com.matkosmoljan.apps.autousbtethering.domain.shell.ShellTetherSwitch
import com.matkosmoljan.apps.autousbtethering.fold


class UsbAttachedReceiver : BroadcastReceiver() {

    private val tetherSwitch: TetherSwitch = ShellTetherSwitch()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.hardware.usb.action.USB_STATE") {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val enable = prefs.getBoolean("enableusbt", false)
            if(enable) {
                tetherSwitch.turnTetheringOn().fold(
                    onSuccess = {
                    }
                    ,
                    onFailure = {
                    }
                )
            }

        }
    }
}
