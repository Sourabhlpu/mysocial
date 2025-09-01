package sourabh.pal.mysocial

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import sourabh.pal.mysocial.common.domain.repositories.MySocialRepository

@HiltViewModel
class MainViewModel @Inject constructor(val repo: MySocialRepository): ViewModel() {
    suspend fun getFeeds() = repo.getFeeds()
}
