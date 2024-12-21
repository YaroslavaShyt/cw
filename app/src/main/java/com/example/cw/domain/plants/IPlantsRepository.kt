package com.example.cw.domain.plants

import com.example.cw.data.plants.Plant

interface IPlantsRepository{
    suspend fun getAllPlants(): List<Plant>

    suspend fun getPlantsById(ids: List<String>): List<Plant>

    suspend fun getPlantsCategories(): List<String>

}