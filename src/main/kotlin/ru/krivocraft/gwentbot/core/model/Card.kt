package ru.krivocraft.gwentbot.core.model

interface Card {
    val properties: MutableList<CardProperty>
    fun use(field: Field)
}