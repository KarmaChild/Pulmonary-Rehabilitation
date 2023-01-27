package com.example.pulmonaryrehabilitation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pulmonaryrehabilitation.Model.Member
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val login = findViewById<Button>(R.id.login)
        val register = findViewById<Button>(R.id.register)
        val dashboard = findViewById<Button>(R.id.dashboard)

        login.setOnClickListener {
            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
        }
        register.setOnClickListener {
            val intent = Intent(this, EmailRegisterActivity::class.java)
            startActivity(intent)
        }

        dashboard.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        val myRef = database.getReference("Members")

        val mem = Member("3", "trang", "trang", "tnd246@gmai.com", "0123456789")
        val mem1 = Member("4", "eugene", "eugene", "eugene@gmai.com", "0123456789")
        writeNewMember(mem, myRef)
        writeNewMember(mem1, myRef)
        readMemberfromDatabase(myRef)
    }

    private fun readMemberfromDatabase(myRef: DatabaseReference) {
        val memberListener = object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                for (child in ds.children) {
                    val m = child.getValue<Member>()!!
                    println(m.toString())
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("onCancelled", databaseError.toException())
            }
        }

        myRef.addValueEventListener(memberListener)
    }

    private fun writeNewMember(member: Member, myRef: DatabaseReference) {
        val key = member.id
        val values = member.toMemberMap()
        val childUpdates = hashMapOf<String, Any>(
            "$key" to values,
        )
        myRef.updateChildren(childUpdates)
    }

    private fun deleteMember(member: Member, myRef: DatabaseReference) {
        val memberListener = object : ValueEventListener {
            override fun onDataChange(ds: DataSnapshot) {
                for (child in ds.children) {
                    val m = child.getValue<Member>()!!
                    if (m.id.equals(member.id)) {
                        val key = m.id
                        if (key != null) {
                            myRef.child(key).removeValue()
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("onCancelled", databaseError.toException())
            }
        }

        myRef.addValueEventListener(memberListener)
    }
}