package ru.gr05307.net

import java.net.Socket

class ConnectedClient(private val socket: Socket) {
    companion object {
        private val clients = mutableListOf<ConnectedClient>()
    }
    private val communicator = Communicator(socket)

    init{
        communicator.addDataListener { parseData(it) }
        clients.add(this)
    }

    private fun parseData(data: String) {
        clients.forEach { it.sendData(data) }
    }

    fun start() = communicator.start()
    fun sendData(data: String) = communicator.sendData(data)
    fun stop() = communicator.stop()
}