package ru.krivocraft.gwentbot.core.storage

interface Serializable {

    fun toJson(): String

    fun fromJson(): Serializable
}