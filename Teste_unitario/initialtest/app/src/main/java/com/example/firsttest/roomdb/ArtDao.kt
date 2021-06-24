package com.example.firsttest.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ArtDao {

    suspend fun getArts() : List<ArtModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: ArtModel)

    @Delete
    suspend fun deleteArt(art: ArtModel)

    fun observeArts (): LiveData<List<ArtModel>>






}