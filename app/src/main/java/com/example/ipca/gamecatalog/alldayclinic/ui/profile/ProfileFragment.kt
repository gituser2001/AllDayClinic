package com.example.ipca.gamecatalog.alldayclinic.ui.profile

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.profile
import com.google.firebase.auth.FirebaseAuth


import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

private var currentUser : profile? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val uid  = FirebaseAuth.getInstance().uid
        val db = Firebase.firestore
        //val reference = FirebaseFirestore.getInstance()
          //  .collection("users")
            //.document(uid.toString())

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_profile)
        val text_nome : TextView = root.findViewById(R.id.text_name)
        val text_idade : TextView = root.findViewById(R.id.text_age)


        val docRef = db.collection("users").document(uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("erro", "DocumentSnapshot data: ${document.data}")
                    currentUser = profile(
                        document.data?.getValue("nome").toString(),
                        document.data?.getValue("idade") as Int
                    )
                } else {
                    Log.d("erro", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("erro", "get failed with ", exception)
            }
        //reference.addSnapshotListener { querySnapshot, e ->

        text_nome.text = currentUser!!.nome.toString()
        text_idade.text ="Idade : " + currentUser!!.idade.toString()

        return root

    }


}

