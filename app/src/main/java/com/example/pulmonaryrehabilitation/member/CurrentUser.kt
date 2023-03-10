package com.example.pulmonaryrehabilitation.member

import android.util.Log
import com.example.pulmonaryrehabilitation.database.DatabaseMethod
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.Instant
import java.time.LocalDate
import java.util.concurrent.TimeUnit

/*
CurrentUser Object Specification
This is the connection to the users data. Anything you want to get or change, should be done through this.
It is a singleton because of the nature of need. We will need it on almost every activity and so passing it around
seems like a poor implementation.
Pre-Condition:
getUserDataFor() in Database method needs to be called on a valid user ID otherwise everything will return null
Post-Condition:
Allow developers access to the relevant user data through internal methods.

Ian Kohlert
Feb 19, 2023
 */
object CurrentUser {
    private var data: MemberClass? = null
    // When searching the logs, "CurrentUser" tag can be used to filter the logs of this class
    private const val LOG_TAG: String = "CurrentUser"

    /*
    setData Method Specification
    Pre-Condition:
        Pass in a nullable Member Class. Other than testing, this will only be done via the method
        getUserDataFor() in the DatabaseMethod file.
    Post-Condition
        Sets the users data in the CurrentUser object.
     */
    fun setData(member: MemberClass?) {
        Log.d(LOG_TAG, "setData() invoked")
        data = member
        // Testing ↓
//        setGoal(3333)
//        addStepHistory(4999)
    }

    // GETTERS
    /* Specification for each getter
        Pre-Condition: None
        Post-Condition:
            It will return the desired value if 'data' has been set with a valid memberClass object
            If not it returns a default value via the elvis operator (?:)
            The goal of the default value is to make it clear that there was an error

     */

    fun getUserId(): String {
        Log.d(LOG_TAG, "getUserId() invoked")
        val user = Firebase.auth.currentUser
        return if (user != null) {
            user.uid
        } else {
            // user not signed in
            Log.e(LOG_TAG, "user not signed in")
            "Error"
        }
    }
    fun getFirstName(): String {
        Log.d(LOG_TAG, "getFirstName() invoked")
        return data?.firstName ?: "Error"
    }
    fun getLastName(): String {
        Log.d(LOG_TAG, "getLastName() invoked")
        return data?.lastName ?: "Error"
    }
    fun getStepGoal(): Int {
        Log.d(LOG_TAG, "getStepGoal() invoked")
        return data?.stepGoal ?: -9999
    }
    fun getStepHistory(): Map<String, StepHistoryClass> {
        Log.d(LOG_TAG, "getStepHistory() invoked")
        return data?.stepHistory ?: mutableMapOf()
    }
    fun getGamificationHistory(): Map<String, GamificationHistoryClass> {
        Log.d(LOG_TAG, "getGamificationHistory() invoked")
        return data?.gamificationHistory ?: mutableMapOf()
    }
    fun getUsageHistory(): Map<String, UsageHistoryClass> {
        Log.d(LOG_TAG, "getUsageHistory() invoked")
        return data?.usageHistory ?: mutableMapOf()
    }

    /*
    Specification for getCurrentDateTime
        Pre-Condition: None
        Post-Condition:
            returns the current time in the unix timestamp as a Long value type
             which is converted to a String
     */
    private fun getCurrentDateTime(): String {
        try {
            Log.d(LOG_TAG, "getCurrentDateTime() invoked")

            return Instant.now().toEpochMilli().toString()
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in getCurrentDateTime()", exception)
            return ""
        }
    }

    /*
    Specification for getCurrentDateTime
        Pre-Condition: None
        Post-Condition:
            returns the date of when the user last finished a questionnaire
            as a unix timestamp in Long value type
     */
    fun getLastQuestionnaireDate(): Long? {
        Log.d(LOG_TAG, "getLastQuestionnaireDate() invoked")

        return data?.lastQuestionnaireDate?.toLong()
    }

    /*
    Specification for getCurrentDateTime
        Pre-Condition:
            lastQuestionnaireDate -  The date of when the user last finished a questionnaire
            as a unix timestamp in Long value type

            daysSince - The number of days since the last questionnaire that has to be checked

        Post-Condition:
            returns true if it has been more than number of "daysSince" user did a questionnaire
            returns false otherwise
     */
    fun daysSinceLastQuestionnaire(lastQuestionnaireDate: Long?, daysSince: Long): Boolean {
        if (lastQuestionnaireDate == null) {
            return true
        }
        val currentTime: Long = getCurrentDateTime().toLong()
        val daysToSubtract = TimeUnit.DAYS.toMillis(daysSince)

        return (currentTime - daysToSubtract) > lastQuestionnaireDate
    }

