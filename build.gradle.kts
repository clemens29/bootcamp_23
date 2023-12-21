plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("java")
}

group = "de.bootcamp"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")


    // OpenApi/Swagger documentation
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    runtimeOnly("com.h2database:h2")
}
tasks {

    test {
        useJUnitPlatform()
    }
}
