package com.example.ipca.gamecatalog.alldayclinic.ui.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.consulta
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AgendaFragment : Fragment() {


    private lateinit var listConst : consulta
    private lateinit var listConsultas : ListView
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_agenda, container, false)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val db = FirebaseFirestore.getInstance()
        listConsultas = root.findViewById(R.id.listaConsultas)
        currentUser!!.uid?.let {
            db.collection("Consulta").document(it)
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        querySnapshot?.data?.let {
                            listConst = consulta.fromHash(querySnapshot.data as HashMap<String, Any>)
                            listConst?.let{ consulta ->
                                val arratAdapter : ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_spinner_item)
                                listConsultas.adapter = arratAdapter
                            }
                        }
                    }
        }
        
        return root
    }
}