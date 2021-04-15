package com.example.gastosdeviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkboxTint.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if(id == R.id.checkboxTint ){
            trocarCor()
        }
    }

    private fun trocarCor(){
        if(checkboxTint.isChecked) {
            icone_bt.setColorFilter(resources.getColor(R.color.vermelho))
        }else{
            icone_bt.setColorFilter(resources.getColor(R.color.cinza))
        }
    }
}