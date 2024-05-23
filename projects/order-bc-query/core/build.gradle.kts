import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
}

dependencies {
    //Active MQ
    api("org.springframework.boot:spring-boot-starter-activemq")
    api("org.apache.activemq:activemq-broker")
    api("org.springframework:spring-jms")

    // DataBase connection core
    runtimeOnly("mysql:mysql-connector-java")

    //Core
    api("com.google.code.findbugs:jsr305:3.0.2")

    // Core Kotlin Extensions
    api("org.jetbrains.kotlin:kotlin-reflect")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //Data Base
    api("org.springframework.boot:spring-boot-starter-jdbc")

    // JPA
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")

    // Logging
    api("net.logstash.logback:logstash-logback-encoder:6.4")
    api("org.springframework.cloud:spring-cloud-starter-sleuth:3.0.3")

    // Gson
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")

    // Network
    api("org.apache.httpcomponents:httpcore:4.4.13")

    // Security
    api("io.jsonwebtoken:jjwt:0.2")
    api("org.springframework.boot:spring-boot-starter-security")

    // Spring Boot Core
    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-web")

    // Swagger
    api("io.springfox:springfox-boot-starter:3.0.0")
    api("io.springfox:springfox-swagger-ui:3.0.0")

    // Spring Boot Core
    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-devtools")

    implementation("com.google.code.gson:gson:2.10.1")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly("org.projectlombok:lombok:1.18.22")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
    testImplementation("org.mockito:mockito-inline:3.8.0")

    // Validation
    api("org.springframework.boot:spring-boot-starter-validation:2.5.0")

    // Elastic
    api("co.elastic.apm:apm-agent-attach:1.30.0")

    //Aws DynamodDb
    implementation("com.amazonaws:aws-java-sdk-dynamodb:1.11.573")
    implementation("com.github.derjust:spring-data-dynamodb:5.1.0")

    //EKS
    api("io.awspring.cloud:spring-cloud-starter-aws-parameter-store-config:2.4.1")
    api("io.awspring.cloud:spring-cloud-aws-autoconfigure:2.4.1")
    api("org.springframework.boot:spring-boot-starter-actuator:2.7.1")
    api("io.awspring.cloud:spring-cloud-starter-aws-secrets-manager-config:2.4.1")

}

apply(plugin = "io.spring.dependency-management")
dependencyManagement {
    imports {
        mavenBom("io.awspring.cloud:spring-cloud-aws-dependencies:2.4.1")
    }
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false