package com.example.runninappdh.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runninappdh.db.Run
import com.example.runninappdh.other.SortType
import com.example.runninappdh.repositories.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
    private val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runsSortedByCalories = mainRepository.getAllRunsSortedByCalories()
    private val runsSortedByTime = mainRepository.getAllRunsSortedByTimeInMillis()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedBySpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runsSortedByDate) { result ->
            if(sortType == SortType.DATE){
                result?.let {
                    runs.value = it
                }
            }

        }
        runs.addSource(runsSortedByAvgSpeed) { result ->
            if(sortType == SortType.AVG_SPEED){
                result?.let {
                    runs.value = it
                }
            }

        }
        runs.addSource(runsSortedByCalories) { result ->
            if(sortType == SortType.CALORIES_BURNED){
                result?.let {
                    runs.value = it
                }
            }

        }
        runs.addSource(runsSortedByDistance) { result ->
            if(sortType == SortType.DISTANCE){
                result?.let {
                    runs.value = it
                }
            }

        }
        runs.addSource(runsSortedByTime) { result ->
            if(sortType == SortType.RUNNING_TIME){
                result?.let {
                    runs.value = it
                }
            }

        }

    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runsSortedByTime.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCalories.value?.let { runs.value = it }
    }.also{
        this.sortType=  sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch(Dispatchers.IO) {
        mainRepository.insertRun(run)
    }


}