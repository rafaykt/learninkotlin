package com.example.convidados.Service.Repository

import com.example.convidados.Service.Model.GuestModel
import java.util.ArrayList


class GuestRepository {


    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun save(guest: GuestModel) {

    }

    fun update( guest: GuestModel){

    }

    fun delete (guest: GuestModel){

    }


}