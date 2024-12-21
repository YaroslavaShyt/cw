package com.example.cw.data.plants

import com.example.cw.domain.networking.INetworkingClient
import com.example.cw.domain.networking.plantsCategoriesEnd
import com.example.cw.domain.networking.plantsEnd
import com.example.cw.domain.plants.IPlantsRepository

private const val nameString: String = "name"
private const val dataString: String = "data"

class PlantsRepository(private val networkingClient: INetworkingClient) : IPlantsRepository {
    override suspend fun getAllPlants(): List<Plant> {
        val plantsData = networkingClient.get(plantsEnd)

        return plantsData.map { plantData ->
            Plant.fromMap(plantData)
        }
    }

    override suspend fun getPlantsById(ids: List<String>): List<Plant> {
        if (ids.isNotEmpty()) {
            val plantsData = networkingClient.get(plantsEnd, ids = ids)

            return plantsData.map { plantData ->
                Plant.fromMap(plantData)
            }
        }
        return emptyList()
    }

    override suspend fun getPlantsCategories(): List<String> {
        val categories = networkingClient.get(plantsCategoriesEnd)

        return categories.mapNotNull { category ->
            val dataMap = category[dataString] as? Map<String, Any> ?: return@mapNotNull null
            dataMap[nameString] as? String
        }
    }
}