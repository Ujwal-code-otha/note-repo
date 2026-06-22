plugins {
    kotlin("jvm")
}

group = "com.smartnotes.appium"
version = "1.0.0"

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
    // Run only the 350-test main suite
    filter {
        includeTestsMatching("com.smartnotes.appium.tests.GeneratedAppiumTests")
    }
    // Tell ExcelReporter exactly where to write the .xlsx file
    systemProperty("appium.report.dir", layout.buildDirectory.dir("reports/appium").get().asFile.absolutePath)
    systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results").get().asFile.absolutePath)
    // Working dir = subproject dir so relative paths resolve correctly
    workingDir = projectDir
    // Don't fail the Gradle task on test failures — let the report still be written
    ignoreFailures = true
    testLogging {
        events("passed", "failed", "skipped")
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(17)
}
