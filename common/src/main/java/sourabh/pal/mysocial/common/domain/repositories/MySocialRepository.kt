package sourabh.pal.mysocial.common.domain.repositories
import sourabh.pal.mysocial.common.data.api.Character

interface MySocialRepository {
    suspend fun getFeeds(): Character
}