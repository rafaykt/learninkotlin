package com.example.convidados.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.R
import com.example.convidados.Service.Constants.GuestConstants
import com.example.convidados.Service.Model.GuestModel
import com.example.convidados.ViewModel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

/*Fazer os set listeners*/
// 1 - Add set listeners
//add setListeners no On create
//extender View.OnClickListeners
//implementar a função setListeners e override na fun onClick, pois a onclicklistener é uma interface
//criar o lateinit mViewModel do tipo GuestFormViewModel
//ele ser o Provider.get
class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        radio_presente.isChecked=true
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.submit_button) {
            val name = edit_name.text.toString()
            val presence = radio_presente.isChecked
            mViewModel.save(mGuestId, name, presence)
        }
    }

    private fun observe(){
        mViewModel.saveGuest.observe(this, Observer{
            if(it){
                Toast.makeText(applicationContext, "Sucesso!!", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(applicationContext, "Falha!!", Toast.LENGTH_SHORT).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer{
            edit_name.setText(it.name)
            if(it.presence){
                radio_presente.isChecked= true
            }else{
                radio_ausente.isChecked=true
            }
        })
    }

    private fun setListeners() {
        submit_button.setOnClickListener(this)
    }

    private fun loadData() {
        val bundle = intent.extras
        if(bundle != null){
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }
    }
}