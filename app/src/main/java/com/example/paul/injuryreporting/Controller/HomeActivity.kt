package com.example.paul.injuryreporting.Controller

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.paul.injuryreporting.R

import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        homeReportButton.setOnClickListener{ startActivity(Intent(this, InjuryReportActivity :: class.java)) }
    }


}
