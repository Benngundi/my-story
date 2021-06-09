package com.example.storyhub

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*


class singlestoryactivity : AppCompatActivity() {
    private var listData: MutableList<singlestorymodelaclass?>? = null
    private var rv: RecyclerView? = null
    private var adapter: singlestoryadapterclass? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onestory)
        rv = findViewById<View>(R.id.recycler_view_onestory) as RecyclerView
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)
        listData = ArrayList<singlestorymodelaclass?>()
        val nm = FirebaseDatabase.getInstance().getReference("allavailablestories")
        nm.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (npsnapshot in dataSnapshot.children) {
                        val l: singlestorymodelaclass? = npsnapshot.getValue(singlestorymodelaclass::class.java)
                        listData!!.add(l)
                    }
                    adapter = singlestoryadapterclass(listData as ArrayList<singlestorymodelaclass?>)
                    rv!!.adapter = adapter
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}