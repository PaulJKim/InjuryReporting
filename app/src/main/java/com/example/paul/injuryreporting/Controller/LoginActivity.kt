package com.example.paul.injuryreporting.Controller

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.paul.injuryreporting.R
import com.example.paul.injuryreporting.Services.SkiAreaFacade
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_injury_report.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        var skiAreaFacade = SkiAreaFacade(this)
        var progressBar = loginProgressBar
        registrationButton.setOnClickListener { view ->
            startActivity(Intent(this, RegisterActivity::class.java)) }

        loginButton.setOnClickListener { view ->
            Thread(Runnable {
                // dummy thread mimicking some operation whose progress cannot be tracked

                // display the indefinite progressbar
                this@LoginActivity.runOnUiThread({
                    progressBar.visibility = View.VISIBLE
                })

                // performing some dummy time taking operation
                skiAreaFacade.getAllSkiAreas()

                // when the task is completed, make progressBar gone
                this@LoginActivity.runOnUiThread({
                    progressBar.visibility = View.GONE
                })
            }).start()

            login() }
    }

    private fun login() {
        var emailText = email.text.toString()
        var pass = password.text.toString()

        mAuth.signInWithEmailAndPassword(emailText, pass).addOnCompleteListener(this, OnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Logged In!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, HomeActivity::class.java))
//                GetSkiAreasAsync(this).execute()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    inner class GetSkiAreasAsync(context: Context): AsyncTask<Context, String, String>() {

        var context = context

        override fun doInBackground(vararg p0: Context?): String? {
            val skiAreaFacade = SkiAreaFacade(context)
            skiAreaFacade.getAllSkiAreas()
            return "Done"
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_login, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
