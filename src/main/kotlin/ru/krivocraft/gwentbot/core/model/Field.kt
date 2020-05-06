package ru.krivocraft.gwentbot.core.model

class Field {

    private val lines: List<Line> = List(6) { Line() }

    fun add(player: Player, unit: Unit) {
        //Not yet implemented
    }

    fun toMessage(): String {
        //TODO: Implement field status showing
        return "field status"
    }

    class Line {
        private val cards = mutableListOf<Unit>()

        var power = 0

        fun putCard(card: Unit) {
            cards.add(card)
            power += card.power
        }
    }
}