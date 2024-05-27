plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)

}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.runtime)
            implementation(libs.kotlinx.datetime)
            //DI Koin
            implementation(libs.koin.core)


        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.android.driver)

            //DI Koin
            implementation(libs.koin.android)
        }
        iosMain.dependencies {
            implementation(libs.native.driver)
        }
    }
}

sqldelight {
    databases {
        create("NoteDB") {
            packageName.set("com.jetbrains.spacetutorial.cache")
        }
    }
}

android {
    namespace = "com.blackhand.sqldelight"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
