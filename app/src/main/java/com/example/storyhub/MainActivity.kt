package com.example.storyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnmember=findViewById<Button>(R.id.btnmember)
        val  btnnotmember=findViewById<Button>(R.id. btnnotmember)
        val btnstories=findViewById<Button>(R.id.btnstories)
        val btnhometest= findViewById<Button>(R.id.btnstoriesavailalestorie)

        btnmember.setOnClickListener {
            intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        btnnotmember.setOnClickListener {
            intent = Intent(this, Registeractivity::class.java)
            startActivity(intent)
        }
        btnstories.setOnClickListener {
            intent = Intent(this, showavailablestories::class.java)
            startActivity(intent)
        }

    }
}