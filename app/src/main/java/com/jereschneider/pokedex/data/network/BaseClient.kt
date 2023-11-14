package com.jereschneider.pokedex.data.network

import android.util.Log
import com.jereschneider.pokedex.data.models.PokePageDto
import com.jereschneider.pokedex.data.models.PokemonDetailDto
import com.jereschneider.pokedex.data.network.BaseClient.Companion.BASE_URL
import com.jereschneider.pokedex.domain.models.StatusResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class BaseClient constructor(engine: HttpClientEngine) {
    companion object {
        internal const val BASE_URL = "https://pokeapi.co/api/v2"
    }

    internal val apiClient: HttpClient = HttpClient(engine) {
        Json { ignoreUnknownKeys = true }
        install(ContentNegotiation) {
            json()
        }
    }
}

internal suspend fun BaseClient.fetchPokemonPage(navigateToPage: String? = null)
        : StatusResult<PokePageDto> {
    val page = if (navigateToPage.isNullOrEmpty()) "$BASE_URL/pokemon" else navigateToPage

    val response = apiClient.get(page)

    return try {
        if (response.status.value in 200..299)
            StatusResult.Success(value = response.body())
        else
            StatusResult.Error("No se pudo obtener el listado de pokemones debido a ${response.status.description}")
    } catch (e: Exception) {
        Log.e("fetchPokemonPage: ", "ERROR DESCONOCIDO: $e")
        StatusResult.Error("No se pudo obtener el listado de pokemones debido a un error desconocido")
    }
}

internal suspend fun BaseClient.fetchPokemonDetail(endpoint: String) : StatusResult<PokemonDetailDto> {
    val response = apiClient.get(endpoint)

    return try {
        if (response.status.value in 200..299)
            StatusResult.Success(value = response.body())
        else
            StatusResult.Error("No se pudo cargar el pokemon porque ${response.status.description}")
    } catch (e: Exception) {
        Log.e("fetchPokemonDetail: ", "ERROR DESCONOCIDO: $e")
        StatusResult.Error("No se pudo obtener el pokemon debido a un error desconocido")
    }
}