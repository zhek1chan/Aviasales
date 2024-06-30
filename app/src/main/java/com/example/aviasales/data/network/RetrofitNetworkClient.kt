package com.example.aviasales.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.aviasales.data.dto.RecTicketsHolderDto
import com.example.aviasales.data.dto.RecommendationDto
import com.example.aviasales.data.dto.TicketsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class RetrofitNetworkClient(
    private val context: Context,
    private val api: Api
) : NetworkClient {

    override suspend fun getRecs(): Resource<RecommendationDto> {
        var recommedations: Resource<RecommendationDto>
        if (!isConnected()) return Resource.ConnectionError(DEVICE_IS_OFFLINE)
        withContext(Dispatchers.IO) {
            recommedations = try {
                api.getRecommendations().body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR)
            }
        }
        return recommedations
    }

    override suspend fun getTicketsRecs(): Resource<RecTicketsHolderDto> {
        var ticketsOffers: Resource<RecTicketsHolderDto>
        if (!isConnected()) return Resource.ConnectionError(DEVICE_IS_OFFLINE)
        withContext(Dispatchers.IO) {
            ticketsOffers = try {
                api.getTicketsOffers().body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR)
            }
        }
        return ticketsOffers
    }

    override suspend fun getTickets(): Resource<TicketsDto> {
        var tickets: Resource<TicketsDto>
        if (!isConnected()) return Resource.ConnectionError(DEVICE_IS_OFFLINE)
        withContext(Dispatchers.IO) {
            tickets = try {
                api.getTickets().body()?.let {
                    Resource.Data(it)
                } ?: Resource.NotFound(NOT_FOUND)
            } catch (ex: IOException) {
                Log.e(REQUEST_ERROR, ex.toString())
                Resource.ConnectionError(REQUEST_ERROR)
            }
        }
        return tickets
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        private const val REQUEST_ERROR = "error_400"
        private const val NOT_FOUND = "error_404"
        private const val DEVICE_IS_OFFLINE = "you_are_offline"
    }

}