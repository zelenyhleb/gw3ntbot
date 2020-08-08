package ru.krivocraft.gwentbot.core.telegram

import ru.krivocraft.gwentbot.core.Message

interface Handler {

    fun handle(message: Message, chatId: Long)
}