package com.example.authorsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authorsapp.adapters.AuthorBooksAdapter
import com.example.authorsapp.databinding.ActivityAuthorbooksBinding

class AuthorBooksActivity : AppCompatActivity() {

    private lateinit var authorBooksAdapter: AuthorBooksAdapter
    private lateinit var authorBooksBinding: ActivityAuthorbooksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authorBooksBinding = ActivityAuthorbooksBinding.inflate(layoutInflater)
        setContentView(authorBooksBinding.root)

        supportActionBar?.title = "Books"

        val authorBooks: ArrayList<String>? = intent.getStringArrayListExtra("authorbooks")
        authorBooksBinding.authorBooksRecyclerview.layoutManager = LinearLayoutManager(this)
        authorBooksAdapter = AuthorBooksAdapter(this)
        if (authorBooks != null) {
            authorBooksAdapter.setAuthorBooksList(authorBooks)
        }
        authorBooksBinding.authorBooksRecyclerview.adapter = authorBooksAdapter
    }
}