package com.codberg.exception

import com.crashlytics.android.Crashlytics

class CodbergCrash {

    fun TestCrash() {
        Crashlytics.getInstance().crash()
    }

    class Crash {
        fun customLog(key: String, value: String) {
            Crashlytics.setString(key, value)
        }

        fun customLogMessage(logMessage: String) {
            Crashlytics.log(logMessage)
        }
    }
}