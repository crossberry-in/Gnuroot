package com.rk.terminal.ui.screens.terminal

import android.os.Environment
import androidx.compose.runtime.mutableStateOf
import com.rk.libcommons.application
import com.rk.libcommons.child
import com.rk.terminal.App
import com.rk.terminal.ui.screens.settings.WorkingMode
import java.io.File

object Rootfs {
    val reTerminal = application!!.filesDir

    init {
        if (reTerminal.exists().not()){
            reTerminal.mkdirs()
        }
    }

    fun isCommonFilesDownloaded(): Boolean {
        return reTerminal.exists() && reTerminal.child("proot").exists() && reTerminal.child("libtalloc.so.2").exists()
    }

    fun isAlpineDownloaded(): Boolean {
        return reTerminal.child("alpine.tar.gz").exists()
    }

    fun isUbuntuDownloaded(): Boolean {
        return reTerminal.child("ubuntu-base.tar.gz").exists()
    }

    fun isModeReady(mode: Int): Boolean {
        if (!isCommonFilesDownloaded()) return false
        return when (mode) {
            WorkingMode.ALPINE -> isAlpineDownloaded()
            WorkingMode.UBUNTU -> isUbuntuDownloaded()
            else -> true // Android mode doesn't need rootfs
        }
    }
}