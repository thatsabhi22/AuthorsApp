package com.example.authorsapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.authorsapp.api.ApiInterface
import com.example.authorsapp.api.RetrofitInstance
import com.example.authorsapp.database.Author
import com.example.authorsapp.database.AuthorsDatabase
import com.example.authorsapp.database.User
import com.example.authorsapp.rawdata.Books
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository() {

    lateinit var authorsDatabase: AuthorsDatabase

    final val books = MutableLiveData<Books?>()
    fun makeAPICall(applicationContext: Context): MutableLiveData<Books?> {
        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        authorsDatabase = AuthorsDatabase.getInstance(applicationContext)!!
        val apiService = retrofitInstance.create(ApiInterface::class.java)
        val call = apiService.getBooksData()
        call.enqueue(object : Callback<Books?> {
            override fun onResponse(call: Call<Books?>, response: Response<Books?>) {
                val authorResponse = response.body()
                authorResponse.let { books1 ->
                    val authorList = books1?.results
                    authorList.let { resultList ->
                        GlobalScope.launch {
                            authorsDatabase.authorDAO()?.deleteAllAuthors()
                            resultList?.forEach {
                                val authorName = it.authors[0].name
                                val bookTitle = it.title
                                val bookList = ArrayList<String>()
                                bookList.add(bookTitle)
                                val authorInDb =
                                    authorsDatabase.authorDAO()?.findAuthorsInDb(authorName)
                                if (authorInDb != null) {
                                    authorInDb.authorBooks?.add(bookTitle)
                                    authorsDatabase.authorDAO()?.updateAuthor(authorInDb)
                                } else {
                                    val author = Author(name = authorName, authorBooks = bookList)
                                    authorsDatabase.authorDAO()?.insertAuthor(author)
                                }
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Books?>, t: Throwable) {
                books.postValue(null)
            }
        })
        return books
    }

    fun getAuthorsFromDB(applicationContext: Context): LiveData<List<Author?>?>? {
        authorsDatabase = AuthorsDatabase.getInstance(applicationContext)!!
        return authorsDatabase.authorDAO()?.loadAllAuthors()
    }

    fun getUserFromDb(applicationContext: Context?): LiveData<User>? {
        authorsDatabase = applicationContext?.let { AuthorsDatabase.getInstance(it) }!!
        return authorsDatabase.userDAO()?.getUserInDb()
    }

    fun saveUserInDb(user: User) {
        GlobalScope.launch {
            authorsDatabase.userDAO()?.insertUser(user)
        }
    }
}