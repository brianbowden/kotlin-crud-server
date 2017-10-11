package com.peak.prototype.tasks

object TaskFactory {
    fun getInstance(taskName: String): BaseTask = when(taskName) {
        PopulateTestData.command -> PopulateTestData()
        else -> throw IllegalArgumentException("Invalid task name")
    }
}