package com.example.ipca.gamecatalog.alldayclinic.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ProfileFragment : Fragment() {

private var currentUser : profile? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uid  = FirebaseAuth.getInstance().uid
        val db = FirebaseFirestore.getInstance()
        //val reference = FirebaseFirestore.getInstance()
            //.collection("users")
           // .document(uid.toString())

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val text_nome : TextView = root.findViewById(R.id.text_name)
        val text_idade : TextView = root.findViewById(R.id.text_age)


        db.collection("users").document(uid.toString())
                .get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        currentUser = profile(
                                document.data?.getValue("name").toString(),
                                document.data?.getValue("dta_nasc") as Int
                        )
                    }
                }
                .addOnFailureListener { exception ->
                }

        /*reference.addSnapshotListener { querySnapshot, e ->

            if (querySnapshot != null) {

                currentUser = profile(
                        querySnapshot.data?.getValue("name").toString(),
                        querySnapshot.data?.getValue("dta_nasc") as Int
                )
            }
        }*/

        text_nome.setText(currentUser!!.getName()!!.toString())
        text_idade.setText("Idade : " + (2020- currentUser!!.getDtaNasc()!!).toString())

        return root

    }


}

