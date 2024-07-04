package com.example.java.interactor

import com.example.java.Resource
import com.example.java.model.Recommendation
import com.example.java.repository.RecsRepository
import kotlinx.coroutines.flow.Flow

class RecsInteractorImpl(
    private val repository: RecsRepository
) : RecsInteractor {
    override suspend fun getRecommendations(): Flow<Resource<List<Recommendation>>> =
        repository.getRecommendations()
}