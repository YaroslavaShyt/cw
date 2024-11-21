package com.example.cw.domain.plants

import com.example.cw.Plant

interface IPlantsRepository {
    suspend fun getAllPlants(): List<Plant>
}