package com.example.pulmonaryrehabilitation.model_database

import com.google.firebase.database.FirebaseDatabase

interface DatabaseInterface {
    fun readFromDatabase(path: String, data: FirebaseDatabase)
    fun writeToDatabase(path: String, data: FirebaseDatabase, obj: Any)
    fun deleteFromDatabase(path: String, data: FirebaseDatabase, id: String)
}