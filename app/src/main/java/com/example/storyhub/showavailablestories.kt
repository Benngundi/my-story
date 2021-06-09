package com.example.storyhub

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyhub.Extensions.Extensions.toast
import com.example.storyhub.FirebaseUtils.firebaseAuth
import com.google.android.material.navigation.NavigationView


class showavailablestories : AppCompatActivity() {
    private var dl: DrawerLayout? = null
    private var t: ActionBarDrawerToggle? = null
    private var nv: NavigationView? = null

    lateinit var storyadapter:availabelstoriesadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.showavailablestories)

        iniRrecycleview()
        adddataset()


        setSupportActionBar(findViewById(R.id.toolbar))

        //menu drawer
        dl = findViewById<View>(R.id.activity_hometest) as DrawerLayout
        t = ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close)
        dl!!.addDrawerListener(t!!)
        t!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        nv = findViewById<View>(R.id.nv) as NavigationView
        nv!!.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            val id = item.itemId

            when (id) {
                R.id.nav_home -> openhome("home")
                R.id.nav_myaccount ->openhome("myaccount")
                R.id.nav_addstroy -> openhome("addstory")
                R.id.nav_logout-> openhome("signout")
                else -> return@OnNavigationItemSelectedListener true
            }
            true
        })


    }

    private fun openhome(page:String){
        if(page=="home"){
        intent = Intent(this,Registeractivity::class.java)
        startActivity(intent)}
        else if (page=="myaccount"){
            intent = Intent(this, MainActivity::class.java)
        startActivity(intent)}
        else if (page=="signout"){
            firebaseAuth.signOut()
            intent = Intent(this, Registeractivity::class.java)
            toast("signed out")
            startActivity(intent)}
        else if (page=="addstory")
            intent = Intent(this, addstory::class.java)
        startActivity(intent)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (t!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    private  fun adddataset(){
        var data= availablestoriesdatasource.createDataSet()
        storyadapter.submitlist(data)
    }
    private fun iniRrecycleview(){
var recyclerviewname= findViewById<RecyclerView>(R.id.recycler_view)
    recyclerviewname.layoutManager=LinearLayoutManager(this@showavailablestories)
    storyadapter=availabelstoriesadapter()
        val topspacingdecoration= availablestoriestoppadding(5)
        recyclerviewname.addItemDecoration(topspacingdecoration)
    recyclerviewname.adapter=storyadapter
    }

}