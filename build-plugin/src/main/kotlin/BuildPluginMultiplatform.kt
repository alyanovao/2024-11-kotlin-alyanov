package ru.aao.geolocation.plugin

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.the
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("LongMethod", "MagicNumber")
fun KotlinMultiplatformExtension.configureTargets(project: Project) {
    val libs = project.the<LibrariesForLibs>()
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.language.get()))
//        vendor.set(JvmVendorSpec.AZUL)
    }

    jvm {
        compilations.configureEach {
            compilerOptions.configure {
                jvmTarget.set(JvmTarget.valueOf("JVM_${libs.versions.jvm.compiler.get()}"))
            }
        }
    }
    linuxX64()
    macosArm64()
    macosX64()
    project.tasks.withType(JavaCompile::class.java) {
        sourceCompatibility = libs.versions.jvm.language.get()
        targetCompatibility = libs.versions.jvm.compiler.get()
    }
}
