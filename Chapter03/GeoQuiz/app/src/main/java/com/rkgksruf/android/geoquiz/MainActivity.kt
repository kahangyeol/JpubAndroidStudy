package com.rkgksruf.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate() call")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { _ : View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { _:View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { _:View ->
            currentIndex = (currentIndex + 1) % questionBank.size
            isAnswered(currentIndex)
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart() call")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume() call")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() call")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() call")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() call")
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        questionBank[currentIndex].answered = messageResId == R.string.correct_toast
    }

    private fun isAnswered(index: Int) {
        trueButton.isEnabled = !questionBank[index].answered
    }
}