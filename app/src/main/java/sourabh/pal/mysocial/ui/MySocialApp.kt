@file:OptIn(ExperimentalComposeUiApi::class)

package sourabh.pal.mysocial.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import sourabh.pal.mysocial.R
import sourabh.pal.mysocial.core.designsystem.component.MySocialBackground
import sourabh.pal.mysocial.core.designsystem.component.MySocialGradientBackground
import sourabh.pal.mysocial.core.designsystem.theme.GradientColors
import sourabh.pal.mysocial.core.designsystem.theme.LocalGradientColors
import sourabh.pal.mysocial.navigation.TopLevelDestination

@Composable
fun MySocialApp(
    appState: MySocialAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    val shouldShowGradient = appState.currentDestination == TopLevelDestination.FEEDS.route
    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

    MySocialBackground(modifier = modifier) {
        MySocialGradientBackground(
            gradientColors = if (shouldShowGradient) {
                LocalGradientColors.current
            } else {
                GradientColors()
            }
        ) {
            val snackbarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            val notConnectedMessage = stringResource(R.string.not_connected)

            LaunchedEffect(isOffline) {
                if(isOffline){
                    snackbarHostState.showSnackbar(
                        message = notConnectedMessage,
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }
            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = {
                    if(appState.shouldShowBottomBar){

                    }
                }
            ) {  paddingValues ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .consumeWindowInsets(paddingValues)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ){

                }
            }

        }
    }

}