package ru.gr05307.ui

import java.awt.TrayIcon
enum class InfoType{
    INFORMATION, WARNING, ERROR
}

enum class RequestType{
    USERNAME
}

interface UI {
    fun showMessage(author: String, msg: String)
    fun showInfo(msg: String, msgType: InfoType)
    fun showRequest(request: RequestType)

    fun addUserDataListener(listener: (String) -> Unit)
    fun removeUserDataListener(listener: (String) -> Unit)

    fun start()
}