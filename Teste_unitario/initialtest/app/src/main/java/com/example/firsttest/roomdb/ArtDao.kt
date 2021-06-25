package com.example.firsttest.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: ArtModel)

    @Delete
    suspend fun deleteArt(art: ArtModel)

    @Query("SELECT * FROM arts")
    fun observeArts (): LiveData<List<ArtModel>>






}