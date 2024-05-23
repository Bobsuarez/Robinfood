import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val appGroup = "com.robinfood"
val appName = "order-bc"
val appVersion = "1.0.4"
val javaVersion = "11"

plugins {
    jacoco

    id("idea")
    id("java")
    kotlin("jvm") version "1.4.31"
    kotlin("plugin.spring") version "1.4.31"

    id("org.springframework.boot") version "2.6.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    id("com.palantir.docker") version "0.26.0"
    id("org.sonarqube") version "3.1.1"
}

springBoot {
    mainClass.set("$appGroup.app.OrderBcApplication")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-loader:2.6.5")
}

tasks["sonarqube"].dependsOn("test")

version = appVersion
group = appGroup

allprojects {
    apply(plugin = "jacoco")

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.1")
    }
}

subprojects {

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        finalizedBy(testReport)
        finalizedBy(jacocoRootReport)
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}

val jacocoRootReport = tasks.register<JacocoReport>("jacocoRootReport") {
    subprojects {
        this@subprojects.plugins.withType<JacocoPlugin>().configureEach {
            this@subprojects.tasks.matching { task ->
                task.extensions.findByType<JacocoTaskExtension>() != null }.configureEach {
                sourceSets(this@subprojects.the<SourceSetContainer>().named("main").get())
                executionData(this)
            }
        }
    }
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/reports/jacoco/test/jacocoTestReport.xml")
        html.isEnabled = true
    }
}

val testReport = tasks.register<TestReport>("testReport") {
    destinationDir = file("$buildDir/reports/jacoco/test")
    reportOn(subprojects.mapNotNull { subproject -> subproject.tasks.findByPath("test") })
}