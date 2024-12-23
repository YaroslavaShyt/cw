package com.example.cw.screens.base.drawer.shippingAddresses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.user.Address
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShippingAddressesViewModel(userService: IUserService) : ViewModel() {
    private val _userService: IUserService = userService

    private val _selectedAddress: MutableStateFlow<Address> = MutableStateFlow(Address())
    val selectedAddress: StateFlow<Address> = _selectedAddress

    private var _addresses = MutableStateFlow<List<Address>>(mutableListOf())
    var addresses: StateFlow<List<Address>> = _addresses

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage

    init {
        fetchAddresses()
    }

    fun onAddressSelected(selectedAddress: Address) {
        try {
            _selectedAddress.value = _addresses.value.first { address ->
                selectedAddress == address
            }
        } catch (e: NoSuchElementException) {
            _error.value = "Адреса не знайдена"
        }
    }

    fun onAddAddressPressed(country: String, city: String, street: String) {
        viewModelScope.launch {
            try {
                val newAddress = Address(country = country, city = city, street = street)

                if (_addresses.value.any { it == newAddress }) {
                    _snackbarMessage.value = "Ця адреса вже існує"
                    return@launch
                }

                _addresses.value += newAddress
                _userService.updateUserAddresses(_addresses.value)
            } catch (e: Exception) {
                _error.value = "Не вдалося додати адресу: ${e.localizedMessage}"
            }
        }
    }

    fun onDeleteAddressPressed(address: Address) {
        viewModelScope.launch {
            try {
                _addresses.value -= address
                _userService.updateUserAddresses(_addresses.value)
            } catch (e: Exception) {
                _error.value = "Не вдалося видалити адресу: ${e.localizedMessage}"
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null
    }

    private fun fetchAddresses() {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                val user = _userService.user.value
                if (user != null) {
                    _addresses.value = user.addresses
                } else {
                    _error.value = "Користувач не знайдений"
                }
            } catch (e: Exception) {
                _error.value = "Не вдалося завантажити адреси: ${e.localizedMessage}"
            } finally {
                _loading.value = false
            }
        }
    }
}
