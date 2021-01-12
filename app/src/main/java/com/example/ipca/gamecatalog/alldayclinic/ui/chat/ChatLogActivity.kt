package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ipca.gamecatalog.alldayclinic.Message
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.profile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_chat_log.*
import kotlinx.android.synthetic.main.chat_mensagem_enviada.view.*
import kotlinx.android.synthetic.main.chat_mensagem_recebida.view.*

@RequiresApi(api = Build.VERSION_CODES.M)
class ChatLogActivity : AppCompatActivity() {
    var message : Message? = null
    val adapter = GroupAdapter<GroupieViewHolder>()

    var toUser : profile? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        toUser = intent.getParcelableExtra<profile>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = toUser?.nome

        recycle_message.adapter = adapter
        listenForMessages()

        btnenviar.setOnClickListener {

            performSendMessage()
        }

    }
    private fun listenForMessages() {

        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid

        val reference = FirebaseFirestore.getInstance()
            .collection("user_messages")
            .document(fromId.toString())
            .collection(toId.toString())
            .orderBy("timeStamp")

        reference.addSnapshotListener { querySnapshot, firebaseFirestoreException ->

            adapter.clear()

            if (querySnapshot != null) {

                for (d in querySnapshot) {

                    message = Message(
                        d.data.getValue("text").toString(),
                        d.data.getValue("fromId").toString(),
                        d.data.getValue("toId").toString(),
                        d.data.getValue("timeStamp") as Long)

                    if (message!!.fromId == FirebaseAuth.getInstance().uid) {

                        //val currentUser = LatestMessageActivity.currentUser

                        adapter.add(ChatToItem(message!!.text.toString()/*, currentUser!!*/))
                    }
                    else {

                        adapter.add(ChatFromItem(message!!.text.toString()/*, toUser!!*/))

                    }
                }
            }
            recycle_message.scrollToPosition(adapter.itemCount - 1)
        }
    }

    private fun performSendMessage() {

        val text = textmessage.text.toString()
        val fromId = FirebaseAuth.getInstance().uid
        val toId = toUser?.uid

        val reference = FirebaseFirestore.getInstance()
            .collection("user_messages")
            .document(fromId.toString())
            .collection(toId.toString())
        val toReference = FirebaseFirestore.getInstance()
            .collection("user_messages")
            .document(toId.toString())
            .collection(fromId.toString())

        val chatMessage = Message(text, fromId!!, toId!!, System.currentTimeMillis() / 1000)

        reference.add(chatMessage).addOnSuccessListener {

            textmessage.text.clear()
            recycle_message.scrollToPosition(adapter.itemCount - 1)
        }
        toReference.add(chatMessage)

        val latestMessageRef = FirebaseFirestore.getInstance()
            .collection("latest_messages")
            .document(fromId.toString())
            .collection("latest_message")
            .document(toId.toString())
        latestMessageRef.set(chatMessage)

        val latestMessageToRef = FirebaseFirestore.getInstance()
            .collection("latest_messages")
            .document(toId.toString())
            .collection("latest_message")
            .document(fromId.toString())
        latestMessageToRef.set(chatMessage)
    }
}

class ChatFromItem (val text : String/*, val user : profile*/) : Item<GroupieViewHolder>(){

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_from_row.text = text
    }

    override fun getLayout(): Int {

        return R.layout.chat_mensagem_recebida
    }
}

class ChatToItem (val text : String/*, val user : profile*/):Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.textview_to_row.text = text
    }

    override fun getLayout(): Int {

        return R.layout.chat_mensagem_enviada
    }
}