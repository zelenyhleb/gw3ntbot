package ru.krivocraft.gwentbot.core.telegram

import me.ivmg.telegram.entities.KeyboardReplyMarkup

interface State {

    fun message() : String

    fun keyboard() : KeyboardReplyMarkup

}