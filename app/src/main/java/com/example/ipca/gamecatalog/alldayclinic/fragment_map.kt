package com.example.ipca.gamecatalog.alldayclinic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import com.google.android.gms.maps.SupportMapFragment

class fragment_map : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root : View = inflater.inflate(R.layout.fragment_map, container, false)

        val supportMapFragment : SupportMapFragment

        return root
    }


}