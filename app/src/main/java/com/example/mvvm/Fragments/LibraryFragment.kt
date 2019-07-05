package com.example.mvvm.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.Adapters.LibraryAdapter

import com.example.mvvm.R
import com.example.mvvm.Room.Entities.Book
import com.example.mvvm.ViewModel.ViewModel
import kotlinx.android.synthetic.main.fragment_library.view.*

class LibraryFragment : Fragment() {

    lateinit var viewModel: ViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        init(view)



        return view
    }

    fun init(view: View){
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        var adapter = object : LibraryAdapter(view.context){
            override fun setClickListenerToBook(holderLibrary: LibraryViewHolder, book: Book) {
                holderLibrary.container.setOnClickListener {
                    val nextAction = LibraryFragmentDirections.nextAction(book.name,book.editorial,book.favorite)
                    Navigation.findNavController(it).navigate(nextAction)
                }
                holderLibrary.checkBox.setOnClickListener {
                    if (holderLibrary.checkBox.isChecked){
                        viewModel.updateFavorite(book.id,1)
                    } else{
                        viewModel.updateFavorite(book.id,0)
                    }
                }
            }

        }
        val recyclerView = view.recyclerviewLibrary
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        viewModel.allBooks.observe(this, Observer { books ->
            books?.let { adapter.setBooks(it) }
        })
    }




}
