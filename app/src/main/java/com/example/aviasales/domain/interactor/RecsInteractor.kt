package com.example.aviasales.domain.interactor

import com.example.aviasales.data.network.Resource
import com.example.aviasales.domain.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface RecsInteractor {
    suspend fun getRecommendations(): Flow<Resource<List<Recommendation>>>
}