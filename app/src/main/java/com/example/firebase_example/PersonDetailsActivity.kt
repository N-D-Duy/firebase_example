package com.example.firebase_example

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_person_details.*
import kotlinx.android.synthetic.main.activity_write_data.*
import java.util.zip.Inflater

class PersonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)

        setDetailView()

        delete_btn.setOnClickListener {
            deleteItem(intent.getStringExtra("PersonId").toString())
        }

        update_btn.setOnClickListener {
            openUpdateDialog(intent.getStringExtra("PersonId").toString())

            /*name_detail.text = intent.getStringExtra("PersonName").toString()
            age_detail.text = intent.getStringExtra("PersonAge").toString()
            height_detail.text = intent.getStringExtra("PersonHeight").toString()
            Toast.makeText(this, "update successful", Toast.LENGTH_SHORT).show()*/
        }
    }

    private fun openUpdateDialog(id:String?) {
        val mDialog = AlertDialog.Builder(this)
        val mDialogView = layoutInflater.inflate(R.layout.update_dialog, null)
        mDialog.setView(mDialogView)
        val alertDialog = mDialog.create()
        alertDialog.show()

        val updateName = mDialogView.findViewById<EditText>(R.id.update_name)
        val updateAge = mDialogView.findViewById<EditText>(R.id.update_age)
        val updateHeight = mDialogView.findViewById<EditText>(R.id.update_height)
        val btnSave = mDialogView.findViewById<Button>(R.id.save_update)

        updateName.setText(intent.getStringExtra("PersonName").toString())
        updateAge.setText(intent.getStringExtra("PersonAge").toString())
        updateHeight.setText(intent.getStringExtra("PersonHeight").toString())



        btnSave.setOnClickListener {
            updateData(
                id,
                updateName.text.toString(),
                updateAge.text.toString(),
                updateHeight.text.toString()
            )
            Toast.makeText(this, "update successful", Toast.LENGTH_SHORT).show()
        }
        name_detail.text = updateName.toString()
        age_detail.text = updateAge.toString()
        height_detail.text = updateHeight.toString()
        alertDialog.dismiss()
    }

    private fun updateData(id: String?, name: String?, age: String?, height: String?) {
        val data = Person(id!!, name, age, height)
        val dbReference = FirebaseDatabase.getInstance().getReference("Persons").child(id)
        dbReference.setValue(data)
    }

    private fun deleteItem(id: String) {
        val dbReference = FirebaseDatabase.getInstance().getReference("Persons").child(id)
        val itemRemove = dbReference.removeValue()
        itemRemove.addOnCompleteListener {
            Toast.makeText(this, "completed!", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this, ReadDataActivity::class.java))
        }
    }


    private fun setDetailView() {
        id_detail.text = intent.getStringExtra("PersonId")
        name_detail.text = intent.getStringExtra("PersonName")
        age_detail.text = intent.getStringExtra("PersonAge")
        height_detail.text = intent.getStringExtra("PersonHeight")
    }
}