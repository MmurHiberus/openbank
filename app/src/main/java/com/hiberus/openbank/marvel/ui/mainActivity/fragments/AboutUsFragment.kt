package com.hiberus.openbank.marvel.ui.mainActivity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.mainActivity.MainViewModel
import com.hiberus.openbank.marvel.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {

    private val TAG = "AboutUsFragment"
    private lateinit var binding: FragmentAboutUsBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false)
        mainViewModel.mainModel.loading = false
        mainViewModel.menuModel.hasClickBack = false
        binding.mvm = mainViewModel


        return binding.root
    }


}