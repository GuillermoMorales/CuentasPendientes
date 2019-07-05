package com.example.mvvm.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mvvm.Room.Dao.CuentaDao
import com.example.mvvm.Room.Entities.Cuenta

class BookRepository(private val cuentaDao: CuentaDao) {

    fun getAllBooks():LiveData<List<Cuenta>> = cuentaDao.getAllBooks()

    fun getAllFavoritesBooks():LiveData<List<Cuenta>> = cuentaDao.getFavoritesBooks(1)

    fun getBooksByName(name: String):LiveData<List<Cuenta>> = cuentaDao.getByName(name)

    @WorkerThread
    suspend fun insert(cuenta: Cuenta) = cuentaDao.insert(cuenta)

    @WorkerThread
    suspend fun updateFavorite(idBook:Int, favorite:Int) = cuentaDao.updateFavoriteState(idBook,favorite)

}