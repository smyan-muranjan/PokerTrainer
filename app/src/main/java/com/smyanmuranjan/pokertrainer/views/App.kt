package com.smyanmuranjan.pokertrainer.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
            States.CONFIG -> ConfigScreen(vm)
            States.PLAY -> PlayScreen(vm)
        }
    }
}

@Composable
fun ConfigScreen(vm: MainViewModel) {
    val (selectedOption, onOptionSelected) = remember { mutableIntStateOf(0) }
    Text(text = "Enter Player Names and Select Dealer: ")
    vm.players.forEachIndexed { i, player ->
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Player ${i + 1}")
                TextField(
                    value = player.name,
                    onValueChange = { vm.setName(it, i) },
                    label = { Text(text = "Name") }
                )
            }
            RadioButton(
                selected = (vm.dealer.intValue == i),
                onClick = { vm.setDealer(i) }
            )
        }
    }
}

@Composable
fun PlayScreen(vm: MainViewModel) {
    Text(text = "Play game")
}

@Composable
private fun StartScreen(vm: MainViewModel) {

    var numPlayers by remember { mutableFloatStateOf(6F) }

    Text(text = "Select Number of Players: ")
    Slider(
        value = numPlayers,
        onValueChange = { numPlayers = it },
        steps = 7,
        valueRange = 2f..10f,
        modifier = Modifier.padding(horizontal = 50.dp)
    )
    Text(
        text = numPlayers.toInt().toString()
    )
    Button(
        onClick = { vm.setupConfig(numPlayers) }
    ) {
        Text("Continue")
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
