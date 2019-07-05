package com.example.mvvm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.Repositories.BookRepository
import com.example.mvvm.Room.Entities.Book
import com.example.mvvm.Room.LibraryDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(app:Application): AndroidViewModel(app) {

    private val bookRepository:BookRepository
    val allBooks: LiveData<List<Book>>
    val allFavoritesBook: LiveData<List<Book>>

    init {
        val bookDao = LibraryDB.getInstance(app, viewModelScope).bookDao()
        bookRepository = BookRepository(bookDao)
        allBooks = bookRepository.getAllBooks()
        allFavoritesBook = bookRepository.getAllFavoritesBooks()
    }


    fun insertBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.insert(book)
    }

    fun updateFavorite(id: Int, favorite:Int) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.updateFavorite(id,favorite)
    }

}