import java.util.regex.Pattern.compile

// Required by the 'shadowJar' task
//project.setProperty("mainClassName", "br.com.itau.bootcamp.ApplicationKt")

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.kotlin.kapt") version "1.6.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
//    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("io.micronaut.application") version "3.2.1"

}
version = "0.1"
group = "br.com.itau.bootcamp"
java.sourceCompatibility = JavaVersion.VERSION_11

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-http")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    //    implementation("software.amazon.awssdk:dynamodb")
    implementation("com.github.derjust:spring-data-dynamodb:5.1.0")
    compile("com.amazonaws:aws-java-sdk-dynamodb:1.11.887")
    implementation("io.github.boostchicken:spring-data-dynamodb:5.2.1")

//    "kotlinCompilerClasspath"(fileTree("build/kotlin"))
    implementation("org.apache.commons:commons-lang3:3.5")
//    api("org.apache.httpcomponents:httpclient:4.5.7")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}
//project.configurations.implementation.setCanBeResolved(true)

application {
    mainClass.set("br.com.itau.bootcamp.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

//tasks.withType<Jar> {
//    // Otherwise you'll get a "No main manifest attribute" error
//    manifest {
//        attributes(
//            ["Main-Class"] = "ApplicationKt"
//        )
//        DuplicatesStrategy.EXCLUDE
//    }

//    // To add all of the dependencies
//    from(sourceSets.main.get().output)
//
//    dependsOn(configurations.runtimeClasspath)
//    from({
//        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
//    })
//}

//sourceSets {
//        main.java.srcDirs += 'src/main/kotlin'
//}
// Personal preference. I hate having src/main/kotlin
// be the root, so I change it that 'src'
// is the root of my source directory.
///

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("br.com.itau.bootcamp.*")
    }
}

//// https://mvnrepository.com/artifact/javax.inject/javax.inject
//compile group: 'javax.inject', name: 'javax.inject', version: '1'
//tasks.withType<Jar>() {
//    manifest {
//        attributes["Main-Class"] = "br.com.itau.bootcamp.ApplicationKt"
//    }
//    configurations["compileClasspath"].forEach { file: File ->
//        from(zipTree(file.absoluteFile))
//    }
//}
//sourceSets {
//    main.java.srcDirs += 'src/main/kotlin'
//}

//jar {
//    manifest {
//        attributes "Main-Class": "your.package.classWithMain"
//        attributes "Class-Path": configurations.compile.collect { it.absolutePath }.join(" ")
//    }
//}

tasks.jar {
    manifest.attributes["Main-Class"] = "br.com.itau.bootcamp.ApplicationKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }
}