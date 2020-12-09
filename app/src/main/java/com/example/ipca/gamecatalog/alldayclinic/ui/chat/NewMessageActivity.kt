package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.ipca.gamecatalog.alldayclinic.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class NewMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        //Título da activity
        supportActionBar?.title = "Escolha um utilizador"


        val adapter = GroupAdapter<GroupieViewHolder>()
        val recyclerViewNewMessage = findViewById<RecyclerView>(R.id.recyclerview_new_message)

        adapter.add(UserItem("José"))
        adapter.add(UserItem("Nando"))
        adapter.add(UserItem("Berto"))


        recyclerViewNewMessage.adapter = adapter

        fetchUsers()
    }

    private fun fetchUsers()
    {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()
                val recyclerViewNewMessage = findViewById<RecyclerView>(R.id.recyclerview_new_message)

                p0.children.forEach{
                Log.d("NewMessage", it.toString())
                val user = it.getValue(UserItem::class.java)
                    if (user != null) {
                        adapter.add(UserItem("João"))
                        adapter.add(UserItem("Henrique"))
                    }
                }
                recyclerViewNewMessage.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}

class UserItem(val user: String) : Item<GroupieViewHolder>()
{
    override fun bind(viewHolder: GroupieViewHolder, position: Int)
    {

    }

    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}