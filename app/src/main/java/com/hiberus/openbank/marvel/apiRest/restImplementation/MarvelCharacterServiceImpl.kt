package com.hiberus.openbank.marvel.apiRest.restImplementation

import android.util.Log
import com.hiberus.openbank.marvel.BuildConfig
import com.hiberus.openbank.marvel.MarvelApp
import com.hiberus.openbank.marvel.ui.mainActivity.MainInterface
import com.hiberus.openbank.marvel.apiRest.config.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MarvelCharacterServiceImpl : BaseServiceImpl() {
    private val TAG = "MarvelCharacterServiceImpl"

    fun getCharacters(mainInterface: MainInterface? = null, fromRefresh: Boolean = false) {
        mainInterface?.loading(true, fromRefresh)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val ts = System.currentTimeMillis().toString()
                val call =
                    RetrofitClient.getInstance().marvelCharacterService?.requestCharacterList(
                        BuildConfig.API_PUBLIC_KEY, ts, getHash(ts)
                    )
                Log.d(TAG, "GET->" + call?.toString())

                if (call != null && call!=null && call.code == 200) {
                    MarvelApp.get().DB!!.characterWithItemDao().insertAll(call.characters)

                    mainInterface?.loading(false, fromRefresh)

                } else {
                    mainInterface?.loading(false, fromRefresh)
                    mainInterface?.getCharactersError()
                    Log.d(TAG, "GET-> getCharacters failure")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mainInterface?.loading(false, fromRefresh)
                mainInterface?.getCharactersError()
                Log.d(TAG, "GET-> getCharacters failure")
            }
        }
    }
}