package ru.aao.geolocation.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.work.InputChanges

open class PgContainerStartTask: DefaultTask() {
    override fun getGroup(): String = "containers"

    var pgUrl = ""

    @TaskAction
    fun execute(inputs: InputChanges) {
        val message = if (inputs.isIncremental) "CHAMGED inputs are out of date"
        else "ALL inputs are out of date"
        println(message)
    }
}