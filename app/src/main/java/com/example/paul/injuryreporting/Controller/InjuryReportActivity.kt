package com.example.paul.injuryreporting.Controller

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.paul.injuryreporting.Model.SkiArea
import com.example.paul.injuryreporting.R
import com.example.paul.injuryreporting.Services.SkiAreaFacade

import kotlinx.android.synthetic.main.activity_injury_report.*
import kotlinx.android.synthetic.main.content_injury_report.*

class InjuryReportActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_injury_report)
        setSupportActionBar(toolbar)

        val SkiAreaFacade = SkiAreaFacade(this)

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, SkiAreaFacade.getAllSkiAreaNames())

        skiAreaSpinner.adapter = adapter


    }

}
