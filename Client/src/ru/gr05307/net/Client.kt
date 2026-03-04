package ru.gr05307.net

import ru.gr05307.ui.InfoType
import ru.gr05307.ui.RequestType
import java.net.Socket
import kotlin.concurrent.thread

class Client(
    host: String = "127.0.0.1",
    port: Int = 5307,
) {
    private val messageListeners: MutableList<(String, String) -> Unit> = mutableListOf()
    private val infoListeners: MutableList<(String, InfoType) -> Unit> = mutableListOf()
    private val requestListeners: MutableList<(RequestType) -> Unit> = mutableListOf()
    private val communicator: Communicator

    val isActive: Boolean
        get() = communicator.isActive


    init{
        communicator = Communicator(Socket(host, port))
        //communicator.addDataListener(::parseData)
        communicator.addDataListener { parseData(it) }
    }

    fun addMessageListener(listener: (String, String) -> Unit) {
        messageListeners.add(listener)
    }

    fun removeMessageListener(listener: (String, String) -> Unit) {
        messageListeners.remove(listener)
    }

    fun addInfoListener(listener: (String, InfoType) -> Unit) {
        infoListeners.add(listener)
    }

    fun removeInfoListener(listener: (String, InfoType) -> Unit) {
        infoListeners.remove(listener)
    }

    fun addRequestListener(listener: (RequestType) -> Unit) {
        requestListeners.add(listener)
    }

    fun removeRequestListener(listener: (RequestType) -> Unit) {
        requestListeners.remove(listener)
    }

    fun sendData(data: String) {
        communicator.sendData(data)
    }

    fun parseData(data: String) {
        messageListeners.forEach {
            it("SOMEONE", data)
        }
    }

    fun start() {
        communicator.start()
    }

    fun stop() {
        communicator.stop()
    }

}