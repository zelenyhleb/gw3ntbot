package ru.krivocraft.gwentbot.core.model

class Pool : Reportable{

    val cards: MutableList<Card> = mutableListOf()

    override fun report(): String {
        var report = ""
        cards.forEach() {
            report += it.name
            if (it is Unit) {
                report += " " + it.power
            }
            report += "\n"
        }
        return report
    }
}