    // This function might be useful later on to parse the unix timestamp to human readable format
    fun parseDate(date: Long) {
        Log.d(LOG_TAG, "parseDate() invoked")

        // TODO
    }
    fun getQuestionnaireHistory(): Map<String, QuestionnaireHistoryClass> {
        Log.d(LOG_TAG, "getQuestionnaireHistory() invoked")
        return data?.questionnaireHistory ?: mutableMapOf()
    }
    // END GETTERS

    // SETTERS
    /*
    Specification for each setter
    Pre-Condition:
        The new value you wish to add to the database
    Post-Condition:
        Updates the local 'data' object to avoid an unnecessary read
        Updates the Firebase database

     // note for below, it's safe to use !! because I check it's not null

     TODO: create a function that creates a timestamp and replace the hard coded 'Timestamp' string
     TODO: Once we can append to Firebase objects the collection write functions need to be
      updated to only send the new value

     */
    fun setFirstName(newName: String) {
        Log.d(LOG_TAG, "setFirstName() invoked")
        if (data != null) {
            data?.firstName = newName
            DatabaseMethod().updateFirstNameFor(data!!.id, newName)
        }
    }

    fun setLastName(newName: String) {
        Log.d(LOG_TAG, "setLastName() invoked")
        if (data != null) {
            data?.lastName = newName
            DatabaseMethod().updateLastNameFor(data!!.id, newName)
        }
    }
    fun setAdminStatus(newStatus: Boolean) {
        Log.d(LOG_TAG, "setAdminStatus() invoked")
        if (data != null) {
            data?.isAdmin = newStatus
            DatabaseMethod().updateAdminStatusFor(data!!.id, newStatus)
        }
    }
    fun setGoal(newGoal: Int) {
        Log.d(LOG_TAG, "setGoal() invoked")
        if (data != null) {
            data?.stepGoal = newGoal
            DatabaseMethod().updateStepCountGoalFor(data!!.id, newGoal)
        }
    }

    fun getStreak(): String {
        Log.d(LOG_TAG, "getStreak() invoked")
        return data?.streak ?: "Error"
    }

    fun getNextMonday(date: String): String {
        try {
            Log.d(LOG_TAG, "member getNextMonday() invoked")
            val yyyy = Integer.parseInt(date.subSequence(0, 4).toString())
            val mm = Integer.parseInt(date.subSequence(5, 7).toString())
            val dd = Integer.parseInt(date.subSequence(8, 10).toString())
            val tempDate = LocalDate.of(yyyy, mm, dd)
            return tempDate.minusDays(tempDate.dayOfWeek.value.toLong() - 1 - 7).toString()
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in getNextMonday()", exception)
            return ""
        }
    }

    // converted long datetime format in usageHistory keys to normal datetime format (yyyy-MM-dd hh-mm-ss)
    fun convertDate(date: String): String {
        try {
            val convertedDate = Instant.ofEpochMilli(date.toLong()).toString()
            Log.d(LOG_TAG, "member calculateStreak() convertDate() invoked - $convertedDate")
            return convertedDate
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in convertDate()", exception)
            return ""
        }
    }
    fun getMonday(date: String): String {
        try {
            Log.d(LOG_TAG, "member getMonday() invoked")
            val yyyy = Integer.parseInt(date.subSequence(0, 4).toString())
            val mm = Integer.parseInt(date.subSequence(5, 7).toString())
            val dd = Integer.parseInt(date.subSequence(8, 10).toString())
            val tempDate = LocalDate.of(yyyy, mm, dd)
            return tempDate.minusDays(tempDate.dayOfWeek.value.toLong() - 1).toString()
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in getMonday()", exception)
            return ""
        }
    }

    fun getWeeklyExercisePoint(): String {
        try {
            Log.d(LOG_TAG, "getWeeklyExercisePoint() invoked")
            return data?.weeklyExercisePoint ?: "Error"
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in getWeeklyExercisePoint()", exception)
            return ""
        }
    }
    fun addStepHistory(numberSteps: Int) {
        Log.d(LOG_TAG, "addStepHistory() invoked")
        val timestamp: String = getCurrentDateTime()
        if (data != null) {
            if (data!!.stepHistory == null) {
                data!!.stepHistory = mutableMapOf(timestamp to StepHistoryClass(numberSteps.toString()))
                val newHistory = mutableMapOf(timestamp to StepHistoryClass(numberSteps.toString()))
                DatabaseMethod().updateStepHistoryFor(data!!.id, newHistory)
            }

            // update our local map for current user
            data!!.stepHistory?.set(timestamp, StepHistoryClass(numberSteps.toString()))
            // update database step value for current user
            val newHistory = mutableMapOf(timestamp to StepHistoryClass(numberSteps.toString()))
            DatabaseMethod().updateStepHistoryFor(data!!.id, newHistory)
        }
    }
    fun addQuestionnaireHistory(question: String, answer: String) {
        try {
            Log.d(LOG_TAG, "addQuestionnaireHistory() invoked")
            val timestamp: String = getCurrentDateTime()
            if (data != null) {
                if (data!!.questionnaireHistory == null) {
                    data!!.questionnaireHistory = mutableMapOf(timestamp to QuestionnaireHistoryClass(question, answer))
                    val newHistory = mutableMapOf(timestamp to QuestionnaireHistoryClass(question, answer))
                    DatabaseMethod().updateQuestionnaireHistoryFor(data!!.id, newHistory)
                }

                // update our local map for current user
                data!!.questionnaireHistory?.set(timestamp, QuestionnaireHistoryClass(question, answer))
                data!!.lastQuestionnaireDate = timestamp
                // update database step value for current user
                val newHistory = mutableMapOf(timestamp to QuestionnaireHistoryClass(question, answer))
                DatabaseMethod().updateQuestionnaireHistoryFor(data!!.id, newHistory)
            }
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in addQuestoinnaireHistory()", exception)
        }
    }

