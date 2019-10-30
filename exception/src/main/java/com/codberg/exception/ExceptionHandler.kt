package com.codberg.exception

import android.content.Intent
import android.os.Build
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.core.content.IntentCompat
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess


class ExceptionHandler() : Thread.UncaughtExceptionHandler {
    private var myContext: Context? = null

    enum class ErrorType {
        GLOBAL,
        TRYCATCH
    }

    constructor(myContext: Context) : this() {
        this.myContext = myContext
    }

    private val LINE_SEPARATOR = "\n"

    override fun uncaughtException(thread: Thread, exception: Throwable) {
        val errorString = getErrorAndDeviceInfo(exception, ErrorType.GLOBAL)

        Log.e("CustomException", errorString)

        val intent = myContext!!.packageManager.getLaunchIntentForPackage(myContext!!.packageName)
        intent?.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        myContext?.startActivity(intent)

        exitReport()
    }

    private fun exitReport() {
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(10)
    }

    fun catchException(firebaseFlag: Boolean, exception: Throwable) {
        val errorString = getErrorAndDeviceInfo(exception, ErrorType.TRYCATCH)

        if(firebaseFlag) {
            CodbergCrash.Crash().customLogMessage(errorString)
        }

        Log.e("[Try/Catch] CodbergException", errorString)
    }

    private fun getErrorAndDeviceInfo(exception: Throwable, errorType: ErrorType): String {
        val stackTrace = StringWriter()
        exception.printStackTrace(PrintWriter(stackTrace))
        val errorReport = StringBuilder()
        errorReport.append("************ CAUSE OF ERROR ************\n\n")
        errorReport.append(stackTrace.toString())

        errorReport.append("\n************ INFORMATION ***********\n")
        errorReport.append("ErrorType: ")
        errorReport.append(errorType)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Model: ")
        errorReport.append(Build.MODEL)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Brand: ")
        errorReport.append(Build.BRAND)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("API_Level: ")
        errorReport.append(Build.VERSION.SDK_INT)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Android_Version: ")
        errorReport.append(Build.VERSION.RELEASE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Version_Name: ")
        errorReport.append(BuildConfig.VERSION_NAME)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Version_Code: ")
        errorReport.append(BuildConfig.VERSION_CODE)
        errorReport.append(LINE_SEPARATOR)
        errorReport.append("Build_Type: ")
        errorReport.append(BuildConfig.BUILD_TYPE)
        errorReport.append(LINE_SEPARATOR)

        return errorReport.toString()
    }
}
