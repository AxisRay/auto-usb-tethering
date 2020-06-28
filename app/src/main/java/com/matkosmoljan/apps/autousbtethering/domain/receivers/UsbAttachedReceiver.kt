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
    private var reboot = false
    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == Intent.ACTION_LOCKED_BOOT_COMPLETED || intent.action == Intent.ACTION_BOOT_COMPLETED) {
//            reboot = true
//        }
        if (intent.action == "android.hardware.usb.action.USB_STATE") {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val enable = prefs.getBoolean("enableusbt", false)
            val methodNumber = prefs.getString("methodNumber", tetherSwitch.getMethodNumber().toString())
            if(enable) {
                tetherSwitch.turnTetheringOn(methodNumber)
            }else{
                tetherSwitch.turnTetheringOff(methodNumber)
            }
        }
    }
}