    // call this everytime user are about to view streak, so we can update it in time
    fun updateStreakAndPoint() {
        try {
            val now = getCurrentDateTime()
            if (data != null) {
                if (data!!.usageHistory != null) {
                    val usageHistorySize = data!!.usageHistory?.size
                    // get the latest usage history date
                    val latestDate =
                        usageHistorySize?.let { data!!.usageHistory?.keys?.elementAt(it - 1) }

                    // case 1 (time diff between {latest and now}.getMonday = 1 or != 0, point = 0
                    if (latestDate?.let { convertDate(it) }?.let { getMonday(it) } != getMonday(
                            convertDate(now)
                        )
                    ) {
                        DatabaseMethod().updateWeeklyExercisePoint(data!!.id, "0")
                    }

                    // case 2 (time diff between {latest and now}.getMonday >= 2 or !=0 and !=1, point = 0, streak = 0
                    if (
                        latestDate?.let { convertDate(it) }?.let { getMonday(it) }
                        != getMonday(convertDate(now)) &&
                        latestDate?.let { convertDate(it) }?.let { getNextMonday(it) }
                        != getMonday(convertDate(now))
                    ) {
                        DatabaseMethod().updateWeeklyExercisePoint(data!!.id, "0")
                        DatabaseMethod().updateStreak(data!!.id, "0")
                    }

                    // case 3 (time diff between {latest and now}.getMonday = 0, nothing happen
                }
            }
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in updateStreakAndPoint()", exception)
        }
    }

    // Currently adding 2 items to usage history. Will need to redo this after we get more
    // info about usage from the stakeholder
    // Currently adding 2 items to usage history. Will need to redo this after we get more
    // info about usage from the stakeholder
    fun addUsageHistory(exerciseDone: String) {
        try {
            Log.d(LOG_TAG, "addUsageHistory() invoked")
            val timestamp: String = getCurrentDateTime()
            var previousDate: String

            if (data != null) {
                if (data!!.usageHistory == null) {
                    data!!.usageHistory =
                        mutableMapOf(timestamp to UsageHistoryClass(exerciseDone, "item2"))
                    val newHistory = mutableMapOf(timestamp to UsageHistoryClass(exerciseDone, "item2"))
                    DatabaseMethod().updateUsageHistoryFor(data!!.id, newHistory)

                    // streak = 0, weekly point = 1
                    DatabaseMethod().updateWeeklyExercisePoint(data!!.id, "1")
                    DatabaseMethod().updateStreak(data!!.id, "0")
                }
                data!!.usageHistory?.set(timestamp, UsageHistoryClass(exerciseDone, "item2"))
                val newHistory = mutableMapOf(timestamp to UsageHistoryClass(exerciseDone, "item2"))
                DatabaseMethod().updateUsageHistoryFor(data!!.id, newHistory)

                val newPoint = getWeeklyExercisePoint().toInt() + 1
                DatabaseMethod().updateWeeklyExercisePoint(data!!.id, newPoint.toString())
                if (newPoint == 3) {
                    val newStreak = getStreak().toInt() + 1
                    DatabaseMethod().updateStreak(data!!.id, newStreak.toString())
                }
            }
        } catch (exception: Exception) {
            Log.e("Error", "Exception encountered in addUsageHistory()", exception)
        }
    }
    fun addGamificationHistory(event: String, points: String) {
        Log.d(LOG_TAG, "addGamificationHistory() invoked")
        val timestamp: String = getCurrentDateTime()
        if (data != null) {
            if (data!!.gamificationHistory == null) {
                data!!.gamificationHistory = mutableMapOf(timestamp to GamificationHistoryClass(event, points))
                data!!.gamificationHistory?.let {
                    DatabaseMethod().updateGamificationHistory(
                        data!!.id,
                        it
                    )
                }
            }
            data!!.gamificationHistory?.set(timestamp, GamificationHistoryClass(event, points))
            data!!.gamificationHistory?.let {
                DatabaseMethod().updateGamificationHistory(
                    data!!.id,
                    it
                )
            }
        }
    }

    // END SETTERS
}