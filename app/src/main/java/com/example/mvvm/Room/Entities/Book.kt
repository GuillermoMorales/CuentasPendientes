package com.example.mvvm.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(


    @ColumnInfo(name = "name")
    var name:String,

    @ColumnInfo(name = "editorial")
    var editorial:String,

    @ColumnInfo(name = "favorite")
    var favorite:Int = 0
){

    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}