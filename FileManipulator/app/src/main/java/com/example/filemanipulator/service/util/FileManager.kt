package com.example.filemanipulator.service.util

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.filemanipulator.service.model.Funcionario
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

class FileManager() {

    suspend fun readFuncionariosFromFile(uri: Uri, context: Context): List<Funcionario> {
        val contentResolver = context.contentResolver
        val funcionarioList = arrayListOf<Funcionario>()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream, "ISO-8859-1")).use { reader ->
                var line: String? = reader.readLine()
                var splitter: List<String>
                while (line != null) {
                    line = reader.readLine()
                    if (line != null) {
                        splitter = line.split(";")
                        val funcionario = Funcionario(
                            splitter[0].toLong(),
                            splitter[1],
                            splitter[2],
                            splitter[3],
                            splitter[4]
                        )
                        funcionarioList.add(funcionario)
                    }
                }
            }
        }
        return funcionarioList
    }


}