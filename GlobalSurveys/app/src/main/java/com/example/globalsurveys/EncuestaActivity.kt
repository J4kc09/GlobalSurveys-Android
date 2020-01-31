package com.globalsurveys.app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
//import com.globalSurveys.app.Encuesta
import com.globalsurveys.app.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_encuesta.*

class EncuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)
        val listView = findViewById<ListView>(R.id.listView)
        val p = intent.extras
        val e = p!!.getString("key")
        val en = e?.substring(e?.indexOf("["), e?.indexOf("]") +1)
        val encuestas = parse_json(en.toString())
        listView.adapter = Adaptador(this, encuestas)
        /*textView.text = en*/

        val swiperefresh = findViewById<SwipeRefreshLayout>(R.id.swiperefresh)
        swiperefresh.setColorSchemeResources(R.color.colorPrimary, R.color.refresh);

        swiperefresh.setOnRefreshListener {

            val user_name = intent.getStringExtra("nombre")
            val password = intent.getStringExtra("contrasena")

            Toast.makeText(this, "Encuestas Actualizadas", Toast.LENGTH_LONG).show()

                    val queue = Volley.newRequestQueue(this)
                    val url =
                        "http://192.168.8.11:8080/GlobalSurveys/webresources/rest/login/" + user_name + "/" + password

                    val request = JsonObjectRequest(
                        Request.Method.GET, url, null, Response.Listener
                        { response ->

                            val gson = Gson()
                            val loginDto: LoginDto =
                                gson.fromJson(response.toString(), LoginDto::class.java);

                                val encuestas = Bundle()
                                encuestas.putString("key", response.toString())

                                val i = Intent(this@EncuestaActivity, EncuestaActivity::class.java)
                                i.putExtras(encuestas)
                                i.putExtra("nombre", user_name);
                                i.putExtra("contrasena", password);
                                startActivity(i)

                        },
                        Response.ErrorListener {})
                    queue.add(request)

            swiperefresh.setRefreshing(false);

        }
    }

    fun parse_json(archivo: String): List<EncuestaDto> {
        val gson = Gson()
        val encuestas: List<EncuestaDto> = gson.fromJson(archivo, Array<EncuestaDto>::class.java).toList()
        return encuestas

    }

    private class Adaptador(context: Context, encuestas: List<EncuestaDto>) : BaseAdapter() {
        private val mContext: Context
        private val encuestas: List<EncuestaDto>

        init {
            mContext = context
            this.encuestas = encuestas
        }

        override fun getCount(): Int {
            return encuestas.size
        }

        override fun getItem(position: Int): Any {
            return "Encuestas"
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val fila = layoutInflater.inflate(R.layout.fila, parent, false)
            val nomEncuesta = fila.findViewById<TextView>(R.id.titulo)
            val descripcionEncuesta = fila.findViewById<TextView>(R.id.descripcion)
            nomEncuesta.text = encuestas[position].nomEncuesta
            descripcionEncuesta.text = encuestas[position].descripcionEncuesta
            return fila
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }

}

