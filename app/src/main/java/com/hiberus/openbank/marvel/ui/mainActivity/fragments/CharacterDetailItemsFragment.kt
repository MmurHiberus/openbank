package com.hiberus.openbank.marvel.ui.mainActivity.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.mainActivity.MainViewModel
import com.hiberus.openbank.marvel.ui.mainActivity.adapters.CharacterItemAdapterInterface
import com.hiberus.openbank.marvel.databinding.FragmentCharacterDetailItemsBinding
import okqapps.com.tagslayout.TagClickListener
import okqapps.com.tagslayout.TagItem

class CharacterDetailItemsFragment : Fragment(), CharacterItemAdapterInterface {

    private val TAG = "CharacterDetailItemsFragment"
    private lateinit var binding: FragmentCharacterDetailItemsBinding
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_character_detail_items,
                container,
                false
            )
         mainViewModel.charDetailModel.characterItemAdapter?.characterItemAdapterInterface = this
        binding.mvm = mainViewModel

        //set recycler adapter
        binding.recyclerCharItems.adapter = mainViewModel.charDetailModel.characterItemAdapter
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerCharItems.layoutManager = layoutManager

        //get all types of items of the list as tags. Just once, no repeats
        val itemTypes = mainViewModel.charDetailModel.selectedCharacterWithItemsDto?.itemList
            ?.filter { !it.itemKind.isNullOrEmpty() }
            ?.map {
                it.itemKind!!
            }?.distinct()
        mainViewModel.charDetailModel.characterItemAdapter?.addAllFilters(itemTypes)
        val tags = itemTypes?.map {
            TagItem(it, it, "#000000", "#FFFFFF", true)
        }?.toList()

        //add tags to view
        binding.tagContainerLayout.initializeTags(requireContext(), tags)

        //tag selected/unselected listener
        val tagClickListener = object : TagClickListener {
            override fun onTagSelect(tagItem: TagItem?) {
                Log.d(TAG, "tag selected ->${tagItem!!.tagText}")
                mainViewModel.charDetailModel.characterItemAdapter!!.setFilter(
                    tagItem.tagText,
                    false
                )
            }

            override fun onTagUnselect(tagItem: TagItem?) {
                Log.d(TAG, "tag unselected ->${tagItem!!.tagText}")
                mainViewModel.charDetailModel.characterItemAdapter!!.setFilter(
                    tagItem.tagText,
                    true
                )
            }
        }

        binding.tagContainerLayout.setTagsCallback(tagClickListener)



        return binding.root
    }

    override fun onItemSetChanged(isEmpty: Boolean) {
        mainViewModel.charDetailModel.showItemList = !isEmpty
    }

}