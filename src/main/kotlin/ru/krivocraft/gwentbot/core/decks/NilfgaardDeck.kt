package ru.krivocraft.gwentbot.core.decks

import ru.krivocraft.gwentbot.core.model.Card
import ru.krivocraft.gwentbot.core.model.Deck

class NilfgaardDeck(override val cards: MutableList<Card>) : Deck {

    override fun faction(): String {
        return "Nilfgaard"
    }
}