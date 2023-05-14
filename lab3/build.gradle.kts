import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.22"
    java
    jacoco
}

group = "ru.sennik"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.seleniumhq.selenium:selenium-java:4.9.1")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.9.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.0")
    testImplementation ("com.github.stefanbirkner:system-lambda:1.2.0")
    testImplementation("org.mockito:mockito-core:2.24.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

tasks.test {
    useJUnitPlatform()
//    finalizedBy(tasks.jacocoTestReport)
//    finalizedBy(tasks.jacocoTestCoverageVerification)
}


//tasks.jacocoTestReport {
//    dependsOn(tasks.test)
//    reports {
//        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
//    }
//}
//tasks.jacocoTestCoverageVerification {
//    violationRules {
//        rule {
//            limit {
//                counter = "CLASS"
//                value = "MISSEDCOUNT"
//                minimum = "0".toBigDecimal()
//            }
//        }
//        rule {
//            limit {
//                counter = "METHOD"
//                value = "COVEREDRATIO"
//                minimum = "0.9".toBigDecimal()
//            }
//        }
//        rule {
//            limit {
//                counter = "LINE"
//                value = "COVEREDRATIO"
//                minimum = "0.9".toBigDecimal()
//            }
//        }
//        rule {
//            limit {
//                counter = "INSTRUCTION"
//                value = "COVEREDRATIO"
//                minimum = "0.9".toBigDecimal()
//            }
//        }
//    }
//}
//tasks.check {
//    dependsOn(tasks.jacocoTestReport)
//    dependsOn(tasks.jacocoTestCoverageVerification)
//}