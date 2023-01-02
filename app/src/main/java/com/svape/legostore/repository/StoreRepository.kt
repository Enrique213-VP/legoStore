package com.svape.legostore.repository

import com.svape.legostore.data.model.store.ProductsList

interface StoreRepository {

    suspend fun getProduct(): ProductsList
}