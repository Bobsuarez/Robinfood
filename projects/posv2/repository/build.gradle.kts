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
		jcenter()
		gradlePluginPortal()
	}
}

dependencies {

	api(project(":network"))

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.2")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false