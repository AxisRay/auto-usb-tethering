package com.matkosmoljan.apps.autousbtethering

import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }

        override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
            return true
        }

        override fun onPreferenceTreeClick(preference: Preference): Boolean {
                if (preference.key.equals("enableusbt")){
                    if(preference is SwitchPreferenceCompat){
                        val tetherSwitch: TetherSwitch = ShellTetherSwitch()
                        if(preference.isChecked){
                            tetherSwitch.turnTetheringOn().fold(
                                onSuccess = {
                                }
                                ,
                                onFailure = {
                                    Toast.makeText(this.context,R.string.root_error_message,Toast.LENGTH_LONG).show()
                                }
                            )
                        }else{
                            tetherSwitch.turnTetheringOff().fold(
                                onSuccess = {
                                }
                                ,
                                onFailure = {
                                    Toast.makeText(this.context,R.string.root_error_message,Toast.LENGTH_LONG).show()
                                }
                            )
                        }
                    }
                }
//                else if(preference.key.equals("methodNumber")){
//                }
            return true
        }
    }
}