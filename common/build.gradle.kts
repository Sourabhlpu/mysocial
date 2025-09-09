plugins {
    alias(libs.plugins.mysocial.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.mysocial.android.room)
    alias(libs.plugins.mysocial.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "sourabh.pal.mysocial.common"


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    api(libs.kotlinx.datetime)
}