package com.svape.legostore.data.remote

import com.svape.legostore.data.model.store.ProductsList
import com.svape.legostore.repository.WebService
import com.svape.legostore.untils.RequestConstants

class StoreDataSource(private val webService: WebService) {

    suspend fun getProduct(): ProductsList = webService.getProduct()
}