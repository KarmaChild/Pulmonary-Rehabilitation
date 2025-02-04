package com.example.pulmonaryrehabilitation.activity.questionnaire

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.R
import com.example.pulmonaryrehabilitation.activity.dashboard.DashboardActivity
import com.example.pulmonaryrehabilitation.member.CurrentUser

class QuestionnaireActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
        val userName = CurrentUser.getFirstName()
        Log.d("QuestionnaireActivity", CurrentUser.toString())
        val titleString = "I have a question for you, $userName "
        val title = findViewById<TextView>(R.id.title)
        title.text = titleString

        val questionString = "How do you feel today?"
        CurrentUser.updateStreakAndPoint()
        val question = findViewById<TextView>(R.id.question)
        question.text = questionString
        val submitAnswer = findViewById<Button>(R.id.submit_answer)
        val answerGroup = findViewById<RadioGroup>(R.id.answer_group)

        submitAnswer.setOnClickListener {
            val checkedAnswerID = answerGroup.checkedRadioButtonId
            val checkedAnswer = findViewById<RadioButton>(checkedAnswerID)
            val answerString = "${checkedAnswer.text}"
            addAnswerToFirebaseDB(questionString, answerString)

            // update user streak and point
            CurrentUser.updateStreakAndPoint()

            val intent = Intent(this, DashboardActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun addAnswerToFirebaseDB(question: String, answer: String) {
        CurrentUser.addQuestionnaireHistory(question, answer)
        Log.d("QuestionnaireActivity", "current questionnaire history - " + CurrentUser.getQuestionnaireHistory().toString())
    }
}