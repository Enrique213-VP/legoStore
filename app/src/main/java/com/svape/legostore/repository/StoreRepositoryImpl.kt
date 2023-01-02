package com.svape.legostore.repository

import com.svape.legostore.data.model.store.ProductsList
import com.svape.legostore.data.remote.StoreDataSource

class StoreRepositoryImpl(private val dataSource: StoreDataSource): StoreRepository {

    override suspend fun getProduct(): ProductsList = dataSource.getProduct()
}