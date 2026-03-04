package ru.gr05307

import ru.gr05307.net.Client
import ru.gr05307.ui.UI

class MainViewModel(
    val client: Client,
    val ui: UI,
) {
    fun start(){
        client.addMessageListener { author, message ->
            ui.showMessage(author,message)
        }
        client.addInfoListener { message, msgType ->
            ui.showInfo(message,msgType)
        }
        client.addRequestListener {
            ui.showRequest(it)
        }

        client.start()
        ui.addUserDataListener {
            client.sendData(it)
        }
        ui.start()
    }
}