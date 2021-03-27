import net.minecrell.pluginyml.bungee.BungeePluginDescription

plugins {
    java
    id("net.minecrell.plugin-yml.bungee") version "0.3.0"
}

group = "com.github.syari.bungee.ipblacklist"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("net.md-5:bungeecord-api:1.16-R0.5-SNAPSHOT")
}

configure<BungeePluginDescription> {
    name = project.name
    version = project.version.toString()
    main = "com.github.syari.bungee.ipblacklist.Main"
    author = "sya_ri"
}