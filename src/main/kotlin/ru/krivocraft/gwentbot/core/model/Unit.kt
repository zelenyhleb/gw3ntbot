package ru.krivocraft.gwentbot.core.model

class Unit(val power: Int, val line: Int) : Card {

    override fun use(field: Field) {
        //addCardToField(line)
    }
}