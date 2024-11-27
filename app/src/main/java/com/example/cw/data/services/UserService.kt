package com.example.cw.data.services

import android.util.Log
import com.example.cw.data.user.User
import com.example.cw.domain.services.IUserService
import com.example.cw.domain.user.IUserRepository
import org.koin.core.component.KoinComponent

class UserService (private val userRepository: IUserRepository) : KoinComponent, IUserService{
    private var _user: User = User(id = "1")

    override var user : User = _user

    override suspend fun getUserData() {
        _user = userRepository.getUser(user.id)
        user = _user
        Log.d("data", "data ${_user.addresses}")
    }
}