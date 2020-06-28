package com.matkosmoljan.apps.autousbtethering.domain

import com.matkosmoljan.apps.autousbtethering.FunctionResult

/**
 * Turns USB tethering on. Implementations may vary depending on the Android version used, device etc.
 */
interface TetherSwitch {
    fun turnTetheringOn(): FunctionResult<Unit>
    fun turnTetheringOff(): FunctionResult<Unit>
    fun turnTetheringOn(method:String): FunctionResult<Unit>
    fun turnTetheringOff(method:String): FunctionResult<Unit>
    fun getMethodNumber(): Int
}