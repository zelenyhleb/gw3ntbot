package ru.krivocraft.gwentbot.core.model

class Unit(val power: Int, val line: Int, override val properties: MutableList<CardProperty>, override val name: String) : Card {

    override fun use(field: Field) {
        //addCardToField(line)
    }
}