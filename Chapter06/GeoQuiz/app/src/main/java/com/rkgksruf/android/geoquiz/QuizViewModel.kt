package com.rkgksruf.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {

    var currentIndex = 0
    var isCheater = false

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    val currentQuestionAnswer: Boolean get() = questionBank[currentIndex].answer

    val currentQuestionText: Int get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

/*  -------ViewModel 로그 찍고 디버깅 해보기 좋음
    init {      // 초기화 블록, 클래스 인스턴스 생성 시
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {      // 인스턴스 소멸
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }*/
}