plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.saket.mathlibrary"
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

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// Group and version should be at top level
group = "io.github.saket1192"
version = "1.0.0"
val artifactName = "mathlibrary"

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = project.group.toString()
            artifactId = artifactName
            version = project.version.toString()
            
            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set(artifactName)
                packaging = "aar"  // Added for Android library
                description.set("A mathematics utility library for Android")
                url.set("https://github.com/saket1192/LibraryTesting")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                
                developers {
                    developer {
                        id.set("saket1192")
                        name.set("Saket")
                        email.set("saket1192@gmail.com")
                    }
                }
                
                scm {
                    connection.set("scm:git:github.com/saket1192/LibraryTesting.git")
                    developerConnection.set("scm:git:ssh://github.com/saket1192/LibraryTesting.git")
                    url.set("https://github.com/saket1192/LibraryTesting/tree/main")
                }
            }
        }
    }

    repositories {
        maven {
            name = "ossrh"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            
            credentials {
                // Make sure these property names exactly match what's in gradle.properties
                username = findProperty("ossrh.username")?.toString() ?: ""
                password = findProperty("ossrh.password")?.toString() ?: ""
            }

            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}

signing {
    sign(publishing.publications["release"])
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}