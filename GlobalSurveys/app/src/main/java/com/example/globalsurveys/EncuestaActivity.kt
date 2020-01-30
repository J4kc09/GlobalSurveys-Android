package com.globalsurveys.app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
//import com.globalSurveys.app.Encuesta
import com.globalsurveys.app.R
import com.google.gson.Gson

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

