package com.hiberus.openbank.marvel.ui.mainActivity

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation
import com.hiberus.openbank.marvel.MarvelApp
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.common.MenuModel
import com.hiberus.openbank.marvel.ui.mainActivity.fragments.databindingModels.CharacterDetailModel
import com.hiberus.openbank.marvel.apiRest.restImplementation.MarvelCharacterServiceImpl
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto


class MainViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "MainViewModel"
    var mainModel: MainModel = MainModel()
    var menuModel: MenuModel = MenuModel()
    var mainInterface: MainInterface? = null
    var charDetailModel: CharacterDetailModel = CharacterDetailModel()

    var clickGoToDetails = View.OnClickListener { v: View ->
        val character = v.tag as CharacterWithItemsDto
        Log.d(TAG, "clicked on ->$character")
        charDetailModel.selectedCharacterWithItemsDto = character
        Navigation.findNavController(v).navigate(R.id.from_list_to_detail)
    }


    var clickWebLink = View.OnClickListener { v: View ->
        val link = v.tag as String?
        Log.d(TAG, "clicked on ->$link")
        if (link != null) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            v.context.startActivity(browserIntent)
        } else {
            val toast = Toast.makeText(
                v.context,
                MarvelApp.get().getString(R.string.link_not_available),
                Toast.LENGTH_LONG
            )
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    var clickRefresh = View.OnClickListener { v: View ->

        MarvelCharacterServiceImpl().getCharacters(mainInterface, false)
    }

    //Load aadpter clicks
    init {
        mainModel.characterAdapter.clickListener = clickGoToDetails
    }

    fun updateMarvelList(fromRefresh: Boolean) {
        MarvelCharacterServiceImpl().getCharacters(mainInterface, fromRefresh)
    }
}