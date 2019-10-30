package com.codberg.exception

import android.app.Activity
import android.content.Context
import java.lang.Exception

class CodbergException {

    fun globalException(context: Context) {
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler(context))
    }

    fun tryCatchException(e: Exception, firebaseFlag: Boolean = false) {
        ExceptionHandler().catchException(firebaseFlag, e)
    }
}