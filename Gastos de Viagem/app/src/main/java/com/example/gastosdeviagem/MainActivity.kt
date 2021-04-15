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

        botaoCalcular.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var id = view.id
        if(id == R.id.botaoCalcular){
            calculate()
        }
    }

    private fun calculate(){
        if(validation()) {
            try {
                val precoCombustivel = price_gas.text.toString().toFloat()
                val autonomia = autonomy.text.toString().toFloat()
                val distanciaTotal = distance_total.text.toString().toFloat()
                val valorFinal = (distanciaTotal * precoCombustivel) / autonomia
                final_value.text = "R$ ${"%.2f".format(valorFinal)}"
            } catch(nfe: NumberFormatException){
                Toast.makeText(this, getString(R.string.toast_informar_valor), Toast.LENGTH_LONG).show()
            }

        }
        Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show()
    }

    private fun validation() : Boolean{
        return (distance_total.text.toString() != "" && price_gas.text.toString() != "" && autonomy.text.toString() != "" &&  autonomy.text.toString() != "0")


    }

}