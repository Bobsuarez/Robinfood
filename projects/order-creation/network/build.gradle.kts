import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

dependencies {

    api(project(":core"))

    //Active MQ
    api("org.springframework.boot:spring-boot-starter-activemq")
    api("org.apache.activemq:activemq-broker")
    api("org.springframework:spring-jms")

    // DataBase connection core
    runtimeOnly("mysql:mysql-connector-java")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    // Network
    api("org.apache.httpcomponents:httpcore:4.4.15")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-devtools")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testCompileOnly("org.projectlombok:lombok:1.18.18")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false