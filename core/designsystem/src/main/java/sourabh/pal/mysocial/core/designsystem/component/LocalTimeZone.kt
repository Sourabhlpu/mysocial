package sourabh.pal.mysocial.core.designsystem.component

import androidx.compose.runtime.compositionLocalOf
import kotlinx.datetime.TimeZone

//Todo: move this file into it's own module i.e. UI
val LocalTimeZone = compositionLocalOf { TimeZone.currentSystemDefault() }