package com.example.cw.data.plants

import com.example.cw.Plant
import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.networking.plantsEnd
import com.example.cw.domain.plants.IPlantsRepository
import org.koin.core.component.KoinComponent

class PlantsRepository(private val networkingClient: INetworkingClient) : IPlantsRepository, KoinComponent {
    override suspend fun getAllPlants(): List<Plant> {
        val plantsData = networkingClient.get(plantsEnd)

        return plantsData.map { plantData ->
            Plant.fromMap(plantData)
        }
    }
}