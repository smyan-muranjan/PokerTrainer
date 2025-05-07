package com.smyanmuranjan.pokertrainer.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smyanmuranjan.pokertrainer.data.States
import com.smyanmuranjan.pokertrainer.viewmodels.MainViewModel


@Composable
fun App(vm: MainViewModel) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(vm.state.value) {
            States.START -> StartScreen(vm)
            States.PLAY -> PlayScreen(vm)
        }
    }
}

@Composable
fun PlayScreen(vm: MainViewModel) {
    Text(text = "Play game")
}

@Composable
private fun StartScreen(vm: MainViewModel) {

    var expanded by remember { mutableStateOf(false) }
    Text(text = "Select Number of Players: ")
    Slider(
        value = vm.numPlayers.floatValue,
        onValueChange = { vm.setNumPlayers(it) },
        steps = 7,
        valueRange = 2f..10f,
        modifier = Modifier.padding(horizontal = 50.dp)
    )
    Text(
        text = vm.numPlayers.floatValue.toInt().toString()
    )
    Text(
        text = "Choose Dealer: "
    )
    DealerChipsGrid(
        numPlayers = vm.numPlayers.floatValue.toInt(),
        dealer = vm.dealer.value,
        onDealerChange = { vm.setDealer(it) } )
    Button(
        onClick = { vm.startGame() }
    ) {
        Text("Start Game")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DealerChipsGrid(
    numPlayers: Int,
    dealer: Int,
    onDealerChange: (Int) -> Unit
) {
    // create a list [1, 2, …, numPlayers] then split into rows of 4
    val rows = (1..numPlayers).toList().chunked(4)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        rows.forEach { rowSeats ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                rowSeats.forEach { seat ->
                    FilterChip(
                        selected = (seat == dealer),
                        onClick = { onDealerChange(seat) },
                        label = { Text("Seat $seat") }
                    )
                }
            }
        }
    }
}
