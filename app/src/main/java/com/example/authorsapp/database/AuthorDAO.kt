package com.example.authorsapp.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AuthorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthor(author: Author?)

    @Query("DELETE FROM author")
    fun deleteAllAuthors();

    @Query("SELECT * FROM author")
    fun loadAllAuthors(): LiveData<List<Author?>?>?

    @Query("SELECT * FROM author where name = :name")
    fun findAuthorsInDb(name:String?): Author

    @Update
    suspend fun updateAuthor(author: Author)
}