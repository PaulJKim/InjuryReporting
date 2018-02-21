package com.example.paul.injuryreporting.Services

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.paul.injuryreporting.Model.SkiArea
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

/**
 * Created by Paul on 12/28/2017.
 */

class SkiAreaFacade(context: Context) {

    val baseURL = "https://skimap.org/"
    val requestQueue = Volley.newRequestQueue(context)
    val LOG_TAG = "XML"

    companion object {
        var skiAreaList: ArrayList<SkiArea> = ArrayList()
    }


//    fun getAllSkiAreas(): List<String> {
//        val url = URL(baseURL)
//
//        with(url.openConnection() as HttpURLConnection) {
//            requestMethod = "GET"
//        }
//    }

    fun getAllSkiAreas() {
        val url = baseURL + "SkiAreas/index.xml"

        val stringRequest = StringRequest(url,
            Response.Listener { response ->
                Log.d("Success", response.toString())
                processSkiAreaXMLResponse(response.toString())
            },
            Response.ErrorListener { error ->
                Log.d("Error", error.toString() + ":: Error requesting url: " + url)
            })

        stringRequest.setRetryPolicy(DefaultRetryPolicy(8000, 2, 2.0F))
        requestQueue.add(stringRequest)
    }

    fun processSkiAreaXMLResponse(response: String) : List<SkiArea>{
        val pullParserFactory = XmlPullParserFactory.newInstance()
        pullParserFactory.isNamespaceAware = true
        val parser = pullParserFactory.newPullParser()
        parser.setInput(StringReader(response))
        var eventType = parser.eventType

        while(eventType != XmlPullParser.END_DOCUMENT) {

            if (parser.name != null && parser.name.equals("name")) {
                eventType = parser.next()
                if (parser.text != null && parser.text.isNotBlank()) {
                    var newSkiArea = SkiArea(parser.text)
                    skiAreaList.add(newSkiArea)
                    Log.d(LOG_TAG, "New Ski Area Created: " + newSkiArea.name)
                }
            }

//            if (parser.eventType == (XmlPullParser.START_DOCUMENT)) {
//                var name = parser.name
////                Log.d(LOG_TAG, parser.text)
////                Log.d(LOG_TAG, "parsing")
//            } else {
//                Log.d(LOG_TAG, "Name: " + parser.name + " Content: " + parser.text)
//            }
            eventType = parser.next()
        }
        return skiAreaList
//        Log.d(LOG_TAG, parser.nextText())
    }

    fun getAllSkiAreaNames(): ArrayList<String> {
        var nameList = ArrayList<String>()
        skiAreaList.forEach({ skiArea: SkiArea ->
            nameList.add(skiArea.name)
        })
        return nameList
    }
}