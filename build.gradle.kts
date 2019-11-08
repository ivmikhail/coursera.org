plugins {
    id("org.sonarqube") version "2.8"
}

allprojects {
    repositories {
        jcenter()
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "ivmikhail_coursera.org")
    }
}
