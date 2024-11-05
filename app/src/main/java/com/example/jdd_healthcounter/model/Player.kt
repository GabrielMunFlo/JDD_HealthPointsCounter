package com.example.jdd_healthcounter.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Player (var name: String,health: Int = 100) {
    var health by mutableStateOf(health) // Ahora es observable
        private set

    val isAlive: Boolean
        get() = health > 0

    fun increaseHealth() {
        health++
    }
    fun decreaseHealth() {
        health--
    }
}