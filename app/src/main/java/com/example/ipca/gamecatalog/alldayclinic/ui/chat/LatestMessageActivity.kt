package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.ipca.gamecatalog.alldayclinic.MainActivity
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

class LatestMessageActivity : AppCompatActivity() {
    companion object {

        var currentUser: profile? = null
    }

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_message)

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

    //Instruções após clicar em algum dos botões
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_message_button -> {
                val intent = Intent(this, NewMessageActivity::class.java)
                startActivity(intent)
            }
            R.id.signout_button -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //criar o menu na parte de cima da app
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    class LatestMessageRow(val chatMessage: com.example.ipca.gamecatalog.alldayclinic.Message) :
        Item<GroupieViewHolder>() {

        var chatPartnerUser: profile? = null

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {

            viewHolder.itemView.username_textview_latest_message.text = chatMessage.text

            val chatPartnerId: String
            if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {

                chatPartnerId = chatMessage.toId.toString()
            } else {

                chatPartnerId = chatMessage.fromId.toString()
            }

            val reference = FirebaseFirestore.getInstance()
                .collection("users_chat")
                .document(chatPartnerId)

            reference.addSnapshotListener { querySnapshot, e ->

                if (querySnapshot != null) {

                    chatPartnerUser = profile(
                        querySnapshot.data?.getValue("dtaNasc").toString(),
                        querySnapshot.data?.getValue("nome").toString(),
                        querySnapshot.data?.getValue("uid").toString()
                    )

                    viewHolder.itemView.username_textview_latest_message.text =
                        chatPartnerUser!!.nome

                }
            }
        }
        override fun getLayout(): Int {
            return R.layout.activity_latest_message
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

            if (querySnapshot != null) {

                for (d in querySnapshot) {

                    val chatMessage = com.example.ipca.gamecatalog.alldayclinic.Message(
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
