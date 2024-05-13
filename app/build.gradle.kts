plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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

//    implementation("org.apache.pdfbox:pdfbox:3.0.2")
//    implementation("com.tom-roush:pdfbox-android:2.0.27.0")

    implementation(project(":itext5"))
    implementation(project(":itext7"))
    implementation(project(":aspose"))
    implementation(project(":apryse"))
//    implementation(project(":ironpdf"))
//    implementation(project(":openpdf"))
//    implementation(project(":pdfium"))
    implementation(project(":mu-pdf"))
}
