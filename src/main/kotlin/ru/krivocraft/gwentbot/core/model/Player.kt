package ru.krivocraft.gwentbot.core.model

import ru.krivocraft.gwentbot.core.dsl.DeckProvider

data class Player(val chatId: Long) {
    val deck: Deck = DeckProvider().default()
}