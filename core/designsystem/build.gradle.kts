
plugins {
    alias(libs.plugins.mysocial.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.mysocial.android.compose)
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
    namespace = "sourabh.pal.mysocial.core.designsystem"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
    implementation(libs.kotlinx.datetime)

    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.compose.ui.test)

}
