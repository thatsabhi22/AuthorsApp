package com.example.authorsapp.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.authorsapp.database.User
import com.example.authorsapp.databinding.ActivityUserBinding
import com.example.authorsapp.viewmodel.BooksViewModel

class UserActivity : AppCompatActivity() {

    private lateinit var userActivityBinding: ActivityUserBinding
    private lateinit var booksViewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userActivityBinding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(userActivityBinding.root)

        supportActionBar?.title = "User Details"

        booksViewModel =
            ViewModelProvider(this).get(BooksViewModel::class.java)

        booksViewModel.getUser()?.observe(this, Observer {
            if(it != null) {
                userActivityBinding.user = User(1,it.name,it.age,it.email)
            }
        })

        userActivityBinding.saveUserBtn.setOnClickListener(View.OnClickListener {
            val userName = userActivityBinding.editTextName.text
            val userAge = userActivityBinding.editTextAge.text
            val userEmail = userActivityBinding.editTextEmailAddress.text

            if(TextUtils.isEmpty(userName)){
                userActivityBinding.editTextName.error = "Please Enter your name"
            } else if (TextUtils.isEmpty(userAge.toString())){
                userActivityBinding.editTextAge.error = "Please Enter the age"
            } else if (Integer.parseInt(userAge.toString()) < 10 || Integer.parseInt(userAge.toString()) > 100){
                userActivityBinding.editTextAge.error = "Please Enter the valid age"
            } else if (!isValidEmail(userEmail)){
                userActivityBinding.editTextEmailAddress.error = "Please Enter your email"
            } else {
                val user = User(1,userName.toString(),userAge.toString(),userEmail.toString())
                booksViewModel.saveUser(user)
                Toast.makeText(this,"Your data saved..",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}