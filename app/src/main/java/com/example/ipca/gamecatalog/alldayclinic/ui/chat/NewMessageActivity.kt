package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ipca.gamecatalog.alldayclinic.R
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
        val recyclerview_new_message = findViewById<RecyclerView>(R.id.recyclerview_new_message)

        //adapter.add(??)

        recyclerview_new_message.adapter = adapter
    }
}

/*class profile : Item<RecyclerView.ViewHolder>()
{
    override fun bind(viewHolder: RecyclerView.ViewHolder, position: Int)
    {

    }

    override fun getLayout(): Int {
        return R.layout
    }
}*/