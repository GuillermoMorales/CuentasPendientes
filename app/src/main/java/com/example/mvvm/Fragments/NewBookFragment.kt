package com.example.mvvm.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.mvvm.R
import com.example.mvvm.Room.Entities.Book
import com.example.mvvm.ViewModel.ViewModel
import kotlinx.android.synthetic.main.fragment_new_book.*
import java.lang.Exception

class NewBookFragment : Fragment() {

    lateinit var viewModel:ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        save_book.setOnClickListener {
            val book = Book(txtName.text.toString(), txtEditorial.text.toString(), 0)

            try {
                viewModel.insertBook(book)
                //viewModel.insertBookAuthor(bookAuthor)
                Log.d("CODIGO", "Creado con exito")
                txtName.setText("")
                txtEditorial.setText("")
                Toast.makeText(view.context, "Book created!!", Toast.LENGTH_LONG).show()
            } catch (e:Exception){
                Log.d("CODIGO", e.message)
            }
        }
    }

}
