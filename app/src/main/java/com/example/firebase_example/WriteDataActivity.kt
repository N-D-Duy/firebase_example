package com.example.firebase_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_write_data.*

class WriteDataActivity : AppCompatActivity() {
    private lateinit var dbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_data)

        dbRef = FirebaseDatabase.getInstance().getReference("Persons")
        save_btn.setOnClickListener {
            saveInformation()
        }
    }

    private fun saveInformation() {
        val personName = name_edt.text.toString()
        val personAge = age_edt.text.toString()
        val personHeight = height_edt.text.toString()

        val personId = dbRef.push().key!!
        val person = Person(personId,personName, personAge, personHeight)
        dbRef.child(personId).setValue(person)
            .addOnCompleteListener {
                Toast.makeText(this, "write data successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}