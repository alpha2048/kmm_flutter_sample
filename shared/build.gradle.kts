import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

//plugins {
//    kotlin("multiplatform")
//    id("com.android.library")
//}
//
//kotlin {
//    androidTarget {
//        @OptIn(ExperimentalKotlinGradlePluginApi::class)
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_11)
//        }
//    }
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "Shared"
//            isStatic = true
//        }
//    }
//
//    sourceSets {
//        commonMain.dependencies {
//            // put your Multiplatform dependencies here
//        }
//    }
//}
//
//android {
//    namespace = "com.example.kmp_flutter_sample.shared"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_11
//        targetCompatibility = JavaVersion.VERSION_11
//    }
//    defaultConfig {
//        minSdk = libs.versions.android.minSdk.get().toInt()
//    }
//}

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //implementation("androidx.datastore:datastore-preferences-core:1.1.1")
            }
        }

        val androidMain by getting { // note androidMain here
            dependencies {
                implementation("androidx.datastore:datastore:1.1.1")
                implementation("androidx.datastore:datastore-preferences:1.1.1")
            }
        }
    }

    jvmToolchain(17)
}

android {
    namespace = "com.example.kmp_flutter_sample.shared"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = 21
    }
}