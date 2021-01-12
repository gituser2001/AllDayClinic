package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.profile
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        supportActionBar?.hide()

        val adapter = GroupAdapter<GroupieViewHolder>()
        val recyclerViewNewMessage = findViewById<RecyclerView>(R.id.recyclerview_new_message)

        recyclerViewNewMessage.adapter = adapter

        fetchUsers()
    }

    companion object {

        val USER_KEY = "USER_KEY"
    }

    private fun fetchUsers()
    {
        val ref = FirebaseFirestore.getInstance().collection("users")
        ref.addSnapshotListener { value, error ->
                val adapter = GroupAdapter<GroupieViewHolder>()
                val recyclerViewNewMessage = findViewById<RecyclerView>(R.id.recyclerview_new_message)
                if (value != null ){
                    for (doc in value ){
                        val user = profile(doc.data.getValue("uid").toString(),
                                           doc.data.getValue("nome").toString(),
                                           doc.data.getValue("dtaNasc").toString())
                        adapter.add(UserItem(user))
                    }
                }

            adapter.setOnItemClickListener { item, view ->

                val userItem = item as UserItem

                val intent = Intent(view.context, ChatLogActivity::class.java)
                intent.putExtra(USER_KEY, userItem.user )
                startActivity(intent)

                finish()
            }
                recyclerViewNewMessage.adapter = adapter
        }
    }
}


class UserItem(val user: profile) : Item<GroupieViewHolder>()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int)
    {
        val textview = viewHolder.itemView.findViewById<TextView>(R.id.textView)
        textview.text = user.nome
    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}