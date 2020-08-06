package ru.krivocraft.gwentbot.core.dsl

import ru.krivocraft.gwentbot.core.decks.NilfgaardDeck
import ru.krivocraft.gwentbot.core.model.Card
import ru.krivocraft.gwentbot.core.model.Deck

class DeckProvider {

    fun default() : Deck {
        return NilfgaardDeck(mutableListOf())
    }
}