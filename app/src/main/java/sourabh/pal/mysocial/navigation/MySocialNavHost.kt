package sourabh.pal.mysocial.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import sourabh.pal.mysocial.ui.MySocialAppState

@Composable
fun MySocialNavHost(
    appState: MySocialAppState,
    onShowSnackBar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
){
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = FeedsBaseRoute,
        modifier = modifier
    ) {

    }
}