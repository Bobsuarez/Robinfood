import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
}

dependencies {

	//Core
	implementation("com.google.code.findbugs:jsr305:3.0.2")

	// Logging
	api("net.logstash.logback:logstash-logback-encoder:6.4")
	api("org.springframework.cloud:spring-cloud-starter-sleuth:3.0.3")

	// Core Kotlin Extensions
	api("org.jetbrains.kotlin:kotlin-reflect")
	api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.22")
	annotationProcessor("org.projectlombok:lombok:1.18.22")

	// Retrofit 2
	api("com.squareup.retrofit2:retrofit:2.7.0")
	api("com.squareup.retrofit2:converter-gson:2.7.0")
	api("com.squareup.okhttp3:logging-interceptor:4.5.0")

	// Security
	api("io.jsonwebtoken:jjwt:0.2")
	api("org.springframework.boot:spring-boot-starter-security")

	// Spring Boot Core
	api("org.springframework.boot:spring-boot-starter")
	api("org.springframework.boot:spring-boot-starter-web")

	// Swagger
	api("org.springdoc:springdoc-openapi-ui:1.6.11")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testCompileOnly("org.projectlombok:lombok:1.18.22")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.22")

	// Redis
	api("org.springframework.boot:spring-boot-starter-data-redis")
	api("redis.clients:jedis:4.2.0")

	// Validation
	api("org.springframework.boot:spring-boot-starter-validation:2.6.5")

	// Elastic
	api("co.elastic.apm:apm-agent-attach:1.30.0")

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
