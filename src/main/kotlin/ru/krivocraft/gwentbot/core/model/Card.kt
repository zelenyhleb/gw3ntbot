package ru.krivocraft.gwentbot.core.model

interface Card {
    val properties: MutableList<CardProperty>
    val name: String
    fun use(field: Field)
}