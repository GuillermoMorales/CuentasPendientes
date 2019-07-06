package com.example.mvvm.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvm.Room.Dao.CuentaDao
import com.example.mvvm.Room.Entities.Cuenta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Cuenta::class], version = 5, exportSchema = false)
abstract  class CuentaDB:RoomDatabase() {

    abstract fun bookDao():CuentaDao

    companion object {
        @Volatile
        private var INSTANCE: CuentaDB? = null

        fun getInstance(appContext:Context, scope: CoroutineScope): CuentaDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(appContext, CuentaDB::class.java, "library_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class DatabaseCallback(private val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                scope.launch(Dispatchers.IO){
                    populateDatabase(it.bookDao())
                }
            }
        }

        suspend fun populateDatabase(cuentaDao: CuentaDao){
           // cuentaDao.deleteAllBooks()



            var cuenta = Cuenta("Cuenta Super", "100.4", 0)
            cuentaDao.insert(cuenta)
            cuenta= Cuenta("Cuenta Banco Agricola", "251", 1)
            cuentaDao.insert(cuenta)

            cuenta= Cuenta("Cuenta Carro", "451", 0)
            cuentaDao.insert(cuenta)

            cuenta= Cuenta("Cuenta Universidad", "123", 1)
            cuentaDao.insert(cuenta)

            cuenta= Cuenta("Cuenta Alquiler", "12.01", 1)
            cuentaDao.insert(cuenta)
        }
    }

}