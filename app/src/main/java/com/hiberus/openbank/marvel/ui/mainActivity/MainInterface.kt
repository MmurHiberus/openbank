package com.hiberus.openbank.marvel.ui.mainActivity

interface MainInterface {
    fun loading(isLoading:Boolean, fromRefresh:Boolean =false)
    fun getCharactersError()
}