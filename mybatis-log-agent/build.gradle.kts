plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "com.zp"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mybatis:mybatis:3.5.6")
    implementation("com.mysql:mysql-connector-j:8.0.32")
    implementation("net.bytebuddy:byte-buddy:1.12.6")
    implementation("net.bytebuddy:byte-buddy-agent:1.12.6")
    implementation("cn.hutool:hutool-all:5.8.11")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}



tasks {
    // Set the JVM compatibility versions
    withType <JavaCompile> {
        sourceCompatibility = "8"
        targetCompatibility = "8"
        options.encoding = "UTF-8"
    }

    shadowJar {
        manifest {
            attributes(
                mapOf<String, String>("Premain-Class" to "com.zp.agent.MybatisAgent")
            )
        }
        archiveName = "${project.name}.${extension}"
    }

    test {
        useJUnitPlatform()
    }
}

