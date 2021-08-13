package com.improving.testing_20.repositories

import androidx.lifecycle.LiveData
import com.improving.testing_20.others.Resource
import com.improving.testing_20.data.local.ShoppingItem
import com.improving.testing_20.data.responses.ImageResponse

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItem() : LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}