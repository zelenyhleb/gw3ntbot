@file:JvmName("Main")

package ru.krivocraft.gwentbot

import me.ivmg.telegram.bot
import me.ivmg.telegram.dispatch
import me.ivmg.telegram.dispatcher.callbackQuery
import me.ivmg.telegram.dispatcher.command
import me.ivmg.telegram.dispatcher.text
import me.ivmg.telegram.entities.InlineKeyboardButton
import me.ivmg.telegram.entities.InlineKeyboardMarkup
import me.ivmg.telegram.entities.ReplyMarkup
import okhttp3.logging.HttpLoggingInterceptor
import ru.krivocraft.gwentbot.core.Match
import ru.krivocraft.gwentbot.core.MatchMaking
import ru.krivocraft.gwentbot.core.model.Player

fun main(args: Array<String>) {
    val bot = bot {
        token = args[0]
        logLevel = HttpLoggingInterceptor.Level.BODY
        dispatch {
            val matchmaking = MatchMaking(object : Match.Message {
                override fun send(chatId: Long, message: String, replyMarkup: ReplyMarkup) {
                    bot.sendMessage(chatId = chatId, text = message)
                }
            })
            command("start") { bot, update ->
                val buttons = listOf(
                    listOf(InlineKeyboardButton(text = "Меня дважды просить не нужно", callbackData = "findMatch")),
                    listOf(InlineKeyboardButton(text = "Я с кем попало в Гвинт не играю", callbackData = "iAmABore"))
                )
                val inlineKeyboardMarkup = InlineKeyboardMarkup(buttons)
                bot.sendMessage(
                    chatId = update.message!!.chat.id,
                    text = "Партейку в Гвинт?",
                    replyMarkup = inlineKeyboardMarkup
                )
            }
            callbackQuery("findMatch") { bot, update ->
                update.callbackQuery?.let {
                    val chatId = it.message?.chat?.id ?: return@callbackQuery
                    bot.sendMessage(chatId = chatId, text = "Ищем подходящего игрока")
                    matchmaking.addPlayer(Player(chatId = update.message!!.chat.id))
                }
            }
            callbackQuery("iAmABore") { bot, update ->
                update.callbackQuery?.let {
                    val chatId = it.message?.chat?.id ?: return@callbackQuery
                    val buttons = listOf(
                        listOf(InlineKeyboardButton(text = "Ладно", callbackData = "findMatch"))
                    )
                    val inlineKeyboardMarkup = InlineKeyboardMarkup(buttons)
                    bot.sendMessage(
                        chatId = chatId,
                        text = "Ты просто боишься проиграть",
                        replyMarkup = inlineKeyboardMarkup
                    )
                }
            }
            text { bot, update ->
                val chatId = update.message!!.chat.id


            }
            callbackQuery("putCard") { bot, update ->
                update.callbackQuery?.let {
                    val chatId = it.message?.chat?.id ?: return@callbackQuery

                }
            }

        }
    }
    bot.startPolling()

}