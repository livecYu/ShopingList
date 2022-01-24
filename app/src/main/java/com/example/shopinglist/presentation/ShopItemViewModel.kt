package com.example.shopinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shopinglist.data.ShopListRepositoryImpl
import com.example.shopinglist.domain.AddShopItemUseCase
import com.example.shopinglist.domain.EditShopItemUseCase
import com.example.shopinglist.domain.GetSHopItemUseCase
import com.example.shopinglist.domain.ShopItem


class ShopItemViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetSHopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    fun getShopItemId(shopItemId: Int){
        val item = getShopItemUseCase.getShopItemId(shopItemId)
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid){
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)

        }
    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid){
            val shopItem = ShopItem(name, count, true)
            editShopItemUseCase.editShopItem(shopItem)

        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0

        } catch (e: Exception){
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean{
        var result = true
        if (name.isBlank()){
            result = false
        }
        if (count <= 0){
            result = false
        }
        return result
    }

}