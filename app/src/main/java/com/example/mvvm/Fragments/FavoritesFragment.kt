package com.example.mvvm.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.Adapters.FavoritesAdapter

import com.example.mvvm.R
import com.example.mvvm.Room.Entities.Cuenta
import com.example.mvvm.ViewModel.ViewModel
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment() {

    lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        init(view)

        return view
    }

    fun init(view: View){
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        var adapter = object : FavoritesAdapter(view.context){
            override fun setClickListenerToFavoriteBook(holder: FavoriteViewHolder, cuenta: Cuenta) {
                holder.container.setOnClickListener {
                    val nextAction = FavoritesFragmentDirections.nextAction(cuenta.name,cuenta.editorial,cuenta.favorite)
                    Navigation.findNavController(it).navigate(nextAction)
                }
                holder.checkBox.setOnClickListener {
                    if (holder.checkBox.isChecked){
                        viewModel.updateFavorite(cuenta.id,1)
                    } else{
                        viewModel.updateFavorite(cuenta.id,0)
                    }
                }
            }
        }


        viewModel.allFavoritesCuenta.observe(this, Observer { books ->
            books?.let { adapter.setFavorites(it) }
        })
    }

}
