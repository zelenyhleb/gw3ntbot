package ru.krivocraft.gwentbot.core

import me.ivmg.telegram.entities.ReplyMarkup

interface Message {
    fun send(chatId: Long, message: String, replyMarkup: ReplyMarkup)
}