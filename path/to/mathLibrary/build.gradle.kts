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
                username = findProperty("ossrh.username")?.toString() ?: ""
                password = findProperty("ossrh.password")?.toString() ?: ""
            }

            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
} 