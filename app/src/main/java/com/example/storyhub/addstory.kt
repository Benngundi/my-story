package com.example.storyhub

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.addstory.*

class addstory : AppCompatActivity() {
    lateinit var storycategory: TextView
    lateinit var storyaddauthorname:TextView
    lateinit var storyname:TextView
   lateinit var episodname:TextView
    lateinit var episodepic:String
    lateinit var storycontent:EditText
    lateinit var ref: DatabaseReference
    private var storageRef: StorageReference? = null
    lateinit var storyList: MutableList<Registeredstorydmodelclass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addstory)

        ref = FirebaseDatabase.getInstance().getReference("allavailablestories")
        //storage database reference for image uploads to the firebase storage bucket
        storageRef = FirebaseStorage.getInstance().reference.child("allavailablestories")
        storycategory=  findViewById<TextView>(R.id.addstorycategory)
        storyaddauthorname= findViewById<TextView>(R.id.addstoryauthorname)
        storyname=  findViewById<TextView>(R.id.addstorytitle)
        episodname= findViewById<TextView>(R.id.addstoryepisodename)
        // val episodepic= episodeprofilephoto.setImageDrawable()
        storycontent=findViewById<EditText>(R.id.addstorycontent)


        btnsave.setOnClickListener {
            Toast.makeText(this," We are uoloading your story,", Toast.LENGTH_SHORT).show()
            registeruser()

        }


    }
    private fun registeruser() {

        //getting the text input
        val storycategorysv= storycategory.text.toString()
        val storyaddauthornamesv = storyaddauthorname.text.toString()
        val storynamesv= storyname.text.toString()
        val episodnamesv= episodname.text.toString()
        // val episodepic= episodeprofilephoto.setImageDrawable()
        val storycontentsv= storycontent.text.toString()
        storyList = mutableListOf()
        //validating entry

        if (storycategorysv.isEmpty()) {
            storycategory.error = "Required"
        } else if (storyaddauthornamesv.isEmpty()) {
            storyaddauthorname.error = "Required"
        }
        else if (storynamesv.isEmpty()) {
            storyname.error = "Required"
        }
        else if (episodnamesv.isEmpty()) {
            episodname.error = "Required"
        }  else if (storycontentsv.isEmpty()) {
            Toast.makeText(this, "write your story", Toast.LENGTH_SHORT).show()
            storycontent.error = "Required"
        }
        else{
            val storyId = ref.push().key

            val registeruser = storyId?.let {
                Registeredstorydmodelclass(storyId,storycategorysv,storyaddauthornamesv,storynamesv, episodnamesv, storycontentsv)
            }
            //pushing my hero object to my database reference , surrounding with null check to prevent errors
            //this will save our hero to our firebase database
            if (storyId!= null) {
                ref.child(storyId).setValue(registeruser).addOnCompleteListener {
                    Toast.makeText(applicationContext, "Welcome, whats your story?", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(this,showavailablestories::class.java)
                    startActivity(intent)
                }
            }

        }



    }

}