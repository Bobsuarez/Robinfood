import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

val appGroup = "com.robinfood"
val appName = "order-creation-queries"

plugins {
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

springBoot {
    mainClass.set("$appGroup.app.OrderCreationQueriesApplication")
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

dependencies {
    implementation(project(":repository"))

    // Debugging
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.18")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = true
