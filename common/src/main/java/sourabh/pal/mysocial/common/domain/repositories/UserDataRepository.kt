package sourabh.pal.mysocial.common.domain.repositories

import kotlinx.coroutines.flow.Flow
import sourabh.pal.mysocial.core.model.data.DarkThemeConfig
import sourabh.pal.mysocial.core.model.data.ThemeBrand
import sourabh.pal.mysocial.core.model.data.UserData

interface UserDataRepository {

    val userData: Flow<UserData>

    /**
     * Sets the desired theme brand.
     */
    suspend fun setThemeBrand(themeBrand: ThemeBrand)

    /**
     * Sets the desired dark theme config.
     */
    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)

    /**
     * Sets the preferred dynamic color config.
     */
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean)

    /**
     * Sets whether the user has completed the onboarding process.
     */
    suspend fun setShouldHideOnboarding(shouldHideOnboarding: Boolean)
}