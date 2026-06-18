plugins {
    kotlin("jvm") version "2.0.21"
}

group = "com.smartnotes.appium"
version = "1.0.0"

repositories {
    mavenCentral()
}

val junitVersion  = "5.10.2"
val allureVersion = "2.27.0"
val appiumVersion = "9.2.2"

dependencies {
    // Appium Java Client (includes Selenium WebDriver)
    implementation("io.appium:java-client:$appiumVersion")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    // Allure reporting
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("io.qameta.allure:allure-attachments:$allureVersion")

    // Excel reporting
    implementation("org.apache.poi:poi:5.2.5")
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    // Kotlin
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))

    // Logging
    implementation("org.slf4j:slf4j-simple:2.0.12")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("allure.results.directory", "$buildDir/allure-results")
    testLogging {
        events("passed", "failed", "skipped")
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(17)
}
