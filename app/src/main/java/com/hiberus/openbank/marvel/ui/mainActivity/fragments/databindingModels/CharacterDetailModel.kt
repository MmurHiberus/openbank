package com.hiberus.openbank.marvel.ui.mainActivity.fragments.databindingModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR;
import com.hiberus.openbank.marvel.MarvelApp
import com.hiberus.openbank.marvel.R
import com.hiberus.openbank.marvel.ui.mainActivity.adapters.CharacterItemAdapter
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto
import java.text.DateFormat.getDateInstance
import java.util.*

class CharacterDetailModel : BaseObservable() {



    //region GENERAL
    @get:Bindable
    public var selectedCharacterWithItemsDto: CharacterWithItemsDto? = null
        set(value) {
            field = value
            dateModified = selectedCharacterWithItemsDto?.marvelCharacter?.modified?.toSimpleString()
                ?: MarvelApp.get().getString(R.string.no_date_modified)

            description = selectedCharacterWithItemsDto?.marvelCharacter?.description
            showItemList =  !selectedCharacterWithItemsDto!!.itemList.isNullOrEmpty()
            characterItemAdapter!!.setData(selectedCharacterWithItemsDto!!.itemList)
            notifyPropertyChanged(BR.selectedCharacterWithItemsDto)
        }

    @get:Bindable
    public var dateModified: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateModified)
        }

  @get:Bindable
    public var description: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }


    @get:Bindable
    public var characterItemAdapter: CharacterItemAdapter? = CharacterItemAdapter(null)
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }
    @get:Bindable
    public var showItemList: Boolean = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showItemList)
        }
    fun Date.toSimpleString(): String {
        return getDateInstance().format(this)
    }
    //endregion

}