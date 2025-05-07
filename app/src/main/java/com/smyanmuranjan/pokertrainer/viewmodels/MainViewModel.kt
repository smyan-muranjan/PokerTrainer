package com.smyanmuranjan.pokertrainer.viewmodels

import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.smyanmuranjan.pokertrainer.data.Game
import com.smyanmuranjan.pokertrainer.data.Player
import com.smyanmuranjan.pokertrainer.data.States

class MainViewModel : ViewModel() {
    var game = mutableStateOf<Game?>(null)
        private set
    var numPlayers = mutableFloatStateOf(6F)
        private set
    var dealer = mutableIntStateOf(0)
        private set
    var state = mutableStateOf<States>(States.START)
        private set

    fun setNumPlayers(newNumPlayers: Float) {
        numPlayers.floatValue = newNumPlayers
    }

    fun setDealer(newDealer: Int) {
        dealer.intValue = newDealer
    }

    fun startGame() {
        state.value = States.PLAY

        val n = numPlayers.floatValue.toInt()
        val players: MutableList<Player> = mutableListOf()
        for (i in 1..n) {
            players.add(
                Player(
                    chips = 1000,
                    cards = null,
                    name = i.toString()
                )
            )
        }

    }
}
