package sourabh.pal.mysocial.common.data

import javax.inject.Inject
import sourabh.pal.mysocial.common.data.api.Character
import sourabh.pal.mysocial.common.data.api.MySocialApi
import sourabh.pal.mysocial.common.domain.repositories.MySocialRepository

class MySocialRepoImpl @Inject constructor(val api: MySocialApi): MySocialRepository  {
    override suspend fun getFeeds(): Character {
        return api.getFeeds(1)
    }
}