import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

val appGroup = "com.robinfood"
val appName = "posv2"

plugins {
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.6.0"
    id("org.springframework.boot")
    id("java")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

springBoot {
    mainClass.set("$appGroup.app.POSApplicationKt")
}

tasks.withType<BootJar> {
    archiveFileName.set("$appName.jar")
    manifest {
        attributes(
            "Main-Class" to "org.springframework.boot.loader.PropertiesLauncher"
        )
    }
    isEnabled = true
    launchScript()
}

openApi {
    apiDocsUrl.set("http://localhost:8080/api-docs")
}

dependencies {
    implementation(project(":repository"))

    // Debugging
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Swagger 3.0
    implementation("org.springdoc:springdoc-openapi-ui:1.5.4")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")

    // Testing
    testImplementation("junit:junit:4.12")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Elastic
    implementation("co.elastic.apm:apm-agent-attach:1.30.0")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Jasper
    implementation("com.google.zxing:core:3.5.0")
    implementation("com.google.zxing:javase:3.5.0")
    implementation("com.google.zxing:android-core:3.3.0")
    implementation("net.sf.jasperreports:jasperreports:6.19.1")
    // https://mvnrepository.com/artifact/com.lowagie/itext
    implementation("com.lowagie:itext:2.1.7")
    testImplementation("net.sf.jasperreports", "jasperreports", "6.17.0")
    compileOnly("org.apache.commons", "commons-collections4", "4.2")
    compileOnly("com.lowagie", "itext", "2.1.7")


}

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = true