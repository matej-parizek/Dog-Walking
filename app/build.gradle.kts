plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "cz.ctu.fit.bi.and.semestral"
    compileSdk = 34

    defaultConfig {
        applicationId = "cz.ctu.fit.bi.and.semestral"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
//    implementation("androidx.core:core-ktx:1.13.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.0")
//    implementation("androidx.activity:activity-compose:1.9.0")
//
//    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.navigation:navigation-compose:2.7.7")
//    implementation("io.coil-kt:coil-compose:2.6.0")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    implementation("androidx.compose.material3:material3")
//    implementation("androidx.compose.material3:material3-window-size-class")
//    implementation("androidx.compose.runtime:runtime-livedata")
    implementation ("androidx.core:core-ktx:1.13.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation ("androidx.activity:activity-compose:1.9.0")
    implementation ("androidx.compose.ui:ui:1.6.7")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation("androidx.compose.material3:material3:1.3.0-alpha06")
    implementation ("androidx.compose.material:material:1.6.7")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.6.7")
    debugImplementation ("androidx.compose.ui:ui-tooling:1.6.7")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.6.7")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    //Coil
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Room
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    // Koin
    implementation(platform("io.insert-koin:koin-bom:3.5.3"))
    implementation("io.insert-koin:koin-android")
    implementation("io.insert-koin:koin-androidx-compose")

    // OkHttp
    val okHttpVersion = "4.12.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    //LifeCycle
    val lifecycleVersion = "2.8.0"
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")

    //Charts
    // For Jetpack Compose.
    implementation("com.patrykandpatrick.vico:compose:2.0.0-alpha.20")
    // For `compose`. Creates a `ChartStyle` based on an M2 Material Theme.
    implementation("com.patrykandpatrick.vico:compose-m2:2.0.0-alpha.20")
    // For `compose`. Creates a `ChartStyle` based on an M3 Material Theme.
    implementation("com.patrykandpatrick.vico:compose-m3:2.0.0-alpha.20")
    // Houses the core logic for charts and other elements. Included in all other modules.
    implementation("com.patrykandpatrick.vico:core:2.0.0-alpha.20")
    // For the view system.
//    implementation("com.patrykandpatrick.vico:views:2.0.0-alpha.20")

}