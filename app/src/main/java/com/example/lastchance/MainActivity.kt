package com.example.lastchance

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    private lateinit var requestQueue:RequestQueue;
    private lateinit var button:Button;

    private lateinit var nameField:TextView;
    private lateinit var addressField:TextView;
    private lateinit var latitudeField:TextView;
    private lateinit var longitudeField:TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //element initialization
        button = findViewById(R.id.button);
        nameField = findViewById(R.id.namefield);
        addressField = findViewById(R.id.addressField);
        latitudeField = findViewById(R.id.latitudeField);
        longitudeField = findViewById(R.id.longitudeField);

        requestQueue = Volley.newRequestQueue(this)

        //click event listener
        button.setOnClickListener(View.OnClickListener {
            var jsonArrayResponse = JsonObjectRequest(Request.Method.GET,"https://api.namefake.com/",null,{ response ->
                nameField.text = response.getString("name");
                addressField.text = response.getString("address");
                latitudeField.text = response.getString("latitude");
                longitudeField.text = response.getString("longitude");
            },{err->
                println(err);
            })
            requestQueue.add(jsonArrayResponse);
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        TODO("remove all the queue here")
        requestQueue?.cancelAll(TAG);
    }


}