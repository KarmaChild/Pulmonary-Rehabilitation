package com.example.pulmonaryrehabilitation.model_database

import android.content.ContentValues
import android.util.Log
import com.example.pulmonaryrehabilitation.model_since_2_17.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseMethod : DatabaseInterface {
// Henry: what is your reasoning behind the name DatabaseMethod?
    fun getUserDataFor(id: String, database: FirebaseDatabase): MemberClass? {
        var userData: MemberClass? = null
        val myRef = database.getReference("Member/$id")
        myRef.get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
            val member: MemberClass? =  convertFirebaseDataToMember(it.value as HashMap<String, Any>)
            userData = member
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        return userData
    }
    private fun convertFirebaseDataToMember(data: HashMap<String, Any>): MemberClass? {

        val member = MemberClass(
            data.get("id") as String, data.get("username") as String,
            data.get("password") as String, data.get("email") as String, (data.get("stepGoal") as Long).toInt(),
            data.get("gamificationHistory") as Map<String, GamificationHistoryClass>,
            data.get("usageHistory") as Map<String, UsageHistoryClass>,
            data.get("stepHistory") as Map<String, StepHistoryClass>
        )
        return member
    }

    override fun readFromDatabase(path: String, data: FirebaseDatabase) {
        // This is for listening for database changes to get a specific user, use getUserDataFor() ↑
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
}