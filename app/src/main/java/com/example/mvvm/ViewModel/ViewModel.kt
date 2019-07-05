package com.example.mvvm.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm.Repositories.BookRepository
import com.example.mvvm.Room.Entities.Cuenta
import com.example.mvvm.Room.CuentaDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(app:Application): AndroidViewModel(app) {

    private val bookRepository:BookRepository
    val allBooks: LiveData<List<Cuenta>>
    val allFavoritesCuenta: LiveData<List<Cuenta>>

    init {
        val bookDao = CuentaDB.getInstance(app, viewModelScope).bookDao()
        bookRepository = BookRepository(bookDao)
        allBooks = bookRepository.getAllBooks()
        allFavoritesCuenta = bookRepository.getAllFavoritesBooks()
    }


    fun insertBook(cuenta: Cuenta) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.insert(cuenta)
    }

    fun updateFavorite(id: Int, favorite:Int) = viewModelScope.launch(Dispatchers.IO){
        bookRepository.updateFavorite(id,favorite)
    }

}