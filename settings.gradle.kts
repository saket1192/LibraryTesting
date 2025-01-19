pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/saket1192/LibraryTesting")
            credentials {
                username = "saket1192"
                password = "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"
            }
        }
    }
}

rootProject.name = "TestingLibrary"
include(":app")
include(":mathLibrary")
