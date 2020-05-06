package ru.krivocraft.gwentbot.core

import me.ivmg.telegram.entities.ReplyKeyboardRemove
import me.ivmg.telegram.entities.ReplyMarkup
import ru.krivocraft.gwentbot.core.model.Field
import ru.krivocraft.gwentbot.core.model.Player

class Match(
    val players: List<Player>,
    private val message: Message,
    private val field: Field = Field()
) {

    init {
        players.forEach {
            "Session found".notifyPlayer(it, ReplyKeyboardRemove())
        }
        nextRound()
        nextTurn()
    }

    private fun String.notifyPlayer(player: Player, replyMarkup: ReplyMarkup) {
        message.send(player.chatId, this, replyMarkup = replyMarkup)
    }

    private fun nextTurn() {

    }

    private fun nextRound() {

    }

    interface Message {
        fun send(chatId: Long, message: String, replyMarkup: ReplyMarkup)
    }
}