package com.example.convidados.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.convidados.Listeners.GuestListener
import com.example.convidados.R
import com.example.convidados.Service.Constants.GuestConstants
import com.example.convidados.View.adapter.GuestAdapter
import com.example.convidados.ViewModel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_all.*

class AllGuestsFragment : Fragment() {

    private lateinit var mViewModel: AllGuestsViewModel
    private lateinit var mListener: GuestListener
    private val mAdapter: GuestAdapter = GuestAdapter()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mViewModel =
                ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //Recycler View - Passos
        // 1 - Obter a recycler
        // 2 - definir um layout
        // 3 - Definir um adapter


        //1 -
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)


        //2 -
        recycler.layoutManager = LinearLayoutManager(context)

        //3 -
        //Criar uma classe chamada GuestAdapter (esta dentro do pacote adapter)
        //Dentro do Guest Adapter, precisou ser criada uma classe do tipo ViewHolder, que está no package viewholder
        recycler.adapter = mAdapter

        //Ir no all guest view model
        //depois criar o observer que vai ver a lista do all guest view model



        mListener = object: GuestListener{
            override fun onClick(id: Int) {
                //abrir a tela com os dados
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load()
            }

        }
        mAdapter.attachListener(mListener)
        observer()
        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load()
    }

    private fun observer(){
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}