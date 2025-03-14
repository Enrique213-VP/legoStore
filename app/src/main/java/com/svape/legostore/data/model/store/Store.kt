package com.svape.legostore.data.model.store

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
 *
 *          "id": 1,
 *          "name": "Base Gris",
 *          "unit_price": 400,
 *          "stock": 5,
 *          "image": "https://www.lego.com/cdn/cs/set/assets/blt3baed37200b0845a/11024.png"
 */

data class Store (
    val id: Int = -1,
    val name: String = "",
    @SerializedName("unit_price")
    val unitPrice: Long = -1,
    val stock: Long = -1,
    val image: String = ""
        ) : Serializable

data class ProductsList(val products: List<Store> = listOf())