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

class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel
    var array = arrayOf("Teste", "Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste","Teste")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        agendaViewModel =
            ViewModelProvider(this).get(AgendaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_agenda, container, false)
        val textView: TextView = root.findViewById(R.id.text_agenda)
        agendaViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it

        })
        
        return root
    }
}