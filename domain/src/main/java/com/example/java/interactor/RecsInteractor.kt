package com.example.java.interactor

import com.example.java.Resource
import com.example.java.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface RecsInteractor {
    suspend fun getRecommendations(): Flow<Resource<List<Recommendation>>>
}