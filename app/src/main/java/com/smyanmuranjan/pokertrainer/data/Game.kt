package com.smyanmuranjan.pokertrainer.data

data class Game(
    val players: List<Player>,
    val pot: Int,
    val communityCards: List<Card>,
    val dealerIndex: Int
)
