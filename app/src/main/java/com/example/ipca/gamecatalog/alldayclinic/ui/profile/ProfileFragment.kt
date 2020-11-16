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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {
private lateinit var auth : FirebaseAuth
    var listUser: profile? = null
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
        auth = Firebase.auth
        val currentUser = auth.currentUser


        currentUser!!.uid?.let {
            db.collection("users").document(it)
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        querySnapshot?.data?.let {
                            listUser = profile.fromHash(querySnapshot.data as HashMap<String, Any?>)
                            listUser?.let{ user ->
                                text_nome.setText(user.nome)
                            }
                        }




                        }
                    }

        /*reference.addSnapshotListener { querySnapshot, e ->

            if (querySnapshot != null) {

                currentUser = profile(
                        querySnapshot.data?.getValue("name").toString(),
                        querySnapshot.data?.getValue("dta_nasc") as Int
                )
            }
        }*/

        /*text_nome.setText(currentUser!!.getName()!!.toString())
        text_idade.setText("Idade : " + (2020- currentUser!!.getDtaNasc()!!).toString())*/

        return root

    }


}

