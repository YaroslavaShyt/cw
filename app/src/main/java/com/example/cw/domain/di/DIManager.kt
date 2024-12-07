package com.example.cw.domain.di

import com.example.cw.data.networking.NetworkingClient
import com.example.cw.data.plants.PlantsRepository
import com.example.cw.data.services.UserService
import com.example.cw.data.user.UserRepository
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.plants.IPlantsRepository
import com.example.cw.domain.services.IUserService
import com.example.cw.domain.user.IUserRepository
import com.example.cw.screens.home.main.HomeViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
    single<INetworkingClient> { NetworkingClient(get<FirebaseFirestore>()) }

    factory<IPlantsRepository> { PlantsRepository(get<INetworkingClient>()) }

    factory<IUserRepository> { UserRepository(get<INetworkingClient>()) }
    single<IUserService> { UserService(get<IUserRepository>()) }
}
