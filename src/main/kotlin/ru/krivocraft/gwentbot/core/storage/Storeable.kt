package ru.krivocraft.gwentbot.core.storage

import java.io.File

interface Storeable {

    fun write(file: File)

    fun read(file: File): Storeable
}