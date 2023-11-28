package com.example.confirmatio.testsSystem

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths

class TestLoader(
   val test_id : TestID,
    val context : Context
) {

    fun getQuestionsCount() : Int {
        val bufferedReader = context.assets.open("test${test_id.id}.csv").bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
        return Integer.parseInt(csvParser.first().get(2))
    }
    fun loadQuestions() : List<Question> {
        when(test_id) {
            TestID.STAI ->
                return parseCSV()
            else ->
                return listOf(Question("Question # 1 text",
                    listOf("Option 1","Option 2","Option 3","Option 4")),
                    Question("Question # 2 text",
                        listOf("Option 1","Option 2","Option 3","Option 4")),
                    Question("Question # 3 text",
                        listOf("Option 1","Option 2","Option 3","Option 4")),
                    Question("Question # 4 text",
                        listOf("Option 1","Option 2","Option 3","Option 4")))

        }



    }

    fun parseCSV() : List<Question> {
        var list_q : List<Question> = emptyList()
        val bufferedReader = context.assets.open("test${test_id.id}.csv").bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
        for(csvRec in csvParser) {
            if(csvRec.get(0) != test_id.id.toString()) {
                list_q = list_q.plus(Question(csvRec.get(0), listOf(csvRec.get(1),csvRec.get(2),csvRec.get(3),csvRec.get(4))))
            }
        }
        return list_q
    }

    fun getTestName(): String {
        val bufferedReader = context.assets.open("test${test_id.id}.csv").bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
        return csvParser.first().get(1)
    }
    companion object {
        fun getTestName(id : Int, context : Context): String {
            val bufferedReader = context.assets.open("test${id}.csv").bufferedReader()
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
            return csvParser.first().get(1)
        }
        fun getTestDescription(id : Int, context : Context): String {
            val bufferedReader = context.assets.open("test${id}.csv").bufferedReader()
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
            return csvParser.first().get(5)
        }
        fun getInfo(id : Int, context : Context): String {
            val bufferedReader = context.assets.open("test${id}.csv").bufferedReader()
            val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
            return csvParser.first().get(4)
        }
    }
}