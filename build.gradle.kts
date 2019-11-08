allprojects {
    repositories {
        jcenter()
    }
}
plugins {
    id("org.sonarqube") version "2.8"
}

sonarqube {
    properties {
        property("sonar.projectKey", "ivmikhail_coursera.org")
    }
}
