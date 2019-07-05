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

abstract class FavoritesAdapter internal  constructor(context: Context): RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>(){

    private val inflater = LayoutInflater.from(context)
    private var books = emptyList<Book>()

    abstract fun setClickListenerToFavoriteBook(holder: FavoriteViewHolder, book: Book)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemView = inflater.inflate(R.layout.book_info,parent,false)
        return FavoriteViewHolder(itemView)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentBook = books[position]

        holder.name.text = currentBook.name
        holder.editorial.text = currentBook.editorial
        holder.checkBox.isChecked = true

        setClickListenerToFavoriteBook(holder,currentBook)

    }

    internal fun setFavorites(book: List<Book>){
        this.books = book
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name:TextView = itemView.name
        val editorial:TextView = itemView.editorial
        val container:LinearLayout = itemView.bookContainer
        val checkBox:CheckBox = itemView.checkbox_book
    }
}