package ru.krivocraft.gwentbot.core

import ru.krivocraft.gwentbot.core.model.Player

class MatchMaking(private val message: Message) {

    private val matches = mutableListOf<Match>()
    private val queue = mutableListOf<Player>()
    private val playing = mutableListOf<Player>()

    fun addPlayer(player: Player) {
        queue.add(player)
        tryStart()
    }

    fun isPlaying(player: Player): Boolean {
        return playing.contains(player)
    }

    fun getMatch(player: Player): Match {
        matches.iterator().forEach {
            if (it.players[0] == player && it.players[1] == player) {
                return it
            }
        }
        throw Exception("no session found")
    }

    private fun tryStart() {
        if (queue.size > 1) {
            startSession(queue[0], queue[1]) // Taking first two players
        }
    }

    private fun startSession(player1: Player, player2: Player) {
        val players = listOf(player1, player2)

        queue.removeAll(players)
        playing.addAll(players)

        val match = Match(players, message)
        matches.add(match)
    }
}