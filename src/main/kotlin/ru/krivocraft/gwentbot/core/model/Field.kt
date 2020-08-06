package ru.krivocraft.gwentbot.core.model

class Field {

    private val lines: List<Line> = List(6) { Line() }
    private val pools = mutableListOf<Pool>()
    private val eliminated = mutableListOf<Pool>()
    private val poolSize = 10

    fun fillPool(deck: Deck) {
        val pool = Pool()
        for (i in 0..poolSize - 1) {
            pool.cards.add(deck.cards[i])
        }
        pools.add(pool)
    }

    class Line {
        private val cards = mutableListOf<Unit>()

        var power = 0

        fun putUnit(card: Unit) {
            cards.add(card)
            power += card.power
        }
        fun pushUnit(card: Unit) {
            cards.remove(card)
            power -= card.power
        }
    }
}