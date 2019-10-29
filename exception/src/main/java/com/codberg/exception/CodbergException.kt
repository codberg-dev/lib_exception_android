package com.codberg.exception

import android.app.Activity
import java.lang.Exception

class CodbergException {

    fun globalException(activity: Activity) {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(activity))
    }

    fun tryCatchException(e: Exception) {
        ExceptionHandler().catchException(e)
    }
}