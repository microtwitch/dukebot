plugins {
    id("org.springframework.boot") version "2.6.6"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.sonarqube") version "3.3"
}

group "de.com.fdm"
version "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:2.6.7")
    implementation("org.springframework:spring-web:5.3.20")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.github.twitch4j:twitch4j:1.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.mockito:mockito-core:4.5.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sonarqube {
    properties {
        property("sonar.projectKey", "microtwitch_dukebot")
        property("sonar.organization", "microtwitch")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}