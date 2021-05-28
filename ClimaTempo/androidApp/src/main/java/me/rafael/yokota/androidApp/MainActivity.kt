package me.rafael.yokota.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.rafael.yokota.shared.Greeting
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.text_view)


        greet(tv)

    }

    fun greet(tview: TextView) {
        var resp = ""
            lifecycleScope.launch(Dispatchers.IO){
                 tview.text = Greeting().greeting(-21.2115F,-50.4261F)
            }

    }

}