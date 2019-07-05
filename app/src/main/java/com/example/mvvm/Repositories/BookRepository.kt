package com.example.mvvm.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mvvm.Room.Dao.BookDao
import com.example.mvvm.Room.Entities.Book

class BookRepository(private val bookDao: BookDao) {

    fun getAllBooks():LiveData<List<Book>> = bookDao.getAllBooks()

    fun getAllFavoritesBooks():LiveData<List<Book>> = bookDao.getFavoritesBooks(1)

    fun getBooksByName(name: String):LiveData<List<Book>> = bookDao.getByName(name)

    @WorkerThread
    suspend fun insert(book: Book) = bookDao.insert(book)

    @WorkerThread
    suspend fun updateFavorite(idBook:Int, favorite:Int) = bookDao.updateFavoriteState(idBook,favorite)

}