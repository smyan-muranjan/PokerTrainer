package com.smyanmuranjan.pokertrainer

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smyanmuranjan.pokertrainer.ui.theme.PokerTrainerTheme
import com.smyanmuranjan.pokertrainer.viewmodels.MainViewModel
import com.smyanmuranjan.pokertrainer.views.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val vm: MainViewModel by viewModels()
        setContent {
            PokerTrainerTheme {
                App(vm)
            }
        }
    }
}