package com.example.paul.injuryreporting.Controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.paul.injuryreporting.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    val loginService = LoginService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        registerButton2.setOnClickListener { view -> register() }
    }

    private fun register() {
        var newEmail = registerEmail.text.toString()
        val newPass = registerPassword.text.toString()

        if (loginService.verifyNewPassword(registerPassword.text.toString(), registerPasswordConfirm.text.toString()) && !newEmail.isEmpty() && !newPass.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(newEmail, newPass).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
        }
    }

}
