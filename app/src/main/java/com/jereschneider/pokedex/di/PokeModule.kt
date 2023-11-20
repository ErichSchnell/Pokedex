package com.jereschneider.pokedex.di

import com.jereschneider.pokedex.data.datasources.PokemonDetailRemoteDataSource
import com.jereschneider.pokedex.data.datasources.PokemonListDataSource
import com.jereschneider.pokedex.data.interfaces.PokemonDetailRemoteDataSourceInterface
import com.jereschneider.pokedex.data.interfaces.PokemonListDataSourceInterface
import com.jereschneider.pokedex.data.network.BaseClient
import com.jereschneider.pokedex.data.repository.PokemonDetailRepository
import com.jereschneider.pokedex.data.repository.PokemonRawListRepository
import com.jereschneider.pokedex.domain.interfaces.PokemonDetailRepositoryInterface
import com.jereschneider.pokedex.domain.interfaces.PokemonRawListRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokeModule {
    //Data Layer
    @Provides
    @Singleton
    fun provideBaseClient(engine: HttpClientEngine): BaseClient = BaseClient(engine)

    @Provides
    @Singleton
    fun provideBaseClientEngine(): HttpClientEngine = OkHttp.create()
}

@Module
@InstallIn(SingletonComponent::class)
interface BindPokemonDetailRemoteDataSource {
    @Binds
    fun bind(impl: PokemonDetailRemoteDataSource): PokemonDetailRemoteDataSourceInterface
}

@Module
@InstallIn(SingletonComponent::class)
interface BindPokemonListDataSource {
    @Binds
    fun bind(impl: PokemonListDataSource): PokemonListDataSourceInterface
}

@Module
@InstallIn(SingletonComponent::class)
interface BindPokemonDetailRepository {
    @Binds
    fun bind(impl: PokemonDetailRepository): PokemonDetailRepositoryInterface
}

@Module
@InstallIn(SingletonComponent::class)
interface BindPokemonRawListRepository {
    @Binds
    fun bind(impl: PokemonRawListRepository): PokemonRawListRepositoryInterface
}
