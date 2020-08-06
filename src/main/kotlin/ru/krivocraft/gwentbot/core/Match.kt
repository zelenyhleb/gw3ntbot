package ru.krivocraft.gwentbot.core

import me.ivmg.telegram.entities.ReplyKeyboardRemove
import me.ivmg.telegram.entities.ReplyMarkup
import ru.krivocraft.gwentbot.core.model.Deck
import ru.krivocraft.gwentbot.core.model.Field
import ru.krivocraft.gwentbot.core.model.Player
import ru.krivocraft.gwentbot.core.model.Pool

class Match(
    val players: List<Player>,
    private val message: Message,
    private val field: Field = Field()
) {
    private val decks = mutableListOf<Deck>()
    init {
        players.forEach {
            notifyPlayer("Session found", it, ReplyKeyboardRemove())
            it.deck.cards.shuffle()
            decks.add(it.deck)
            field.fillPool(it.deck)
        }
        nextRound()
        nextTurn()
    }

    private fun notifyPlayer(text: String, player: Player, replyMarkup: ReplyMarkup) {
        message.send(player.chatId, text, replyMarkup = replyMarkup)
    }

    private fun nextTurn() {

    }

    private fun nextRound() {
        field.clearField()
    }

    interface Message {
        fun send(chatId: Long, message: String, replyMarkup: ReplyMarkup)
    }
}