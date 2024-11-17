plugins {
    id("buildlogic.java-library-conventions")
}

dependencies {
  implementation("com.google.guava:guava:33.3.1-jre")
  implementation(project(":utilities"))
}
