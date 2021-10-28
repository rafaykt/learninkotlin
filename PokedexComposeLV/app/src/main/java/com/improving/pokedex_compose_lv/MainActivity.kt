package com.improving.pokedex_compose_lv

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController

import com.improving.pokedex_compose_lv.ui.theme.JetpackComposePokedexTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            JetpackComposePokedexTheme {
                val navController = rememberNavController()

            }
        }
    }


}