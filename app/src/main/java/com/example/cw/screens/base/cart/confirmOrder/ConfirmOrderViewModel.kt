package com.example.cw.screens.base.cart.confirmOrder

import androidx.lifecycle.ViewModel
import com.example.cw.data.plants.Plant
import com.example.cw.data.user.Address
import com.example.cw.data.user.ShippingType
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConfirmOrderViewModel(
    plants: List<Plant>,
    totalSum: Double,
    userService: IUserService
) : ViewModel() {
    val shippingTypes = listOf(ShippingType.Standard, ShippingType.Fast)

    private val _selectedShipping = MutableStateFlow(ShippingType.Fast)
    val selectedShipping: StateFlow<ShippingType> = _selectedShipping

    private val _selectedAddress = MutableStateFlow<Address?>(null)
    val selectedAddress: StateFlow<Address?> = _selectedAddress

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber
    private val _expireDate = MutableStateFlow("")
    val expireDate: StateFlow<String> = _expireDate
    private val _cvc = MutableStateFlow("")
    val cvc: StateFlow<String> = _cvc

    val order: List<Plant> = plants
    val totalToPay: Double = totalSum
    val addresses: List<Address> = userService.user.value?.addresses ?: emptyList()

    val isButtonActive: Boolean =
        _cvc.value.isNotEmpty() && _selectedAddress.value != null &&
                _cardNumber.value.isNotEmpty() && _expireDate.value.isNotEmpty()

    init {
        if (addresses.isNotEmpty()) {
            _selectedAddress.value = addresses.first()
        }
    }

    fun onAddressSelected(address: Address) {
        _selectedAddress.value = address
    }

    fun onCardNumberChanged(input: String) {
        _cardNumber.value = input
    }

    fun onCVCChanged(input: String) {
        _cvc.value = input
    }

    fun onExpireDateChanged(input: String) {
        _expireDate.value = input
    }

    fun onShippingSelected(shippingType: ShippingType) {
        _selectedShipping.value = shippingType
    }


    fun onConfirmButtonPressed() {
        if (isButtonActive) {
        }
    }

}