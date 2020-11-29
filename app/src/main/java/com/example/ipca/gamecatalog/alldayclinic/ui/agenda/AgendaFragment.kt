package com.example.ipca.gamecatalog.alldayclinic.ui.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.consulta
import com.google.firebase.database.DatabaseReference

class AgendaFragment : Fragment() {


    private lateinit var ref : DatabaseReference
    private lateinit var listConst : MutableList<consulta>
    private lateinit var listConsultas : ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root = inflater.inflate(R.layout.fragment_agenda, container, false)
        
        return root
    }
}