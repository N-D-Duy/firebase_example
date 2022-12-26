package com.example.firebase_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_read_data.*

class ReadDataActivity : AppCompatActivity() {
    private lateinit var list:ArrayList<Person>
    private lateinit var dbReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)
        rv_person.layoutManager = LinearLayoutManager(this)
        list = arrayListOf()
        rv_person.hasFixedSize()


        getInformation()
    }

    private fun getInformation() {
        loading_tv.visibility = View.VISIBLE
        rv_person.visibility = View.GONE
        dbReference = FirebaseDatabase.getInstance().getReference("Persons")
        dbReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                if(p0.exists()){
                    for(p in p0.children){
                        val data = p.getValue(Person::class.java)
                        list.add(data!!)
                    }
                    val mAdapter = PersonAdapter(list)
                    rv_person.adapter = mAdapter
                    mAdapter.setOnClickListener(object : PersonAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@ReadDataActivity, PersonDetailsActivity::class.java)
                            intent.putExtra("PersonId", list[position].personId)
                            intent.putExtra("PersonName", list[position].personName)
                            intent.putExtra("PersonAge", list[position].personAge)
                            intent.putExtra("PersonHeight", list[position].personHeight)

                            startActivity(intent)
                        }
                    })
                    loading_tv.visibility = View.GONE
                    rv_person.visibility = View.VISIBLE

                }
            }


            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}