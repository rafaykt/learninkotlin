package com.example.convidados.Service.Repository

import androidx.room.*
import com.example.convidados.Service.Model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update( guest: GuestModel) : Int

    @Delete
    fun delete (id: Int)

    @Query("Select * FROM Guest WHERE id = :id")
    fun get(id: Int) : GuestModel

    @Query("SELECT * FROM Guest")
    fun getInvited() : List<GuestModel>

    @Query( "SELECT * FROM Guest WHERE presence= 1")
    fun getPresent() : List<GuestModel>

    @Query( "SELECT * FROM Guest WHERE presence= 0")
    fun getAbsent() : List<GuestModel>
}