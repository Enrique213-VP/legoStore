package com.svape.legostore.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido a Lego Store, en Dashboard encontraras cosas increibles!"
    }
    val text: LiveData<String> = _text
}