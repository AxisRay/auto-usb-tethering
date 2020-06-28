package com.matkosmoljan.apps.autousbtethering

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.matkosmoljan.apps.autousbtethering.domain.TetherSwitch
import com.matkosmoljan.apps.autousbtethering.domain.shell.ShellTetherSwitch

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {
        val tetherSwitch: TetherSwitch = ShellTetherSwitch()
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

        }

        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
            onChange()
            return true
        }

        override fun onPreferenceTreeClick(preference: Preference): Boolean {
            onChange()
            return true
        }

        private fun onChange(){
            val prefs = PreferenceManager.getDefaultSharedPreferences(this.context)
            val enable = prefs.getBoolean("enableusbt", false)
            val methodNumber = prefs.getString("methodNumber", tetherSwitch.getMethodNumber().toString())
            if(enable){
                tetherSwitch.turnTetheringOn(methodNumber)
            }else{
                tetherSwitch.turnTetheringOff(methodNumber)
            }
        }
    }
}