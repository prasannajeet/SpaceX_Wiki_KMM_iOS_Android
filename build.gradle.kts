buildscript {
    val composeVersion by extra("1.2.0-rc01")
    val javaVersion by extra(JavaVersion.VERSION_11)
    val compileTargetSdk by extra(32)
    val minSdk by extra(27)
    val versionCode by extra(1)
    val applicationId by extra("app.prasan.spacexwiki.android")

    val versionNameMajor = "0"
    val versionNameMinor = "1"
    val versionNamePatch = "0"
    val versionName by extra("$versionNameMajor.$versionNameMinor.$versionNamePatch")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath("com.android.tools.build:gradle:7.2.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}