package com.example.jdd_healthcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jdd_healthcounter.ui.theme.JDD_HealthCounterTheme
import com.example.jdd_healthcounter.model.Player

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JDD_HealthCounterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HealthCounterApp()
                }
            }
        }
    }
}

@Composable
fun HealthCounterApp(){
    val players = remember {
        mutableStateListOf(
            Player("Player1"),
            Player("Player2"),
            Player("Player3"),
            Player("Player4")
        )
    }



    Column {
        PlayerList(players = players)
        Button(onClick = {
            val newPlayerNumber = players.size + 1
            if (players.size < 10) {players.add(Player(name = "Player$newPlayerNumber"))}
        }){
            Text(text = "Agregar Jugador")
        }
    }

}


@Composable
fun PlayerList(players: List<Player>, modifier: Modifier = Modifier){
    for (player in players) {
        PlayerDisplay(player = player, modifier = modifier.padding(8.dp))
    }
}

@Composable
fun PlayerDisplay(player: Player, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = "${player.name}, Salud: ${player.health}, Vivo: ${player.isAlive}",
            modifier = modifier
        )
        Button(onClick = { player.increaseHealth() }) {
            Text(text = "+")

        }
        Button(onClick = { if (player.health > 0) {player.decreaseHealth()} }) {
            Text(text = "-")

        }
        Button(onClick = {player.eliminated = true}) {
            Text(text = "x")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HealthCounterAppPreview() {
    HealthCounterApp()
}