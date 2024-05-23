import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
}

dependencies {

	api(project(":core"))

	// DataBase connection core
	runtimeOnly ("mysql:mysql-connector-java")

	// Network
	api("org.apache.httpcomponents:httpcore:4.4.13")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false