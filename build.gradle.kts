plugins {
    id("java")
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "io.edwinjmunoz"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    //runtimeOnly {
    //exclude(group = "commons-logging", module = "commons-logging")
    // }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")
    implementation("org.springdoc:springdoc-openapi-starter-common:2.4.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.4.0")

    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("commons-validator:commons-validator:1.7")
    implementation("commons-io:commons-io:2.15.1")

    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    //implementation("org.flywaydb:flyway-core")
    //implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("javax.validation:validation-api:2.0.0.Final")
    implementation("org.jetbrains:annotations:24.0.0")

    compileOnly("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("junit:junit:4.13.1")
}

tasks.test {
    useJUnitPlatform()
}