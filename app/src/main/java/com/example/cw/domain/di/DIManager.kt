package com.example.cw.domain.di

import com.example.cw.data.networking.NetworkingClient
import com.example.cw.data.plants.PlantsRepository
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.plants.IPlantsRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val appModule = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }
    single<INetworkingClient> { NetworkingClient(get()) }
    single<IPlantsRepository> { PlantsRepository(get()) }
}
