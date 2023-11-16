package com.example.confirmatio.testsSystem

import android.util.Log

class TestManager(
    val  test_id : Int
) {
    var cur_q : Int = 1
        set(n : Int) {field = n}
    val loader : TestLoader = TestLoader(test_id)
    var progress : Int = 0;
    lateinit var test : Test

    fun startTest() {
        loadTest()
        loadQAPairs()
    }

    fun loadTest() {
        test = Test(loader.getCount(), loader.loadQuestions())
    }

    fun loadQAPairs() {
        test.initializeQAPairs()
    }

    fun amountOfQuestions() : Int {
        return test.q_count;
    }
    fun getQuestion(n: Int = cur_q) : Question {
        return test.getQuestion(n)
    }
    fun getOptions(n: Int = cur_q) : List<String> {
        return getQuestion().options
    }
    fun getChoosedOption(q_ind : Int = cur_q) : Int {
        if(test.qa_map.containsKey(test.getQuestion(q_ind))) {
            return test.qa_map[test.getQuestion(q_ind)]!!
        } else return -1
    }
    fun saveAnswer(q_ind : Int = cur_q, a_pos : Int) {
        if(test.qa_map.containsKey(test.getQuestion(q_ind))) {
            Log.d("DEBUG QUESTION", "+++++++++++++ test.qa_map.containsKey(test.getQuestion(q_ind)")
            test.qa_map[test.getQuestion(q_ind)] = a_pos
        }
    }

    fun getProgress() : Float {
        return cur_q.toFloat() / test.q_count;
    }
    fun updateProgress() {
        if(cur_q > progress) progress+=1
    }

}