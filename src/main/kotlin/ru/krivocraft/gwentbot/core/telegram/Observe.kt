package ru.krivocraft.gwentbot.core.telegram

import me.ivmg.telegram.entities.KeyboardButton
import me.ivmg.telegram.entities.KeyboardReplyMarkup
import ru.krivocraft.gwentbot.core.Match

class Observe(match: Match) : State {

    override fun message(): String {
        return ""
    }

    override fun keyboard(): KeyboardReplyMarkup {
        val buttons = listOf(
            listOf(KeyboardButton(text = "Посмотреть линии")),
            listOf(KeyboardButton(text = "Свои карты")),
            listOf(KeyboardButton(text = "Свой сброс")),
            listOf(KeyboardButton(text = "Чужой сброс"))
        )
        return KeyboardReplyMarkup(buttons)
    }

}