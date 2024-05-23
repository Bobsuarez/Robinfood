import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
}

buildscript {
	repositories {
		mavenCentral()
		gradlePluginPortal()
	}
}

dependencies {

	//Core
	implementation("com.google.code.findbugs:jsr305:3.0.2")

	// Core Kotlin Extensions
	api("org.jetbrains.kotlin:kotlin-reflect")
	api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Coroutines
	api("io.projectreactor.kotlin:reactor-kotlin-extensions")
	api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// Logging
	api("net.logstash.logback:logstash-logback-encoder:6.4")
	api("org.springframework.cloud:spring-cloud-starter-sleuth:3.0.3")

	// Retrofit 2
	api("com.squareup.retrofit2:retrofit:2.9.0")
	api("com.squareup.retrofit2:converter-gson:2.9.0")
	api("com.squareup.okhttp3:logging-interceptor:4.5.0")

	// Security
	api("io.jsonwebtoken:jjwt:0.2")
	api("org.springframework.boot:spring-boot-starter-security")

	// Spring Boot Core
	api("org.springframework.boot:spring-boot-starter")
	api("org.springframework.boot:spring-boot-starter-web")

	// Swagger
	api("io.springfox:springfox-boot-starter:3.0.0")
	api("io.springfox:springfox-swagger-ui:3.0.0")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Validation
	api("org.springframework.boot:spring-boot-starter-validation:2.5.0")

	implementation("com.google.zxing:core:3.5.0")
	implementation("com.google.zxing:javase:3.5.0")

	// Jasper
	implementation("net.sf.jasperreports:jasperreports:6.19.1")
	// https://mvnrepository.com/artifact/com.lowagie/itext
	implementation("com.lowagie:itext:2.1.7")
	testImplementation("net.sf.jasperreports", "jasperreports", "6.17.0")
	compileOnly("org.apache.commons", "commons-collections4", "4.2")
	compileOnly("com.lowagie", "itext", "2.1.7")

	//aws parameter store
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