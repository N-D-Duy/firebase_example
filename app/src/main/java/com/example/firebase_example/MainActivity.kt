package com.example.firebase_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        write_btn.setOnClickListener {
            val intent = Intent(this, WriteDataActivity::class.java)
            startActivity(intent)
        }

        read_btn.setOnClickListener {
            val intent = Intent(this, ReadDataActivity::class.java)
            startActivity(intent)
        }

    }
}