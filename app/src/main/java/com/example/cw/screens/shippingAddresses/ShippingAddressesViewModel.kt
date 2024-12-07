package com.example.cw.screens.shippingAddresses

import android.provider.Telephony.Mms.Addr
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.user.Address
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ShippingAddressesViewModel(userService: IUserService) : ViewModel(), KoinComponent {
    private val _userService: IUserService = userService

    private val _selectedAddress: MutableStateFlow<Address> = MutableStateFlow(Address())
    val selectedAddress: StateFlow<Address> = _selectedAddress

    private var _addresses = MutableStateFlow<List<Address>>(mutableListOf())
    var addresses: StateFlow<List<Address>> = _addresses

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchAddresses()
    }

    fun onAddressSelected(selectedAddress: Address) {
        _selectedAddress.value = _addresses.value.first { address ->
            selectedAddress == address
        }
    }

    fun onAddAddressPressed(country: String, city: String, street: String) {
        viewModelScope.launch {
            _addresses.value += Address(country = country, city = city, street = street)
            _userService.updateUserAddresses(_addresses.value)
        }

    }

    fun onDeleteAddressPressed(address: Address) {
        viewModelScope.launch {
            _addresses.value -= address
            _userService.updateUserAddresses(_addresses.value)
        }

    }

    // TODO: does not work
    fun onEditAddressPressed(country: String, city: String, street: String) {
        viewModelScope.launch {
            _addresses.value -= _selectedAddress.value
            _addresses.value += Address(country = country, city = city, street = street)
            _userService.updateUserAddresses(_addresses.value)
        }
    }

    private fun fetchAddresses() {
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                _addresses.value = _userService.user.addresses

            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

}