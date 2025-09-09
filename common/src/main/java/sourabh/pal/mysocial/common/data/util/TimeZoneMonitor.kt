package sourabh.pal.mysocial.common.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.TimeZone

interface TimeZoneMonitor {
    val currentTimeZone: Flow<TimeZone>
}

//Todo: add implementation
