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
