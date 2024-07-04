package com.example.java.repository

import com.example.java.Resource
import com.example.java.model.Recommendation
import kotlinx.coroutines.flow.Flow

interface RecsRepository {
    suspend fun getRecommendations(): Flow<Resource<List<Recommendation>>>
}