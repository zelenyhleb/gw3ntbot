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

    fun getOutcome(): List<Int> {
        val firstPlayerResult = lines[0].power + lines[1].power + lines[2].power
        val secondPlayerResult = lines[3].power + lines[4].power + lines[5].power
        val outcome = mutableListOf(0, 0)
        if (firstPlayerResult >= secondPlayerResult) {
            outcome[0] = 1
        }
        if (firstPlayerResult <= secondPlayerResult) {
            outcome[1] = 1
        }
        return outcome
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