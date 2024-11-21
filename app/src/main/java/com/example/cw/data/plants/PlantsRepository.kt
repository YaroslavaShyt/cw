package com.example.cw.data.plants

import com.example.cw.Plant
import com.example.cw.data.networking.NetworkingClient
import com.example.cw.domain.networking.plantsEnd
import com.example.cw.domain.plants.IPlantsRepository

class PlantsRepository(private val networkingClient: NetworkingClient) : IPlantsRepository {
    override suspend fun getAllPlants(): List<Plant> {
        val plantsData = networkingClient.get(plantsEnd)

        return plantsData.map { plantData ->
            Plant.fromMap(plantData)
        }
    }
}