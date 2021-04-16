package com.example.motivation.UI

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.Infra.MotivationConstants
import com.example.motivation.Infra.SecurityPreferences
import com.example.motivation.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }
        mSecurityPreferences = SecurityPreferences(this);
        textName.text = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        buttonNewPhrase.setOnClickListener(this)
        imageMorning.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)
        if(id == R.id.buttonNewPhrase){
         handleNewPhrase()
        }else if (id in listFilter){
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int ){

        imageAll.setColorFilter((resources.getColor(R.color.white)))
        imageMorning.setColorFilter((resources.getColor(R.color.white)))
        imageHappy.setColorFilter((resources.getColor(R.color.white)))
        when(id){
            R.id.imageAll -> {
                imageAll.setColorFilter((resources.getColor(R.color.colorAccent)))
            }
            R.id.imageHappy -> {
                imageHappy.setColorFilter((resources.getColor(R.color.colorAccent)))
            }
            R.id.imageMorning -> {
                imageMorning.setColorFilter((resources.getColor(R.color.colorAccent)))
            }
        }
    }

    private fun handleNewPhrase(){

    }
}