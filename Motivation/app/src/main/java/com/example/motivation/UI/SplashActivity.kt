package com.example.motivation.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var mSecurityPreferences : SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)
    }


    override fun onClick( view :View){
        var id = view.id
        if (id == R.id.buttonSave){
            handleSave()
        }
    }

    private fun handleSave(){
        val nome = inputNome.text.toString()
        if(nome == "") {
            Toast.makeText(this, "O nome está vazio", Toast.LENGTH_SHORT).show()
        }else{
            mSecurityPreferences.storeString("nome", nome)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
        }
    }

}