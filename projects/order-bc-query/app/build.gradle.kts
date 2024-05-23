import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

val appGroup = "com.robinfood"
val appName = "order-bc-query"

plugins {
    id("com.github.johnrengelman.processes") version "0.5.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.3.0"
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

springBoot {
    mainClass.set("$appGroup.app.OrderBcQueryApplication")
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

allprojects {
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

openApi {
    apiDocsUrl.set("http://localhost:8080/api-docs")
}

dependencies {
    implementation(project(":repository"))

    // Debugging
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    // Swagger 3.0
    implementation("org.springdoc:springdoc-openapi-ui:1.6.6")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("com.google.code.gson:gson:2.10.1")

    // Testing
    testImplementation("org.mockito:mockito-inline:3.8.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")

    //Aws DynamodDb
    implementation ("com.amazonaws:aws-java-sdk-dynamodb:1.11.573")
    implementation ("com.github.derjust:spring-data-dynamodb:5.1.0")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
bootJar.enabled = true