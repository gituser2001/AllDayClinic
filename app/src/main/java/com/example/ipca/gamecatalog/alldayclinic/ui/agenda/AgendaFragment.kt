package com.example.ipca.gamecatalog.alldayclinic.ui.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.consulta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AgendaFragment : Fragment() {


    private lateinit var listConst : consulta
    private lateinit var listViewConsultas : ListView
    private lateinit var auth : FirebaseAuth
    private var listConsultas : MutableList<consulta> = arrayListOf()
    var consultaAdapter : BaseAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_agenda, container, false)
        consultaAdapter = ConsultaAdapter()
        listViewConsultas = root.findViewById(R.id.listaConsultas)
        listViewConsultas.adapter = consultaAdapter
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = FirebaseFirestore.getInstance()

        currentUser!!.uid?.let {
            db.collection("Consulta")
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        querySnapshot?.let {
                            listConsultas.clear()
                            for (doc in querySnapshot){
                                listConst = consulta.fromHash(doc.data as HashMap<String, Any>)
                                if (currentUser.uid == listConst.idUser){
                                    listConsultas.add(listConst)
                                }
                            }
                            consultaAdapter?.notifyDataSetChanged()
                        }
                    }
        }
        
        return root
    }
    inner class ConsultaAdapter : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var rowView = layoutInflater.inflate(R.layout.rowconsulta, parent, false)

            val textViewTitle = rowView.findViewById<TextView>(R.id.titleConsulta)
            val textViewSubTitle = rowView.findViewById<TextView>(R.id.subtitleConsulta)

            textViewTitle.text = listConsultas[position].tipoConsulta
            textViewSubTitle.text = "Sala : "+ listConsultas[position].sala


//            rowView.setOnClickListener {
//
//                val intent = Intent(this@MainActivity, GameDetailActivity::class.java)
//                intent.putExtra("Game Name", games[position].gameName)
//                intent.putExtra("Company Name", games[position].companyName)
//                intent.putExtra("Score", games[position].score)
//                startActivity(intent)
//            }

            return rowView
        }

        override fun getItem(position: Int): Any {
            return listConsultas[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return listConsultas.size
        }
    }
}