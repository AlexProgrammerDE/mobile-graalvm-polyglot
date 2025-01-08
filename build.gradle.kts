plugins {
    java
    application
    id("com.gluonhq.gluonfx-gradle-plugin") version "1.0.23"
}

group = "net.pistonmaster"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.graalvm.polyglot:polyglot:24.1.1")
    implementation("org.graalvm.polyglot:java:24.1.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
        vendor = JvmVendorSpec.GRAAL_VM
    }
}

application {
    mainModule = "net.pistonmaster.nativepolyglot"
    mainClass = "net.pistonmaster.nativepolyglot.Main"
}

gluonfx {
    target = "host"
    if (project.hasProperty("target")) {
        target = project.property("target").toString()
    }
    compilerArgs.addAll(listOf(
        "-H:+UnlockExperimentalVMOptions",
        "--language:nfi",
        "-Dtruffle.TruffleRuntime=com.oracle.truffle.api.impl.DefaultTruffleRuntime",
        "-H:+AllowIncompleteClasspath"
    ))
}

tasks {
    distZip {
        onlyIf { false }
    }
    distTar {
        onlyIf { false }
    }
    startScripts {
        onlyIf { false }
    }
}
