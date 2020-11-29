package com.example.ipca.gamecatalog.alldayclinic.ui.chat


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ipca.gamecatalog.alldayclinic.MainActivity
import com.example.ipca.gamecatalog.alldayclinic.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ChatFragment : Fragment() {
    private lateinit var auth : FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //var view: View = inflater.inflate(R.layout.chat_ultimas_mensagens.container.false)
        super.onCreate(savedInstanceState)

        //setC(R.layout.news_articles)

        auth = Firebase.auth
        val currentUser = auth.currentUser
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        val textView: TextView = root.findViewById(R.id.text_chat)













        //val swiperefresh : SwipeRefreshLayout = root.findViewById(R.id.swiperefresh)
            //swiperefresh.setOnRefreshListener {
            //     fetchCurrentUser()
            //   listenForLatestMessages()
            //}

        //Não dá override na função e o botão de logout para já não faz nada
        /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item?.itemId)
            {
                R.id.menu_signout ->{
                    FirebaseAuth.getInstance().signOut()
                }
            }
            return super.onOptionsItemSelected(item)
        }*/

        return root
    }
}