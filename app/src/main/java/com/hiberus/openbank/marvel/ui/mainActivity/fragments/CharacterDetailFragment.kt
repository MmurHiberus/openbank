package com.hiberus.openbank.marvel.ui.mainActivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.mainActivity.MainViewModel
import com.hiberus.openbank.marvel.ui.mainActivity.adapters.CharacterDetailPagerAdapter
import com.hiberus.openbank.marvel.databinding.FragmentCharacterDetailBinding

//Fragment used as frame with viewpager for INFO and ITEMS fragments
class CharacterDetailFragment : Fragment() {

    private val TAG = "CharacterListFragment"
    private lateinit var binding: FragmentCharacterDetailBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var characterDetailPagerAdapter: CharacterDetailPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false)
          mainViewModel.menuModel.hasClickBack=true

        binding.mvm = mainViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        characterDetailPagerAdapter = CharacterDetailPagerAdapter(
            requireParentFragment().parentFragmentManager,
            requireParentFragment().lifecycle
        )
        viewPager = binding.pager
        viewPager.adapter = characterDetailPagerAdapter
        TabLayoutMediator(binding.tabLayout,viewPager) { tab, position ->
         when(position){
             0->{
                 tab.text = getString(R.string.info)
             }
             1->{
                 tab.text = getString(R.string.Items)}
             else->{
                 Log.d(TAG, "TAB NOT SUPPORTED")
             }
         }
        }.attach()

    }
}