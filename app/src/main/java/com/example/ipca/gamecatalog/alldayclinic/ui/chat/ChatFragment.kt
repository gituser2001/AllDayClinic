package com.example.ipca.gamecatalog.alldayclinic.ui.chat


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ipca.gamecatalog.alldayclinic.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {
    private lateinit var auth : FirebaseAuth


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //var view: View = inflater.inflate(R.layout.chat_ultimas_mensagens.container.false)
        super.onCreate(savedInstanceState)
        //setC(R.layout.news_articles)



        auth = Firebase.auth
        val currentUser = auth.currentUser
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        val textView: TextView = root.findViewById(R.id.text_chat)
            //val swiperefresh : SwipeRefreshLayout = root.findViewById(R.id.swiperefresh)

         //   swiperefresh.setOnRefreshListener {
           //     fetchCurrentUser()
             //   listenForLatestMessages()
            //}

        return root

    }


}