package vi.sukov.fitcherhelper.core.repository

import android.content.Context

class LocalRepository(context: Context) {

    private companion object {
        const val PREFERENCES: String = "FitchPref"

        const val LOCATION_KEY: String = "Location"
        const val LOCATION_DEF: String = "Loading..."

        const val IS_FIRST_RUN_KEY: String = "IsFirsRun"
        const val IS_FIRSt_RUN_DEF: Boolean = false
    }

    private val preferences = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun getLocation() = preferences.getString(LOCATION_KEY, LOCATION_DEF)

    fun putLocation(location: String) {
        editor.putString(LOCATION_KEY, location).commit()
    }

    fun isFirstRun() = preferences.getBoolean(IS_FIRST_RUN_KEY, IS_FIRSt_RUN_DEF)

    fun putFirstRun(b: Boolean) {
        editor.putBoolean(IS_FIRST_RUN_KEY, b).commit()
    }
}