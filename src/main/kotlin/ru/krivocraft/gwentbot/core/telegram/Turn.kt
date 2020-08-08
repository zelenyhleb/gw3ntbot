package ru.krivocraft.gwentbot.core.telegram

import me.ivmg.telegram.entities.KeyboardButton
import me.ivmg.telegram.entities.KeyboardReplyMarkup
import ru.krivocraft.gwentbot.core.Match

class Turn(match: Match) : State {

    override fun message(): String {
        return ""
    }

    override fun keyboard(): KeyboardReplyMarkup {
        val buttons = listOf(
            listOf(KeyboardButton(text = "Пас")),
            listOf(KeyboardButton(text = "Информация о поле")),
            listOf(KeyboardButton(text = "Ходить"))
        )
        return KeyboardReplyMarkup(buttons)
    }
}