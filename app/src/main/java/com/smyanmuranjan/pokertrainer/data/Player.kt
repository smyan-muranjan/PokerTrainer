package com.smyanmuranjan.pokertrainer.data

data class Player(
    val chips: Int,
    val cards: Pair<Card, Card>?,
    val name: String
)
