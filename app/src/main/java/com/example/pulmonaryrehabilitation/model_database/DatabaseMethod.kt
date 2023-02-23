package com.example.pulmonaryrehabilitation.model_database

import android.content.ContentValues
import android.util.Log
import com.example.pulmonaryrehabilitation.model_since_2_17.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DatabaseMethod : DatabaseInterface {

    override fun readFromDatabase(path: String, data: FirebaseDatabase) {
        val myRef = data.getReference(path)
        val dataListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (child in dataSnapshot.children) {
                    // for now we get all kind of children, but later we must use getValue to get the object we want
                    // For example, to get member  object, implement this line, make sure the path is "Member"
//                     val mem = child.getValue<MemberClass>()
//                     Log.d("Child", mem.toString())
                    Log.d("Child", child.toString())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Member failed, log a message
                Log.w(ContentValues.TAG, "loadMember:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(dataListener)
    }

    // add the input obj into Firebase database if the id is not existed,
    // if its id is already existed in the database, it will update the value according to its id
    override fun writeToDatabase(path: String, data: FirebaseDatabase, obj: Any) {
        val myRef = data.getReference(path)
        var key = ""
        var values: Any = ""

        // switch cases
        when (path) {
            "Member" -> {
                val mem = obj as MemberClass // initialize class
                key = mem.id // initialize key
                values = mem.toMemberMap() // initialize value
            }
            "ExerciseData" -> {
                val exe = obj as ExerciseDataClass // initialize class
                key = exe.id // initialize key
                values = exe.toExerciseDataMap() // initialize value
            }
            "Gamification" -> {
                TODO("initialize data")
            }
            "GamificationQuest" -> {
                TODO("initialize data")
            }
            "StepGoal" -> {
                TODO("initialize data")
            }
            "StepHistory" -> {
                TODO("initialize data")
            }
            "UsageHistory" -> {
                TODO("initialize data")
            }
            else -> {
                Log.d("writeToDatabase", "path exception")
            }
        }

        // put key and its value to hashmap
        val childUpdates = hashMapOf<String, Any>(
            "$key" to values,
        )

        // write new or update object as json string to path
        // updateChildren is pre-built Firebase method to update and create children
        // if the id is already existed in the database then the value will be updated instead of
        // adding
        myRef.updateChildren(childUpdates)
    }

    override fun deleteFromDatabase(path: String, data: FirebaseDatabase, id: String) {
        val myRef = data.getReference(path)

        // 2 way for deleting data with specified path and key (id)
        // removeValue is a pre-built Firebase method to delete object
//        myRef.child(id).removeValue()
        myRef.child(id).setValue(null)
    }

    // INDIVIDUAL USER METHODS
    /*
     getUserDataFor Specification
     Pre-Condition:
        id: the users ID
        database: reference to our database
    Post-Condition:
        Sets the data in CurrentUser object
        Logs failure

    Note: there is a 99.9% chance you shouldn't call this function in your code.
     */
    fun getUserDataFor(id: String, database: FirebaseDatabase) {
        val myRef = database.getReference("Member/$id")
        myRef.get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            CurrentUser.setData(convertFirebaseDataToMember(it.value as HashMap<String, Any>))
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
    }

    /* convertFirebaseDataToMember Specification
    Pre-Condition:
        data: HashMap<String, Any>
        this is the raw data from firebase
    Post-Condition
         returns a MemberClass object populated with the data
     */
    private fun convertFirebaseDataToMember(data: HashMap<String, Any>): MemberClass? {
        val member = MemberClass(
            data.get("id") as String, data.get("isAdmin") as Boolean, data.get("firstName") as String,
            data.get("lastName") as String, data.get("username") as String, data.get("email") as String,
            (data.get("stepGoal") as Long).toInt(),
            data.get("gamificationHistory") as MutableMap<String, GamificationHistoryClass>,
            data.get("usageHistory") as MutableMap<String, UsageHistoryClass>,
            data.get("stepHistory") as MutableMap<String, StepHistoryClass>,
            data.get("questionnaireHistory") as MutableMap<String, QuestionnaireHistoryClass>
        )
        return member
    }

    /*
    Specification for each of the update database functions
    Pre-Condition:
        id: take in the current users ID
        newvalue: will replace the value in the firebasedata
    Post-Condition:
        Update database

     // TODO: These need to append to the Firebase data, not replace it as it currently does.

     */
    fun updateFirstNameFor(id: String, newName: String) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/firstName") // this is the path
        // directly to the item in Firebase
        myReference.setValue(newName)
    }
    fun updateLastNameFor(id: String, newName: String) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/lastName")
        myReference.setValue(newName)
    }
    fun updateStepCountGoalFor(id: String, newGoal: Int) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/stepGoal")
        myReference.setValue(newGoal)
    }
    fun updateAdminStatusFor(id: String, newStatus: Boolean) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/isAdmin")
        myReference.setValue(newStatus)
    }
    fun updateStepHistoryFor(id: String, newHistory: Map<String, StepHistoryClass>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/stepHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
    }
    fun updateQuestionnaireHistoryFor(
        id: String,
        newHistory: Map<String, QuestionnaireHistoryClass>
    ) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/questionnaireHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
    }
    fun updateUsageHistoryFor(id: String, newHistory: Map<String, UsageHistoryClass>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/usageHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
    }
    fun updateGamificationHistory(id: String, newHistory: Map<String, GamificationHistoryClass>) {
        val database = Firebase.database
        val myReference = database.getReference("Member/$id/gamificationHistory")
        val childName: String = newHistory.keys.toString().substring(1, newHistory.keys.toString().length - 1)
        newHistory.values.forEach {
            myReference.child(childName).setValue(it)
        }
    }
}