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

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.22")
	annotationProcessor("org.projectlombok:lombok:1.18.22")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testCompileOnly("org.projectlombok:lombok:1.18.22")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
	testImplementation("org.mockito:mockito-inline:3.8.0")
}

val jar: Jar by tasks
val bootJar: BootJar by tasks
jar.enabled = true
bootJar.enabled = false
