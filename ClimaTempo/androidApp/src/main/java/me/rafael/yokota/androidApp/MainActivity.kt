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
    private val job = Job()
    private val scopeIo = CoroutineScope(job + Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val tv: TextView = findViewById(R.id.text_view)
        lifecycleScope.launch{
            println(Thread.currentThread().name)
//            tv.text = Greeting().greeting(-21.2115F,-50.4261F)
        }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navhost) as NavHostFragment
//        val navController = navHostFragment.navController
//        setupActionBarWithNavController(navController)

    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}
