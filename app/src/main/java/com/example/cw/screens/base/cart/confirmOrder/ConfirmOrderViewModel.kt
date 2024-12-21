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
    userService: IUserService,
    private val onSuccess: ()->Unit,
    private val onToPurchases: ()->Unit,
    private val isOrderSuccess: Boolean
) : ViewModel() {
    val isSuccess = MutableStateFlow(isOrderSuccess)
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

    val isButtonActive = MutableStateFlow(false)

    private fun isButtonActive(): Boolean {
        return validateCVC(_cvc.value) && _selectedAddress.value != null &&
                validateCardNumber(_cardNumber.value) && validateExpirationDate(_expireDate.value)
    }


    init {
        if (addresses.isNotEmpty()) {
            _selectedAddress.value = addresses.first()
        }
    }

    fun onAddressSelected(address: Address) {
        _selectedAddress.value = address
        isButtonActive.value = isButtonActive()
    }

    fun onCardNumberChanged(input: String) {
        _cardNumber.value = input
        isButtonActive.value = isButtonActive()
    }

    fun onCVCChanged(input: String) {
        _cvc.value = input
        isButtonActive.value = isButtonActive()
    }

    fun onExpireDateChanged(input: String) {
        _expireDate.value = input
        isButtonActive.value = isButtonActive()
    }

    fun onShippingSelected(shippingType: ShippingType) {
        _selectedShipping.value = shippingType
        isButtonActive.value = isButtonActive()
    }

    private fun validateCardNumber(cardNumber: String): Boolean {
        val cleaned = cardNumber.replace(" ", "")
        return cleaned.length == 16 && cleaned.all { it.isDigit() }
    }

    private fun validateExpirationDate(expirationDate: String): Boolean {
        val regex = Regex("\\d{2}/\\d{2}")
        return regex.matches(expirationDate)
    }

    private fun validateCVC(cvc: String): Boolean {
        return cvc.length == 3 && cvc.all { it.isDigit() }
    }


    fun onConfirmButtonPressed() {
        if (isButtonActive.value) {
            isSuccess.value = true
            onSuccess()
        }
    }
}