package com.peak.prototype

import com.peak.prototype.tasks.TaskFactory
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("Main")

fun main(args: Array<String>) {
    if (args.size == 1 && args[0] == "start") {
        log.info("Firing up routes...")
        Routes.listen()
        log.info("Routes fired up")

    } else if (args.size > 1 && args[0] == "run") {
        val task = TaskFactory.getInstance(args[1])
        // pass along extra args to the task
        task.runTask(args.slice(2 until args.size))

    } else {
        System.exit(0)
    }
}