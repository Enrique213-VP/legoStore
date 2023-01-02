package com.svape.legostore.ui.fragment.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Todavia no tienes productos en tu carrito, animate!"
    }
    val text: LiveData<String> = _text
}