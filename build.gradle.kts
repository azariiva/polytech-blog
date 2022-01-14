val kotlinVersion: String by project
val ktorVersion: String by project
val logbackVersion: String by project
val sqldelightVersion: String by project
val hikariVersion: String by project
val mysqlConnectorVersion: String by project
val exposedVersion: String by project

plugins {
    kotlin("jvm") version "1.5.31"
    java
    application
}

group = "me.freedom4live.ktor"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

java.sourceSets["main"].java.srcDirs("src")
kotlin.sourceSets["main"].kotlin.srcDirs("src")
sourceSets["main"].resources.srcDirs("resources")

java.sourceSets["test"].java.srcDirs("test")
kotlin.sourceSets["test"].kotlin.srcDirs("test")
sourceSets["test"].resources.srcDirs("testresources")

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
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-joda")
    testImplementation("io.ktor:ktor-server-tests:$ktorVersion") // We are going to cover all the stuff with tests, to prove our solution works

    implementation("com.zaxxer:HikariCP:$hikariVersion")
    runtimeOnly("mysql:mysql-connector-java:$mysqlConnectorVersion")
    // using exposed as active record provider
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation ("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation ("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
