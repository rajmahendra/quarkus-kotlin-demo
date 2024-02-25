plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.allopen") version "1.9.22"
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
  //  implementation("io.quarkus:quarkus-resteasy-jsonb")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:kotlin-extensions")
}

group = "org.acme"
version = "1.0.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}

val quarkusDeployment by configurations.creating
dependencies {
quarkusDeployment("io.quarkus:quarkus-vertx-http-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-jsonp-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-netty-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-kotlin-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-mutiny-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-jsonb-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-smallrye-context-propagation-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-arc-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-core-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-vertx-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-virtual-threads-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-resteasy-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-resteasy-server-common-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-resteasy-jsonb-deployment:3.7.4")
quarkusDeployment("io.quarkus:quarkus-resteasy-common-deployment:3.7.4")
}
typealias PrintWriter = java.io.PrintWriter
typealias FileWriter = java.io.FileWriter
tasks.register("listQuarkusDependencies") {
    val writer = PrintWriter(FileWriter("/tmp/177236199935876386.txt"))
    quarkusDeployment.incoming.artifacts.forEach {
        writer.println(it.id.componentIdentifier)
        writer.println(it.file)
    }
    val componentIds = quarkusDeployment.incoming.resolutionResult.allDependencies.map { (it as ResolvedDependencyResult).selected.id }
    val result = dependencies.createArtifactResolutionQuery()
        .forComponents(componentIds)
        .withArtifacts(JvmLibrary::class, SourcesArtifact::class)
        .execute()
    result.resolvedComponents.forEach { component ->
        val sources = component.getArtifacts(SourcesArtifact::class)
        sources.forEach { ar ->
            if (ar is ResolvedArtifactResult) {
                writer.println(ar.id.componentIdentifier)
                writer.println(ar.file)
            }
        }
    }
    writer.close()
}