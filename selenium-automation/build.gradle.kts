plugins {
    kotlin("jvm")
}

group = "com.smartnotes.selenium"
version = "1.0.0"

val seleniumVersion = "4.21.0"
val junitVersion   = "5.10.2"
val allureVersion  = "2.27.0"

dependencies {
    // Selenium WebDriver
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")
    implementation("io.github.bonigarcia:webdrivermanager:5.8.0")

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    // Allure reporting
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("io.qameta.allure:allure-attachments:$allureVersion")

    // Excel reporting (Apache POI)
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
    ignoreFailures = true
    systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results").get().asFile.absolutePath)
    systemProperty("selenium.report.dir", layout.buildDirectory.dir("reports/selenium").get().asFile.absolutePath)
    testLogging {
        events("passed", "failed", "skipped")
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(17)
}
