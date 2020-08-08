package ru.krivocraft.gwentbot.core

import me.ivmg.telegram.entities.ReplyKeyboardRemove
import me.ivmg.telegram.entities.ReplyMarkup
import ru.krivocraft.gwentbot.core.model.Deck
import ru.krivocraft.gwentbot.core.model.Field
import ru.krivocraft.gwentbot.core.model.Player
import kotlin.random.Random

class Match(
    val players: List<Player>,
    private val message: Message,
    private val field: Field = Field()
) {
    private val decks = mutableListOf<Deck>()
    private var currentPlayer = Random.nextInt(0, 1)
    private var scores = mutableListOf<Int>(0, 0)
    private var passes = mutableListOf<Boolean>(false, false)
    init {
        players.forEachIndexed { index, player ->
            notifyPlayer("Игра найдена", player, ReplyKeyboardRemove())
            player.deck.cards.shuffle()
            decks.add(player.deck)
            field.fillPool(player.deck)
            notifyPlayer("Вы игрок " + index + 1, player, ReplyKeyboardRemove())
            notifyPlayer(field.poolReport(index), player, ReplyKeyboardRemove())
        }
        nextRound()
        nextTurn()
    }

    private fun notifyPlayer(text: String, player: Player, replyMarkup: ReplyMarkup) {
        message.send(player.chatId, text, replyMarkup = replyMarkup)
    }

    private fun notifyAllPlayers(text: String, replyMarkup: ReplyMarkup) {
        message.send(players[0].chatId, text, replyMarkup = replyMarkup)
        message.send(players[1].chatId, text, replyMarkup = replyMarkup)
    }

    private fun nextTurn() {
        if (!passes[1 - currentPlayer]) {
            currentPlayer = 1 - currentPlayer
        }
        else {
            if (passes[currentPlayer]) {
                nextRound()
            }
        }

    }

    private fun nextRound() {
        val outcome = field.outcome()
        scores[0]+=outcome[0]
        scores[1]+=outcome[1]
        if (outcome[0] == 1) {
            if (outcome[1] == 1) {
                notifyAllPlayers("Ничья", ReplyKeyboardRemove())
            } else {
                notifyAllPlayers("Победил игрок 1", ReplyKeyboardRemove())
            }
        } else {
            notifyAllPlayers("Победил игрок 2", ReplyKeyboardRemove())
        }
        field.clearField()
    }

}