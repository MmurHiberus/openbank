package com.hiberus.openbank.marvel.ui.mainActivity
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LiveData
import com.hiberus.openbank.marvel.MarvelApp
import com.hiberus.openbank.marvel.ui.mainActivity.adapters.CharacterAdapter
import com.hiberus.openbank.marvel.db.dto.CharacterWithItemsDto

class MainModel : BaseObservable() {

    //region GENERAL
    @get:Bindable
    public var loading: Boolean? = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }
    var marvelCharLD: LiveData<List<CharacterWithItemsDto>>? = MarvelApp.get().DB?.characterWithItemDao()?.findAllCharactersWithItems()

    @get:Bindable
    public var marvelCharWithItemsList : List<CharacterWithItemsDto>? = null
        set(value) {
            field = value
            showCharList = !marvelCharWithItemsList.isNullOrEmpty()
            characterAdapter.setData(marvelCharWithItemsList)
            notifyPropertyChanged(BR.marvelCharWithItemsList)
        }

    //endregion

    //region CHARLIST FRAGMENT DATA
    @get:Bindable
    public var showCharList: Boolean? = true
        set(value) {
            field = value
            notifyPropertyChanged(BR.showCharList)
        }

    @get:Bindable
    public var charListFilter: String? = null
        set(value) {
            field = value
            characterAdapter.setFilter(charListFilter)
            notifyPropertyChanged(BR.charListFilter)
        }

    @get:Bindable
    public var characterAdapter: CharacterAdapter = CharacterAdapter(null, null)
        set(value) {
            field = value
            characterAdapter.setFilter(charListFilter)
            notifyPropertyChanged(BR.characterAdapter)
        }



    //endregion

}