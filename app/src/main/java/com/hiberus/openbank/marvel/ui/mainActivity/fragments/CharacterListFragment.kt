package com.hiberus.openbank.marvel.ui.mainActivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiberus.openbank.marvel.MarvelApp
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.mainActivity.MainInterface
import com.hiberus.openbank.marvel.ui.mainActivity.MainViewModel
import com.hiberus.openbank.marvel.apiRest.restImplementation.MarvelCharacterServiceImpl
import com.hiberus.openbank.marvel.databinding.FragmentCharacterListBinding
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto

class CharacterListFragment : Fragment(), MainInterface {

    private val TAG = "CharacterListFragment"
    private lateinit var binding: FragmentCharacterListBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var firstTime: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_list, container, false)
         mainViewModel.menuModel.hasClickBack=false
        mainViewModel.mainInterface = this
        binding.mvm = mainViewModel


        //Refresh
        binding.swipeRefreshLayout.setOnRefreshListener {
            Log.d(TAG, "Refreshing Characters")
            mainViewModel.updateMarvelList(true)
        }

        //set adapter
        binding.recyclerCharacters.adapter = mainViewModel.mainModel.characterAdapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerCharacters.layoutManager = layoutManager

        //observe data in this fragment lifecycle
        mainViewModel.mainModel.marvelCharLD?.observe(
            requireActivity(),
            { characterList: List<CharacterWithItemsDto>? ->
                Log.d(TAG, "MarvelCharacter LD changed!->$characterList")
                mainViewModel.mainModel.marvelCharWithItemsList = characterList
                if (characterList.isNullOrEmpty()) {
                    if (firstTime) {
                        Log.d(TAG, "MarvelCharacter FIRST TIME SYNC")
                        mainViewModel.updateMarvelList(false)
                        firstTime = false
                    }


                }
            })

        return binding.root
    }

    //if from refresh, cancel it if refreshing, otherwise ignore. If not from refresh, set loading for activity
    override fun loading(isLoading: Boolean, fromRefresh: Boolean) {
        if (fromRefresh) {
            if (binding.swipeRefreshLayout.isRefreshing && !isLoading) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        } else {
            mainViewModel.mainModel.loading = isLoading
        }
    }

    override fun getCharactersError() {
        requireActivity().runOnUiThread {
            val toast = Toast.makeText(
                requireContext(),
                MarvelApp.get().getString(R.string.could_not_download_characters),
                Toast.LENGTH_LONG
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }


    }
}