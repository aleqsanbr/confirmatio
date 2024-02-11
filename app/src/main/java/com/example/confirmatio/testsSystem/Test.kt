package com.example.confirmatio.testsSystem

class Test (
    val name : String,
    val q_count : Int,
   val q_pool : List<Question>
){

    lateinit var qa_map : MutableMap<Question, Int>
    fun getQuestion(n : Int) : Question {
        return q_pool[n-1];
    }
    fun initializeQAPairs() {
        qa_map = mutableMapOf()
        for(q :Question in q_pool) {
            qa_map.put(q, -1)
        }
    }

}

enum class TestID (val id : Int){
    STAI(1), LSAS(2), None(4);
}

fun getTestId(id : Int) : TestID {
    when(id) {
        1 -> return TestID.STAI
        2 -> return TestID.LSAS
        else -> return TestID.None
    }
}