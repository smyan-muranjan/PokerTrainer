package com.smyanmuranjan.pokertrainer.data

data class Player(
    var chips: Int,
    var cards: Pair<Card, Card>?,
    var name: String
)
