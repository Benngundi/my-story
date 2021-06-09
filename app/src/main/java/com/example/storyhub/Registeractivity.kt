package com.example.storyhub

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.*
import com.example.storyhub.Extensions.Extensions.toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_registeractivity.*
import java.io.IOException

//import com.google.firebase.database.*
//import kotlinx.android.synthetic.main.activity_registeractivity.*


class Registeractivity : AppCompatActivity() {
    lateinit var userfirstname:TextView
    lateinit var userlastname:TextView
    lateinit var txtuseremail:TextView
    lateinit var userpassword: TextView
    lateinit var userprofilephoto:ImageView
    lateinit var Gender:String
    lateinit var accounttype:String
    private lateinit var imageView: ImageView
    private lateinit var imageButton: Button
    private lateinit var sendButton: Button
    private var imageData: ByteArray? = null
    private val postURL: String = "https://ptsv2.com/t/54odo-1576291398/post" // remember to use your own api
    lateinit var ref: DatabaseReference
    private var storageRef: StorageReference? = null
    lateinit var listview:ListView
    lateinit var btnregister:TextView
    lateinit var txtvhaveaccount:TextView
    lateinit var usersList: MutableList<Registeredusedmodelclass>
    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>

    companion object {
        private const val IMAGE_PICK_CODE = 999
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeractivity)
        userfirstname = findViewById(R.id.etSignInEmail)
        userlastname=findViewById(R.id.inputuserlastname)
        txtuseremail = findViewById(R.id.etEmail)
        userpassword = findViewById(R.id.etPassword)
        listview = findViewById(R.id.listviewuser)
        txtvhaveaccount = findViewById<TextView>(R.id.btnSignIn2)
        btnregister = findViewById<TextView>(R.id.Btnsave)
        userprofilephoto=findViewById(R.id.inputuserprofilephoto)
        usersList = mutableListOf()
        imageView = findViewById(R.id.inputuserprofilephoto)

        imageButton = findViewById(R.id.imageButton)
        createAccountInputsArray = arrayOf(etEmail, etPassword, etConfirmPassword)



        imageButton.setOnClickListener {
            launchGallery()
        }

        sendButton = findViewById(R.id.sendButton)
        sendButton.setOnClickListener {}
//from create account




            Btnsave.setOnClickListener {
                signIn()
            }

            btnSignIn2.setOnClickListener {
                startActivity(Intent(this, SignInActivity::class.java))
                toast("please sign into your account")
                finish()
            }
        //end of create account


//end of uploading image

    }
    /* check if there's a signed-in user*/

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = FirebaseUtils.firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, showavailablestories::class.java))
            toast("welcome back")
        }
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = etEmail.text.toString().trim()
            userPassword = etPassword.text.toString().trim()

            /*create a user*/
            FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("created account successfully !")
                        sendEmailVerification()
                        startActivity(Intent(this, SignInActivity::class.java))
                        finish()
                    } else {
                        toast("failed to Authenticate !")
                    }
                }
        }
    }

    private fun sendEmailVerification() {
        FirebaseUtils.firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }
        }
    }

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            etPassword.text.toString().trim() == etConfirmPassword.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast("passwords are not matching !")
        }
        return identical
    }

    private fun notEmpty(): Boolean = etEmail.text.toString().trim().isNotEmpty() &&
            etPassword.text.toString().trim().isNotEmpty() &&
            etConfirmPassword.text.toString().trim().isNotEmpty()


    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }




    @Throws(IOException::class)
    private fun createImageData(uri: Uri) {
        val inputStream = contentResolver.openInputStream(uri)
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val uri = data?.data
            if (uri != null) {
                imageView.setImageURI(uri)
                createImageData(uri)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.accountypeauthor ->
                    if (checked) {
                        Gender=="Author"
                    }
                R.id.accounttyoeuser ->
                    if (checked) {
                        Gender=="User"
                    }

            }
        }
    }
    fun onraButtonaccounttypeClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_male ->
                    if (checked) {
                        accounttype=="male"
                    }
                R.id.radio_female ->
                    if (checked) {
                        accounttype=="female"
                    }
                R.id.radio_other ->
                    if (checked) {
                        accounttype=="others"
                    }
            }
        }
    }
}