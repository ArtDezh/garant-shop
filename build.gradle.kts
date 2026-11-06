plugins {
    java
    application
}

group = "ru.garantshop"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Lombok для упрощения кода
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    // Для работы с JSON
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
}

application {
    mainClass.set("ru.garantshop.Main")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("-Dfile.encoding=UTF-8",
        "-Dsun.stdout.encoding=UTF-8",
        "-Dsun.stderr.encoding=UTF-8")
}

tasks.withType<Test> {
    useJUnitPlatform()
}