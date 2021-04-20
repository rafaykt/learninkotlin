package com.example.convidados.ui.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.ViewModel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form2.*

/*Fazer os set listeners*/
// 1 - Add set listeners
//add setListeners no On create
//extender View.OnClickListeners
//implementar a função setListeners e override na fun onClick, pois a onclicklistener é uma interface
//criar o lateinit mViewModel do tipo GuestFormViewModel
//ele ser o Provider.get
class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mViewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form2)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
    }

    private fun setListeners() {
        submit_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.submit_button){

        }
    }
}