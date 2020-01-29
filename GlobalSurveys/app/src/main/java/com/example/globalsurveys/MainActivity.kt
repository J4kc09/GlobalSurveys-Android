package com.example.globalsurveys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonElement

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


            fun sendJson (view: View){
                val textView = findViewById<TextView>(R.id.textView)

                var et_user_name = findViewById(R.id.et_user_name) as EditText
                var et_password = findViewById(R.id.et_password) as EditText
                var btn_submit = findViewById(R.id.btn_submit) as Button

                val user_name = et_user_name.text
                val password = et_password.text

                if (user_name.isEmpty() || password.isEmpty()){
                    textView.text = "Debe introducir un usuario y una contraseña"
                }
                else {

                    // Instantiate the RequestQueue.
                    var queue = Volley.newRequestQueue(this)
                    var url =
                        "http://192.168.1.103:8080/GlobalSurveys/webresources/rest/login/" + user_name + "/" + password

                    // Request a string response from the provided URL.

                    //stringRequest

                    val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener
                    { response ->

                        val gson = Gson()
                        val loginDto: LoginDto =
                            gson.fromJson(response.toString(), LoginDto::class.java);
                        //if (loginDto.getCorrecto()=="true"){
                        if (loginDto.getCorrecto()) {
                            textView.text = "${response}"
                        } else {
                            textView.text = "El usuario o contraseña introducidos es incorrecto"
                        }

                    },
                        Response.ErrorListener { textView.text = "Ha ocurrido un error" })
                    queue.add(request)
                }






            }
}