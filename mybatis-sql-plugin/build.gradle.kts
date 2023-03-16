plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.2"
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "com.zp"
version = "1.0.0"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")
    type.set("IU") // Target IDE Platform
    plugins.set(listOf("java"))
    instrumentCode.set(false)
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    implementation("org.mybatis:mybatis:3.5.6")
    implementation("com.mysql:mysql-connector-j:8.0.32")
    implementation("org.projectlombok:lombok:1.18.26")
    implementation("org.freemarker:freemarker:2.3.31")
    implementation("cn.hutool:hutool-all:5.8.11")
    implementation(files(listOf("/libs/mybatis-log-agent.jar")))
}

tasks {
    // Set the JVM compatibility versions
    withType <JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "UTF-8"
    }

    patchPluginXml {
        sinceBuild.set("211")
        untilBuild.set("222.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    shadowJar {
        archiveName="${project.name}.${extension}"
    }
}