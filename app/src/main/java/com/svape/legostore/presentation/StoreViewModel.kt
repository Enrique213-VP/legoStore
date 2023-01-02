package com.svape.legostore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.svape.legostore.repository.StoreRepository
import com.svape.legostore.core.Resource
import kotlinx.coroutines.Dispatchers

class StoreViewModel(private val repo: StoreRepository): ViewModel() {


    fun fetchMainScreenStore() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    repo.getProduct()
                )
            )
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }
}

class StoreViewModelFactory(private val repo: StoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(StoreRepository::class.java).newInstance(repo)
    }
}