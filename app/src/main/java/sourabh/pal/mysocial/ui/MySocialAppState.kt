package sourabh.pal.mysocial.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import sourabh.pal.mysocial.navigation.TopLevelDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.TimeZone
import sourabh.pal.mysocial.common.data.util.NetworkMonitor
import sourabh.pal.mysocial.common.data.util.TimeZoneMonitor

@Composable
fun rememberMySocialAppState(
    windowSizeClass: WindowSizeClass,
    networkMonitor: NetworkMonitor,
    timeZoneMonitor: TimeZoneMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): MySocialAppState {
    return remember(
        windowSizeClass,
        navController,
        coroutineScope,
        networkMonitor,
        timeZoneMonitor
    ) {
        MySocialAppState(
            windowSizeClass = windowSizeClass,
            navController = navController,
            coroutineScope = coroutineScope,
            networkMonitor = networkMonitor,
            timeZoneMonitor = timeZoneMonitor
        )
    }
}

@Stable
class MySocialAppState(
    val windowSizeClass: WindowSizeClass,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    timeZoneMonitor: TimeZoneMonitor,
    networkMonitor: NetworkMonitor,
) {
    val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(null)

            return currentEntry.value?.destination.also { destination ->
                destination?.let {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.hasRoute(topLevelDestination.route) == true
            }
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val isOffline = networkMonitor.isOnline
        .map ( Boolean::not )
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    val currentTimeZone = timeZoneMonitor.currentTimeZone
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TimeZone.currentSystemDefault()
        )

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        //Todo: add implementation when navigation is ready
//        when(topLevelDestination){
//            TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
//            TopLevelDestination.SEARCH -> navController.navigateToSearch(topLevelNavOptions)
//            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
//        }
    }

}