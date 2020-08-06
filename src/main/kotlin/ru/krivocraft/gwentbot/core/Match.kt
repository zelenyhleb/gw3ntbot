package ru.krivocraft.gwentbot.core

import me.ivmg.telegram.entities.ReplyKeyboardRemove
import me.ivmg.telegram.entities.ReplyMarkup
import ru.krivocraft.gwentbot.core.model.Deck
import ru.krivocraft.gwentbot.core.model.Field
import ru.krivocraft.gwentbot.core.model.Player
import ru.krivocraft.gwentbot.core.model.Pool
import kotlin.random.Random

class Match(
    val players: List<Player>,
    private val message: Message,
    private val field: Field = Field()
) {
    private val decks = mutableListOf<Deck>()
    private var currentPlayer = Random.nextInt(0, 1)
    private var scores = mutableListOf<Int>(0, 0)
    init {
        players.forEach {
            notifyPlayer("Игра найдена", it, ReplyKeyboardRemove())
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
        currentPlayer = 1 - currentPlayer
    }

    private fun nextRound() {
        val outcome = field.getOutcome()
        scores[0]+=outcome[0]
        scores[1]+=outcome[1]
        field.clearField()
    }

    interface Message {
        fun send(chatId: Long, message: String, replyMarkup: ReplyMarkup)
    }
}