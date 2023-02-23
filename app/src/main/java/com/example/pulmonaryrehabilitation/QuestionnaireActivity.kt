package com.example.pulmonaryrehabilitation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.model_database.DatabaseMethod
import com.example.pulmonaryrehabilitation.model_since_2_17.CurrentUser
import com.example.pulmonaryrehabilitation.model_since_2_17.QuestionnaireHistoryClass


class QuestionnaireActivity : AppCompatActivity() {
    private val currentUser = CurrentUser.getUserId()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)
        val userName = CurrentUser.getFirstName()
        val titleString = "I have a question for you, $userName "
        val title = findViewById<TextView>(R.id.title)
        title.text = titleString

        val questionString = "How do you feel today?"

        val question = findViewById<TextView>(R.id.question)
        question.text = questionString
        val submitAnswer = findViewById<Button>(R.id.submit_answer)
        val answerGroup = findViewById<RadioGroup>(R.id.answer_group)

        submitAnswer.setOnClickListener {
            val checkedAnswerID = answerGroup.checkedRadioButtonId
            val checkedAnswer = findViewById<RadioButton>(checkedAnswerID)
            val answerString = "${checkedAnswer.text}"
            addAnswerToFirebaseDB(questionString, answerString)

            val intent = Intent(this, DashboardActivity :: class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun addAnswerToFirebaseDB(question: String, answer: String) {
        val currentTime = CurrentUser.getCurrentDateTime()
        val questionAnswer = QuestionnaireHistoryClass(question, answer)
        val map = mutableMapOf(currentTime to questionAnswer)
        DatabaseMethod().updateQuestionnaireHistoryFor(currentUser, map)
    }
}