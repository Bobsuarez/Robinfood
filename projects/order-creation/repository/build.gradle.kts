import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
}

dependencies {

	api(project(":network"))
	api(project(":core"))

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.22")
	annotationProcessor("org.projectlombok:lombok:1.18.22")

	// Spring Boot
	implementation("org.springframework.boot:spring-boot-devtools")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-inline:3.11.2")
	testImplementation("org.mockito.kotlin:mockito-kotlin:3.1.0")
	testCompileOnly("org.projectlombok:lombok:1.18.22")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false