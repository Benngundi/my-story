package com.example.storyhub

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.storyhub.Extensions.Extensions.toast
import com.example.storyhub.FirebaseUtils.firebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

    class SignInActivity : AppCompatActivity() {
        lateinit var signInEmail: String
        lateinit var signInPassword: String
        lateinit var signInInputsArray: Array<EditText>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            signInInputsArray = arrayOf(etSignInEmail, etSignInPassword)

            btnCreateAccount2.setOnClickListener {
                startActivity(Intent(this, Registeractivity::class.java))
                finish()
            }

            btnSignIn.setOnClickListener {
                signInUser()
            }
        }

        private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

        private fun signInUser() {
            signInEmail = etSignInEmail.text.toString().trim()
            signInPassword = etSignInPassword.text.toString().trim()

            if (notEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                    .addOnCompleteListener { signIn ->
                        if (signIn.isSuccessful) {
                            startActivity(Intent(this, showavailablestories::class.java))
                            toast("signed in successfully")
                            finish()
                        } else {
                            toast("sign in failed")
                        }
                    }
            } else {
                signInInputsArray.forEach { input ->
                    if (input.text.toString().trim().isEmpty()) {
                        input.error = "${input.hint} is required"
                    }
                }
            }
        }
    }
