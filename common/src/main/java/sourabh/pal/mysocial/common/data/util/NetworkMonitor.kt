package sourabh.pal.mysocial.common.data.util

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}

//Todo: add implementation