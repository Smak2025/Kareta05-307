package ru.gr05307

import ru.gr05307.net.Client
import ru.gr05307.ui.ConsoleUI

fun main() {
    val client = Client()
    val ui = ConsoleUI()
    val mvm = MainViewModel(client, ui)
    mvm.start()
}