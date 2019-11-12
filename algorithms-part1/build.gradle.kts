plugins {
    java
    jacoco
}

val packagePercolation = task("packagePercolation", type = Zip::class) {
    archiveFileName.set("percolation.zip")
    destinationDirectory.set(file("$buildDir/libs"))

    from("src/main/java") {
        include("Percolation.java")
        include("PercolationStats.java")
    }
}

val uberJar = task("uberJar", type = Jar::class) {
    archiveClassifier.set("uber")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)

    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}


val packageQueues = task("packageQueues", type = Zip::class) {
    archiveFileName.set("queues.zip")
    destinationDirectory.set(file("$buildDir/libs"))

    from("src/main/java") {
        include("Deque.java")
        include("RandomizedQueue.java")
        include("Permutation.java")
    }
}

tasks {
    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    test {
        useJUnitPlatform()
    }
    build {
        dependsOn(uberJar)
        dependsOn(packagePercolation)
        dependsOn(packageQueues)
    }
    jacocoTestReport {
        reports {
            xml.isEnabled = true
        }
    }
}

dependencies {
    implementation("edu.princeton.cs:algs4:1.0.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}
