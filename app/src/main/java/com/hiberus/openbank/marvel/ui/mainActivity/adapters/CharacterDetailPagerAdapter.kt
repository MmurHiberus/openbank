package com.hiberus.openbank.marvel.ui.mainActivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hiberus.openbank.marvel.ui.mainActivity.fragments.CharacterDetailInfoFragment
import com.hiberus.openbank.marvel.ui.mainActivity.fragments.CharacterDetailItemsFragment

class CharacterDetailPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CharacterDetailInfoFragment()
            }
            1 -> {
                CharacterDetailItemsFragment()
            }
            else -> {
                CharacterDetailInfoFragment()
            }

        }
    }


}