package ru.krivocraft.gwentbot.core.model

class Field {

    private val pools = mutableListOf<Pool>()
    private val eliminated = mutableListOf(Pool(), Pool())
    private val poolSize = 10
    private val lines: List<Line> = List(6) { Line() }

    fun fillPool(deck: Deck) {
        val pool = Pool()
        for (i in 0 until poolSize) {
            pool.cards.add(deck.cards[i])
        }
        pools.add(pool)
    }

    fun clearField() {
        for (i in 0 until 6) {
            if (i < 3) {
                lines[i].clearLine(0)
            } else {
                lines[i].clearLine(1)
            }
        }
    }

    inner class Line {
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
        fun clearLine(playerNumber: Int) {
            cards.forEach() {
                pushUnit(it)
                eliminated[playerNumber].cards.add(it)
            }
        }

    }
}