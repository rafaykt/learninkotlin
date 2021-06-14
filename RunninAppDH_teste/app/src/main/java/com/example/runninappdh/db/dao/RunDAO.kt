package com.example.runninappdh.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.runninappdh.db.Run

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun( run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY running_table.timeStamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY running_table.timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY running_table.avgSpeed DESC")
    fun getAllRunsSortedBySpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY running_table.caloriesBurnt DESC")
    fun getAllRunsSortedByCalories(): LiveData<List<Run>>

    @Query("SELECT SUM (timeInMillis) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurnt) FROM running_table")
    fun getTotalCaloriesBurnt(): LiveData<Long>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT SUM(avgSpeed) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>

}