package com.example.mvvm.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvm.Room.Dao.BookDao
import com.example.mvvm.Room.Entities.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Book::class], version = 5, exportSchema = false)
abstract  class LibraryDB:RoomDatabase() {

    abstract fun bookDao():BookDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDB? = null

        fun getInstance(appContext:Context, scope: CoroutineScope): LibraryDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(appContext, LibraryDB::class.java, "library_db")
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

        suspend fun populateDatabase(bookDao: BookDao){
            bookDao.deleteAllBooks()

            var book = Book("Harry Potter", "Santillana", 0)
            bookDao.insert(book)
            book = Book("Harry Potter 2", "Santillana", 1)
            bookDao.insert(book)

            book = Book("Maze Runner", "Santillana 2", 0)
            bookDao.insert(book)

            book = Book("La Llorona", "Mexicana", 1)
            bookDao.insert(book)

            book = Book("Eso", "Scare", 1)
            bookDao.insert(book)
        }
    }

}