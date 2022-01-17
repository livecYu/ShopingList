package com.example.shopinglist.domain

class GetSHopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopItemId(shopItemId: Int): ShopItem{
        return shopListRepository.getShopItem(shopItemId)
    }
}