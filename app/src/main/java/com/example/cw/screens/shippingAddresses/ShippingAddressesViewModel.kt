package com.example.cw.screens.shippingAddresses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cw.data.user.Address
import com.example.cw.domain.services.IUserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ShippingAddressesViewModel : ViewModel(), KoinComponent{
    private val userService: IUserService by inject()

    private val _addresses = MutableStateFlow<List<Address>>(emptyList())
    val addresses: StateFlow<List<Address>> = _addresses

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchAddresses()
    }

    fun onAddAddressPressed(country: String, city: String, street: String){

    }

    private fun fetchAddresses(){
        _loading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                userService.getUserData()
                _addresses.value = userService.user.addresses

            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

}