plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("com.google.guava:guava:33.3.1-jre")
    implementation("org.apache.commons:commons-text")
    implementation(project(":model"))
    implementation(project(":utilities"))
}

application {
    // Define the main class for the application.
    mainClass = "com.brandonsonoda.app.Main"
}

tasks.getByName("run", JavaExec::class) {
   standardInput = System.`in`
}
