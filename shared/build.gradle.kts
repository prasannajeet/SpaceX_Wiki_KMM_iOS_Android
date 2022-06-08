plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.6.21"
    id("com.android.library")
    //id("io.realm.kotlin")
}

version = "0.1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared business logic, networking and db implementation for the ${rootProject.name} application"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {


            dependencies {
                implementation("io.insert-koin:koin-core:3.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
                implementation("io.ktor:ktor-client-core:2.0.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.1")
                implementation("io.ktor:ktor-client-logging:2.0.1")
                implementation("io.ktor:ktor-client-auth:2.0.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
                implementation("io.github.aakira:napier:2.6.1")
                //implementation("io.realm.kotlin:library-base:1.0.0")
                api("co.touchlab:kermit:1.1.2")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            val accompanistVersion = "0.23.1"

            dependencies {
                //compileOnly("io.realm.kotlin:library:1.0.0")
                implementation("io.ktor:ktor-client-okhttp:2.0.1")

                api("com.google.android.material:material:1.6.1")
                api("androidx.appcompat:appcompat:1.4.2")


                api("androidx.activity:activity-compose:1.4.0")
                api("com.google.android.material:compose-theme-adapter:1.1.10")
                api("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
                api("androidx.compose.material:material:${rootProject.extra["compose_version"]}")
                api("androidx.compose.ui:ui-tooling-preview:${rootProject.extra["compose_version"]}")

                api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
                api("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

                api("androidx.multidex:multidex:2.0.1") // For future proofing

                // Accompanist
                api("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")
                api("com.google.accompanist:accompanist-permissions:$accompanistVersion")
                api("com.google.accompanist:accompanist-swiperefresh:$accompanistVersion")
                api("com.google.accompanist:accompanist-flowlayout:$accompanistVersion")
                api("com.google.accompanist:accompanist-webview:0.24.9-beta")

                api("io.insert-koin:koin-android:3.2.0")
                api("me.onebone:toolbar-compose:2.3.3")
            }
        }
        val androidTest by getting {
            dependencies {
                //implementation(libs.bundles.shared.androidTest)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                //implementation("com.squareup.sqldelight:native-driver:1.5.3")
                implementation("io.ktor:ktor-client-darwin:2.0.1")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"].toString()
    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation("io.mockk:mockk:1.10.4")
    testImplementation("junit:junit:4.13.2")
}