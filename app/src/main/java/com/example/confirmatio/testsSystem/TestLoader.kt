package com.example.confirmatio.testsSystem

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.nio.file.Files
import java.nio.file.Paths

class TestLoader(
   val test_id :Int,
    val context : Context
) {

    fun getQuestionsCount() : Int {
        val bufferedReader = context.assets.open("test${test_id}.csv").bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
        return Integer.parseInt(csvParser.first().get(2))
    }
    fun loadQuestions() : List<Question> {
        if(test_id == 1) {
            /*val opts = listOf("Нет, это не так","Пожалуй, так","Верно","Совершенно верно")
            return listOf(Question("Я спокоен", opts),Question("Мне ничто не угрожает", opts),Question("Я нахожусь в напряжении", opts),
                Question("Я испытываю сожаление", opts),Question("Я чувствую себя свободно", opts),Question("Я расстроен", opts),
                Question("Меня волнуют возможные неудачи", opts),Question("Я чувствую себя отдохнувшим", opts),Question("Я встревожен", opts),
                Question("Я испытываю чувство внутреннего удовлетворения", opts),Question("Я уверен в себе", opts),Question("Я нервничаю", opts),
                Question("Я не нахожу себе места", opts),Question("Я взвинчен", opts),Question("Я не чувствую скованности, напряженности", opts),
                Question("Я доволен", opts),Question("Я озабочен", opts),Question("Я слишком возбужден и мне не по себе", opts),
                Question("Мне радостно", opts),Question("Мне приятно", opts),)
*/
            return parseCSV()
        }
        return listOf(Question("Question # 1 text",
            listOf("Option 1","Option 2","Option 3","Option 4")),
            Question("Question # 2 text",
                listOf("Option 1","Option 2","Option 3","Option 4")),
            Question("Question # 3 text",
                listOf("Option 1","Option 2","Option 3","Option 4")),
            Question("Question # 4 text",
                listOf("Option 1","Option 2","Option 3","Option 4")))
    }

    fun parseCSV() : List<Question> {
        var list_q : List<Question> = emptyList()
        val bufferedReader = context.assets.open("test${test_id}.csv").bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
        for(csvRec in csvParser) {
            if(csvRec.get(0) != test_id.toString()) {
                list_q = list_q.plus(Question(csvRec.get(0), listOf(csvRec.get(1),csvRec.get(2),csvRec.get(3),csvRec.get(4))))
            }
        }
        return list_q
    }

    fun getTestName(): String {

        //context.assets.open("test${test_id}.csv").bufferedReader()
        val bufferedReader = context.assets.open("test${test_id}.csv").bufferedReader()
        val csvParser = CSVParser(bufferedReader, CSVFormat.DEFAULT)
        return csvParser.first().get(1)
    }
    companion object {
        fun getTestName(id : Int, context : Context): String {
            //context.assets.open("test${test_id}.csv").bufferedReader()
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