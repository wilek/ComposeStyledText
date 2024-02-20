plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "eu.wilek.textcombine"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = "1.8"
    }
}


tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {

    implementation(libs.androidx.annotation)
    implementation(libs.androidx.appcompact.resources)

    testRuntimeOnly(libs.junit5.engine.vintage)

    testImplementation(libs.junit)
    testImplementation(libs.junit5.core)

    //testImplementation("androidx.test:core:1.5.0")
    testImplementation("androidx.test.ext:junit-ktx:1.1.5")

    //testImplementation("org.robolectric:robolectric:4.10.2")
    testImplementation(libs.mockito.kotlin)

}