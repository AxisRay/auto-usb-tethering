package com.matkosmoljan.apps.autousbtethering.domain.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_BOOT_COMPLETED
import android.content.Intent.ACTION_LOCKED_BOOT_COMPLETED
import android.preference.PreferenceManager
import com.matkosmoljan.apps.autousbtethering.domain.TetherSwitch
import com.matkosmoljan.apps.autousbtethering.domain.shell.ShellTetherSwitch

class BootCompletedReceiver : BroadcastReceiver() {
    private val tetherSwitch: TetherSwitch = ShellTetherSwitch()
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_LOCKED_BOOT_COMPLETED || intent.action == ACTION_BOOT_COMPLETED) {
            // Start main activity once at boot to check if the tethering has been switched on
//            MainActivity
//                .createIntent(context)
//                .let(context::startActivity)

            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            val enable = prefs.getBoolean("enableonbootusbt", false)
            if(enable) {
                tetherSwitch.turnTetheringOn()
            }
        }
    }
}