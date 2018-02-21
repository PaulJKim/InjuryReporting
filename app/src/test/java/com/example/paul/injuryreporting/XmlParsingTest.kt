package com.example.paul.injuryreporting


import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.paul.injuryreporting.Services.SkiAreaFacade
import org.junit.Test

import org.junit.Assert.*
/**
 * Created by Paul on 12/30/2017.
 */

class XmlParsingTest {

    val facade = SkiAreaFacade(AppCompatActivity())

    @Test
    fun parsingSkiAreaXmlDataTest() {
        facade.getAllSkiAreas()
    }

}