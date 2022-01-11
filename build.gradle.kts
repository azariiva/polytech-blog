val kotlinVersion: String by System.getProperties()
val ktorVersion: String by System.getProperties()
val logbackVersion: String by System.getProperties()

plugins {
    kotlin("jvm").version(System.getProperty("kotlinVersion"))
    java
    application
}

group = "me.freedom4live.ktor"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven {
        setUrl("https://kotlin.bintray.com/ktor")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion") // gradle.properties: ktor_version=1.3.2 Using netty as a server engine
    implementation ("ch.qos.logback:logback-classic:$logbackVersion") //logback as a logging provider
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion") //Needed for session support
    implementation("io.ktor:ktor-auth:$ktorVersion") // Here we adding dependency for auth support, but it has to be enabled explicitly
    implementation("io.ktor:ktor-jackson:$ktorVersion") // For serialization
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion") // We are going to cover all the stuff with tests, to prove our solution works
}
