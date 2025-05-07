package com.smyanmuranjan.pokertrainer.viewmodels

import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.smyanmuranjan.pokertrainer.data.Game
import com.smyanmuranjan.pokertrainer.data.Player
import com.smyanmuranjan.pokertrainer.data.States

class MainViewModel : ViewModel() {
    var game = mutableStateOf<Game?>(null)
        private set
    var numPlayers = mutableIntStateOf(6)
        private set
    var players = mutableStateListOf<Player>()
        private set
    var dealer = mutableIntStateOf(0)
        private set
    var state = mutableStateOf<States>(States.START)
        private set

    fun setName(newName: String, index: Int) {
        val oldPlayer = players[index]
        players[index] = oldPlayer.copy(name = newName)
    }

    fun setupConfig(newNumPlayers: Float) {
        numPlayers.intValue = newNumPlayers.toInt()
        for (i in 1..numPlayers.intValue) {
            players.add(
                Player(
                    chips = 1000,
                    name = "",
                    cards = null
                )
            )
        }
        state.value = States.CONFIG
    }

    fun setDealer(i: Int) {
        dealer.intValue = i
    }
}
