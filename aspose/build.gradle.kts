plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.aspose"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")

//    implementation(files("libs/aspose-pdf-24.4-jdk17.jar"))
//    implementation(files("libs/aspose-pdf-24.4-jdk16.jar"))
//    implementation(files("libs/aspose-pdf-24.4.jar"))
//    implementation("com.aspose:aspose-pdf:9.5.0")
    implementation(files("libs/aspose-pdf-android-via-java-23.2.jar"))
}
