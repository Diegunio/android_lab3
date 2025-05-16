plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "pl.dlavayen.lab3"
    compileSdk = 35

    defaultConfig {
        applicationId = "pl.dlavayen.lab3"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Room components
    implementation("androidx.room:room-runtime:${rootProject.extra["roomVersion"]}")
    annotationProcessor("androidx.room:room-compiler:${rootProject.extra["roomVersion"]}")
    androidTestImplementation("androidx.room:room-testing:${rootProject.extra["roomVersion"]}")
    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-extensions:${rootProject.extra["archLifecycleVersion"]}")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:${rootProject.extra["archLifecycleVersion"]}")

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
