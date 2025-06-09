group = "me.xemor"
version = "4.4.8"
description = "configurationdata"
java.sourceCompatibility = JavaVersion.VERSION_17

plugins {
    java
    `maven-publish`
    id("io.sentry.jvm.gradle") version("3.12.0")
}

repositories {
    mavenCentral()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://mvn-repo.arim.space/lesser-gpl3")}
    maven { url = uri("https://oss.sonatype.org/content/groups/public/")}
    mavenLocal()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:20.1.0")
    compileOnly("com.fasterxml.jackson.core:jackson-core:2.18.3")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.14.0")
}

publishing {
    repositories {
        maven {
            name = "xemorReleases"
            url = uri("https://repo.xemor.zip/releases")
            credentials(PasswordCredentials::class)
            authentication {
                isAllowInsecureProtocol = true
                create<BasicAuthentication>("basic")
            }
        }

        maven {
            name = "xemorSnapshots"
            url = uri("https://repo.xemor.zip/snapshots")
            credentials(PasswordCredentials::class)
            authentication {
                isAllowInsecureProtocol = true
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = rootProject.name
            version = rootProject.version.toString()
            from(project.components["java"])
        }
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters") // necessary for jackson to be able to see parameter names
}
//End of auto generated

// Handles version variables etc
tasks.processResources {
    inputs.property("version", rootProject.version)
    expand("version" to rootProject.version)
}

publishing {

}
