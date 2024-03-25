plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.alireza.imageSearchApp"
    compileSdk = libs.versions.app.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.alireza.imageSearchApp"
        minSdk = libs.versions.app.minSdk.get().toInt()
        targetSdk = libs.versions.app.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.alireza.imageSearchApp.config.HiltTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Projects
    implementation(project(":core:systemDesign"))
    implementation(project(":searchImage"))
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)


    // Test
    testImplementation(libs.bundles.test)


    // Android Test
    androidTestImplementation(libs.bundles.test.ui)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.bundles.paging)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    kspAndroidTest(libs.hilt.kapt.test)

}
