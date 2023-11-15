package com.example.confirmatio.testsSystem

class TestLoader(
   val test_id :Int
) {

    fun getCount() : Int {
        return 4;
    }
    fun loadQuestions() : List<Question> {
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