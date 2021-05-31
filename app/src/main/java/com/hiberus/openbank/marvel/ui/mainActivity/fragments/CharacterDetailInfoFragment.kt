package com.hiberus.openbank.marvel.ui.mainActivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.mainActivity.MainViewModel
import com.hiberus.openbank.marvel.databinding.FragmentCharacterDetailInfoBinding

class CharacterDetailInfoFragment : Fragment() {

    private val TAG = "CharacterListFragment"
    private lateinit var binding: FragmentCharacterDetailInfoBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail_info, container, false)
         binding.mvm = mainViewModel
        Glide.with(binding.imgMarvelChar)
            .load(mainViewModel!!.charDetailModel.selectedCharacterWithItemsDto?.marvelCharacter!!.picture)
            .placeholder(R.drawable.marvel_unknown_hero)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgMarvelChar)
        return binding.root
    }


}