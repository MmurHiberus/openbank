package com.hiberus.openbank.marvel.ui.common

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR;

class MenuModel : BaseObservable() {
        @get:Bindable
        var clickBack: View.OnClickListener? = null
            set(clickBack) {
                field = clickBack
                notifyPropertyChanged(BR.clickBack)
            }



        @get:Bindable
        var hasClickBack = false
            set(enabled) {
                field = enabled
                notifyPropertyChanged(BR.hasClickBack)
            }



    }