package com.example.authorsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authorsapp.adapters.BooksAdapter
import com.example.authorsapp.databinding.ActivityMainBinding
import com.example.authorsapp.viewmodel.BooksViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private lateinit var mainActivityBinding: ActivityMainBinding

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        initRecyclerView()

        booksViewModel =
            ViewModelProvider(this).get(BooksViewModel::class.java)

        booksViewModel.getAllBooks()

        booksViewModel.getAllBooksInDB()?.observe(this, Observer {
            if(it?.size!! > 0){
                booksAdapter.setAuthorList(it)
            }
        })

        mainActivityBinding.addUserFab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,UserActivity::class.java)
            startActivity(intent)
        })
    }

    private fun initRecyclerView() {
        mainActivityBinding.authorsRecyclerview.layoutManager = LinearLayoutManager(this)
        booksAdapter = BooksAdapter(this)
        mainActivityBinding.authorsRecyclerview.adapter =booksAdapter
    }
}