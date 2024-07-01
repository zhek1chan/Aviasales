package com.example.aviasales.domain.repository

import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface RecsRepository {
    suspend fun getRecommendations(): Flow<Resource<List<Recommendation>>>
}