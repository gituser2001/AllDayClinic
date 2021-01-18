package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ipca.gamecatalog.alldayclinic.MainActivity
import com.example.ipca.gamecatalog.alldayclinic.Message
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.profile
import com.example.ipca.gamecatalog.alldayclinic.ui.chat.NewMessageActivity.Companion.USER_KEY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_latest_message.*
import kotlinx.android.synthetic.main.chat_ultimas_mensagens.view.*
import kotlinx.android.synthetic.main.chat_ultimas_mensagens.view.username_textview_latest_message
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class LatestMessageActivity : AppCompatActivity() {
    companion object {

        var currentUser: profile? = null
    }

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_message)
        supportActionBar?.hide()

        var btn_nova = findViewById<Button>(R.id.btnmensagemnova)
        btn_nova.setOnClickListener {
            val intent = Intent(this, NewMessageActivity::class.java)
            startActivity(intent)
        }

        recyclerview_latest_messages.adapter = adapter
        recyclerview_latest_messages.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        adapter.setOnItemClickListener { item, view ->

            val intent = Intent(this, ChatLogActivity::class.java)
            val row = item as LatestMessageRow
            intent.putExtra(USER_KEY, row.chatPartnerUser)
            startActivity(intent)
        }

        listenForLatestMessages()

        fetchCurrentUser()

    }


    class LatestMessageRow(val chatMessage : Message) : Item<GroupieViewHolder>() {

        var chatPartnerUser: profile? = null

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {

            val latestMessage = viewHolder.itemView.findViewById<TextView>(R.id.latest_message_textview)
            val latestUsername = viewHolder.itemView.findViewById<TextView>(R.id.username_textview_latest_message)

            latestMessage.text = chatMessage.text.toString()

            val chatPartnerId: String
            if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {

                chatPartnerId = chatMessage.toId.toString()
            } else {

                chatPartnerId = chatMessage.fromId.toString()
            }

            val reference = FirebaseFirestore.getInstance()
                .collection("users")
                .document(chatPartnerId)

            reference.addSnapshotListener { querySnapshot, e ->

                if (querySnapshot != null) {

                    chatPartnerUser = profile(
                        querySnapshot.data?.getValue("uid").toString(),
                        querySnapshot.data?.getValue("nome").toString(),
                        querySnapshot.data?.getValue("dtaNasc").toString()
                    )

                    latestUsername.text = chatPartnerUser!!.nome.toString()
                }
            }
        }
        override fun getLayout(): Int {
            return R.layout.chat_ultimas_mensagens
        }
    }
    private fun listenForLatestMessages() {
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseFirestore.getInstance()
            .collection("latest_messages")
            .document(fromId.toString())
            .collection("latest_message")
            .orderBy("timeStamp", Query.Direction.DESCENDING)

        ref.addSnapshotListener { querySnapshot, e ->

            adapter.clear()
            if (querySnapshot != null) {

                for (d in querySnapshot) {

                    val chatMessage = Message(
                        d.data.getValue("text").toString(),
                        d.data.getValue("fromId").toString(),
                        d.data.getValue("toId").toString(),
                        d.data.getValue("timeStamp") as Long
                    )
                    adapter.add(LatestMessageRow(chatMessage))
                }
            }
        }
    }
    private fun fetchCurrentUser() {

        val uid = FirebaseAuth.getInstance().uid

        val reference = FirebaseFirestore.getInstance()
            .collection("users_chat")
            .document(uid.toString())



        reference.addSnapshotListener { querySnapshot, e ->

            if (querySnapshot != null) {

                currentUser = profile(
                    querySnapshot.data?.getValue("dtaNasc").toString(),
                    querySnapshot.data?.getValue("nome").toString(),
                    querySnapshot.data?.getValue("uid").toString()
                )
            }
        }
    }

}
