package com.example.filemanipulator.service.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.example.filemanipulator.service.model.Funcionario
import kotlinx.coroutines.launch
import java.io.*

class FileManager() {

    suspend fun readFuncionariosFromFile(uri: Uri, context: Context): List<Funcionario> {
        val contentResolver = context.contentResolver
        val funcionarioList = arrayListOf<Funcionario>()
        contentResolver.openInputStream(uri)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream, "ISO-8859-1")).use { reader ->
                var line: String? = reader.readLine()
                if(line != null){
                    var splitterFirst = line.split(";")
                    val funcionarioFirst = Funcionario(
                        splitterFirst[0],
                        splitterFirst[1],
                        splitterFirst[2],
                        splitterFirst[3],
                        splitterFirst[4]
                    )
                    funcionarioList.add(funcionarioFirst)
                }
                var splitter: List<String>
                while (line != null) {
                    line = reader.readLine()

                    if (line != null) {
                        splitter = line.split(";")
                        val funcionario = Funcionario(
                            splitter[0],
                            splitter[1],
                            splitter[2],
                            splitter[3],
                            splitter[4]
                        )
                        funcionarioList.add(funcionario)
                    }
                }
                reader.close()
            }
        }
        return funcionarioList
    }


    suspend fun writeFuncionariosIntoFile(context: Context, listaFuncionarios: List<Funcionario> ){

        val contentResolver = context.contentResolver
        val funcionarioList = arrayListOf<Funcionario>()
        val fileOutputStream: FileOutputStream


        try{
            val file = FileWriter(context.getFileStreamPath("output_desafio.txt").toString())
            listaFuncionarios.forEach {
                file.write("${it.codFuncionario};${it.descFuncionario};${it.complemento};${it.reservado1};${it.reservado2}\n")
            }
            file.close()
        }catch(e: Exception){
            e.printStackTrace()
        }

    }

}