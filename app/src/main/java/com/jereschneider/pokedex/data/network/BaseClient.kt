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
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BaseClient @Inject constructor(engine: HttpClientEngine) {
    companion object {
        internal const val BASE_URL = "https://pokeapi.co/api/v2"
    }

    private val apiClient: HttpClient = HttpClient(engine) {
        Json { ignoreUnknownKeys = true }
        install(ContentNegotiation) {
            json()
        }
    }

    internal suspend fun get(url: String, errorMessage: String): HttpStatus {
        return try {
            val response = apiClient.get(url)
            if (response.status.value in 200..299)
                HttpStatus(httpResponse = response)
            else
                HttpStatus(errorMessage = errorMessage)
        } catch (e: ConnectException) {
            Log.e("fetchPokemonPage: ", "ERROR DE CONEXION: $e")
            HttpStatus(errorMessage = "Por favor, verifica tu conexión")
        } catch (e: UnknownHostException) {
            Log.e("fetchPokemonPage: ", "ERROR DE CONEXION: $e")
            HttpStatus(errorMessage = "Por favor, verifica tu conexión")
        } catch (e: Exception) {
            Log.e("fetchPokemonPage: ", "ERROR DESCONOCIDO: $e")
            HttpStatus(errorMessage = "Ups! Atrapaste un error desconocido, vuelve a intentarlo")
        }
    }
}

internal data class HttpStatus(
    val httpResponse: HttpResponse? = null,
    val errorMessage: String = ""
)

internal suspend fun BaseClient.fetchPokemonPage(navigateToPage: String? = null): StatusResult<PokePageDto> {
    val page = if (navigateToPage.isNullOrEmpty()) "$BASE_URL/pokemon" else navigateToPage
    val errorMessage = "No se pudo obtener el listado de pokemones"
    val httpStatus = get(page, errorMessage)

    httpStatus.httpResponse?.let { return StatusResult.Success(value = it.body()) }

    return StatusResult.Error(httpStatus.errorMessage)
}

internal suspend fun BaseClient.fetchPokemonDetail(endpoint: String): StatusResult<PokemonDetailDto> {
    val errorMessage = "No se pudo cargar el pokemon"
    val httpStatus = get(endpoint, errorMessage)

    httpStatus.httpResponse?.let { return StatusResult.Success(value = it.body()) }

    return StatusResult.Error(httpStatus.errorMessage)
}