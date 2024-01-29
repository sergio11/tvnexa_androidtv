@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        namespace = "com.dreamsoftware.tvnexa"
        minSdk = libs.versions.minSdkTv.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // Build Variables
        buildConfigField("String", "BASE_URL", "\"http://192.168.1.39:9090/api/v1/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidComposeCompiler.get()
    }

    kapt {
        correctErrorTypes = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(project(mapOf("path" to ":utils")))
    implementation(project(mapOf("path" to ":player")))
    implementation(project(mapOf("path" to ":exoplayer")))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.androidx.compose.bom)
    implementation(libs.bundles.compose.tv)
    implementation(libs.bundles.compose.accompanist)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.line.awesome.icons)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.coil.compose)
    implementation(libs.androidx.datastore.preferences)

    androidTestImplementation(platform(libs.compose.bom))
}
