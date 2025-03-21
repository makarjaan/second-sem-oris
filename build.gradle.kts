import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("war")
}

group = "com.makarova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "com.makarova.Main"
}

dependencies {
    implementation("org.springframework:spring-webmvc:6.2.3")
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper:11.0.5")
    implementation("jakarta.servlet:jakarta.servlet-api:6.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<ShadowJar> {
    mergeServiceFiles()
}