package com.example.mvvm.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.Room.Entities.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: Book)

    @Query("select * from book")
    fun getAllBooks():LiveData<List<Book>>

    @Query("delete from book")
    fun deleteAllBooks()

    @Query("select * from book where favorite=:favorite")
    fun getFavoritesBooks(favorite:Int):LiveData<List<Book>>

    @Query("update book set favorite = :favorite where id = :id")
    suspend fun updateFavoriteState(id:Int, favorite:Int)

    @Query("select * from book where name like :nameBook")
    fun getByName(nameBook: String):LiveData<List<Book>>

}