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

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

// Настройка кодировки при ЗАПУСКЕ приложения
tasks.withType<JavaExec> {
    jvmArgs("-Dfile.encoding=UTF-8")
    standardOutput = System.out
    errorOutput = System.err
}

tasks.withType<Test> {
    useJUnitPlatform()
}