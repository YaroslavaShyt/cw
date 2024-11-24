package com.example.cw.domain.plants

import com.example.cw.data.plants.Plant
import org.koin.core.component.KoinComponent

interface IPlantsRepository: KoinComponent {
    suspend fun getAllPlants(): List<Plant>

    suspend fun getPlantsCategories(): List<String>

}