package ru.krivocraft.gwentbot.core.model

interface SpecialCard : Card {

    override fun use(field: Field) {
        //addCardToField(line)
    }
}