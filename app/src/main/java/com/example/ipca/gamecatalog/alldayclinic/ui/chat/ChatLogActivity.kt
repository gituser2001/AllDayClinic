package com.example.ipca.gamecatalog.alldayclinic.ui.chat

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ipca.gamecatalog.alldayclinic.R
import com.example.ipca.gamecatalog.alldayclinic.profile
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

@RequiresApi(api = Build.VERSION_CODES.M)
class ChatLogActivity : AppCompatActivity() {
    val adapter = GroupAdapter<GroupieViewHolder>()

    var toUser : profile? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_log)

        toUser = intent.getParcelableExtra<profile>(NewMessageActivity.USER_KEY)
        supportActionBar?.title = toUser?.nome

    }
}