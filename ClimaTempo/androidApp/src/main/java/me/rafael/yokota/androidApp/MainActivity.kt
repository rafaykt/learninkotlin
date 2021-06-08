package me.rafael.yokota.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.rafael.yokota.shared.viewmodel.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
