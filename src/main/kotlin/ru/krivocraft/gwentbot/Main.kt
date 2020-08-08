@file:JvmName("Main")

package ru.krivocraft.gwentbot

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.callbackQuery
import me.ivmg.telegram.dispatcher.command
import me.ivmg.telegram.entities.*
import okhttp3.logging.HttpLoggingInterceptor
import ru.krivocraft.gwentbot.core.MatchMaking
import ru.krivocraft.gwentbot.core.Message
import ru.krivocraft.gwentbot.core.model.Player
import ru.krivocraft.gwentbot.core.telegram.Handler

fun main(args: Array<String>) {
    val bot = bot {
        token = args[0]
        logLevel = HttpLoggingInterceptor.Level.BODY
        val handlers = hashMapOf<String, Handler>()
        dispatch {
            val messageDispatcher = object : Message {
                override fun send(chatId: Long, message: String, replyMarkup: ReplyMarkup) {
                    bot.sendMessage(chatId = chatId, text = message)
                }
            }
            val matchmaking = MatchMaking(messageDispatcher)
            command("start") { _, update ->
                handlers[update.message!!.toString()]!!.handle(messageDispatcher, update.message!!.chat.id)
            }
            command("Меня дважды просить не нужно") { _, update ->
                handlers[update.message!!.toString()]!!.handle(messageDispatcher, update.message!!.chat.id)
            }
            handlers["Меня дважды просить не нужно"] = object : Handler {
                override fun handle(message: Message, chatId: Long) {
                    message.send(chatId, "Ищем подходящего игрока", ReplyKeyboardRemove())
                    matchmaking.addPlayer(Player(chatId = chatId))
                }
            }
            handlers["start"] = object : Handler {
                override fun handle(message: Message, chatId: Long) {
                    val buttons = listOf(
                        listOf(KeyboardButton(text = "Меня дважды просить не нужно"))
                    )
                    val inlineKeyboardMarkup = KeyboardReplyMarkup(buttons)
                    message.send(chatId, "Партейку в гвинт?", inlineKeyboardMarkup)
                }
            }

        }
    }
    bot.startPolling()

}