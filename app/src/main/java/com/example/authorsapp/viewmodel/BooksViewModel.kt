package com.example.authorsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.authorsapp.database.Author
import com.example.authorsapp.database.User
import com.example.authorsapp.rawdata.Books
import com.example.authorsapp.repository.BooksRepository

class BooksViewModel(application: Application) : AndroidViewModel(application) {

    private var books : MutableLiveData<Books?> = MutableLiveData()
    private val booksRepository = BooksRepository()

    fun getLiveDataObserver(): MutableLiveData<Books?> {
        return books
    }

    fun getAllBooks() {
        booksRepository.makeAPICall(getApplication<Application>().applicationContext)
    }

    fun getAllBooksInDB(): LiveData<List<Author?>?>? {
        return booksRepository.getAuthorsFromDB(getApplication<Application>().applicationContext)
    }

    fun getUser(): LiveData<User>? {
        return booksRepository.getUserFromDb(getApplication<Application>().applicationContext)
    }

    fun saveUser(user:User){
        booksRepository.saveUserInDb(user)
    }
}