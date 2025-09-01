package sourabh.pal.mysocial.common.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton
import kotlinx.serialization.json.Json
import sourabh.pal.mysocial.common.data.api.ApiConstants
import sourabh.pal.mysocial.common.data.api.interceptors.NetworkStatusInterceptor

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideKtorClient(networkStatusInterceptor: NetworkStatusInterceptor): HttpClient {
        return HttpClient(OkHttp){
            defaultRequest { url(ApiConstants.BASE_URL) }
            install(networkStatusInterceptor.plugin)
            install(Logging) {
                logger = Logger.SIMPLE
            }
            install(ContentNegotiation){
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}