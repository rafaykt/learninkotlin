package com.example.filemanipulator.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.filemanipulator.service.model.Funcionario
import com.example.filemanipulator.service.repository.FuncionarioRepository
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val contentResolver = application.contentResolver
    private val mRepository = FuncionarioRepository(application)

    private val _text = MutableLiveData<String>().apply {

        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    fun readFile (uri: Uri): String{

        val stringBuilder = StringBuilder()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream, "ISO-8859-1")).use { reader ->
                var line: String? = reader.readLine()
                var splitter: List<String>
                while (line != null) {
                    stringBuilder.append(line)
                    line = reader.readLine()
                    if (line != null) {
                        splitter = line.split(";")
                        println("ID: ${splitter[0]} Nome: ${splitter[1]}")
                        viewModelScope.launch {
                            mRepository.save(
                                Funcionario(
                                    splitter[0].toLong(),
                                    splitter[1],
                                    splitter[2],
                                    splitter[3],
                                    splitter[4]
                                )
                            )
                        }
                    }
                }
            }
        }
        return stringBuilder.toString()
    }
}