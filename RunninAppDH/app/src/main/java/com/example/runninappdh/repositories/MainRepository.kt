package com.example.runninappdh.repositories

import com.example.runninappdh.db.Run
import com.example.runninappdh.db.dao.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDAO
){
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByCalories() = runDao.getAllRunsSortedByCalories()

    fun getAllRunsSortedBySpeed() = runDao.getAllRunsSortedBySpeed()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalCaloriesBurnt() = runDao.getTotalCaloriesBurnt()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()



}