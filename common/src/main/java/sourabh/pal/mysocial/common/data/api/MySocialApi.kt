package sourabh.pal.mysocial.common.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import kotlinx.serialization.Serializable

class MySocialApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getFeeds(id: Int): Character = client.get("character/$id").body()
}

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val origin: Origin
){
    @Serializable
    data class Origin(
        val name: String
    )
}