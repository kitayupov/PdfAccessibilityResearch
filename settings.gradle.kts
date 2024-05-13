pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven{ setUrl("https://jitpack.io")}
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://pdftron-maven.s3.amazonaws.com/release")
        maven("https://maven.ghostscript.com")
    }
}

rootProject.name = "Pdf Accessibility Research"

include(":app")
include(":itext7")
include(":itext5")
include(":aspose")
include(":apryse")
//include(":ironpdf")
//include(":openpdf")
//include(":pdfium")
include(":mu-pdf")
