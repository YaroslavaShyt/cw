package com.example.cw.data.services

import com.example.cw.data.user.Address
import com.example.cw.data.user.Address.Companion.toMap
import com.example.cw.data.user.User
import com.example.cw.data.user.User.Companion.toMap
import com.example.cw.domain.services.IUserService
import com.example.cw.domain.user.IUserRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

class UserService(private val userRepository: IUserRepository) : KoinComponent, IUserService {
    private var _user = MutableStateFlow<User?>(null)
    override var user: StateFlow<User?> = _user

    override suspend fun initUser(id: String, firebaseUser: FirebaseUser?) {
        _user.value = userRepository.getUser(id)
        if (_user.value == null && firebaseUser != null) {
            val newUser =
                User(
                    id = firebaseUser.uid,
                    name = firebaseUser.displayName.toString(),
                    photo = firebaseUser.photoUrl.toString()
                )
            _user.value = newUser
            userRepository.addUser(newUser)
        }
    }


    override suspend fun getUserData() {
        if (_user.value != null) {
            _user.value = userRepository.getUser(_user.value!!.id)
        }

    }

    override suspend fun updateUserAddresses(addresses: List<Address>) {
        if (_user.value != null) {
            _user.value!!.addresses = addresses

            userRepository.updateUserAddress(
                _user.value!!.id,
                _user.value!!.addresses.map { address -> address.toMap() }
            )
        }

    }

    override suspend fun updateUserFavorites(favorites: List<String>) {
        if (_user.value != null) {
            _user.value!!.favorite = favorites

            userRepository.updateUserFavorites(
                _user.value!!.id,
                _user.value!!.favorite,
            )
        }

    }

    override suspend fun updateUserCart(cart: Map<String, Int>) {
        if (_user.value != null) {
            if (_user.value!!.cart.contains(cart.keys.first())) {
                val mutMap = _user.value!!.cart.toMutableMap()
                mutMap[cart.keys.first()] = cart.values.first()
                _user.value!!.cart = mutMap
            } else {
                _user.value!!.cart += cart
            }

            userRepository.updateUserCart(
                _user.value!!.id,
                _user.value!!.toMap(),
            )
        }
    }

    override suspend fun removeFromCart(id: String) {
        if (_user.value != null) {
            if (_user.value!!.cart.contains(id)) {
                val mutMap = _user.value!!.cart.toMutableMap()
                mutMap.remove(id)
                _user.value!!.cart = mutMap
                userRepository.updateUserCart(
                    _user.value!!.id,
                    _user.value!!.toMap(),
                )
            }
        }
    }


    override fun cleanData() {
        _user.value = null
    }

}