package com.example.mvvm.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.Room.Entities.Book
import kotlinx.android.synthetic.main.book_info.view.*

abstract  class LibraryAdapter internal constructor(context: Context): RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private var books = emptyList<Book>()

    abstract fun setClickListenerToBook(holderLibrary: LibraryViewHolder, book: Book)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val itemView = inflater.inflate(R.layout.book_info, parent, false)
        return LibraryViewHolder(itemView)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holderLibrary: LibraryViewHolder, position: Int) {
        val currentBook = books[position]

        holderLibrary.name.text = currentBook.name
        holderLibrary.editorial.text = currentBook.editorial

        if (currentBook.favorite==1){
            holderLibrary.checkBox.isChecked = true
        }

        setClickListenerToBook(holderLibrary,currentBook)
    }

    internal fun setBooks(books:List<Book>){
        this.books = books
        notifyDataSetChanged()
    }

    inner class LibraryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.name
        val editorial:TextView = itemView.editorial
        val container:LinearLayout = itemView.bookContainer
        val checkBox:CheckBox = itemView.checkbox_book
    }

}