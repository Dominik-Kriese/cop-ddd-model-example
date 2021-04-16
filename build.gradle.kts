plugins {
    java
    kotlin("jvm") version "1.4.10"
}

group = "de.novatec"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation("io.mockk:mockk:1.11.0")
    testImplementation("org.assertj:assertj-core:3.19.0")
}

tasks {
    test {
        useJUnitPlatform()
    }
}