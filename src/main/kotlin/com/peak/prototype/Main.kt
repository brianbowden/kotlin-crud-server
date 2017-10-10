package com.peak.prototype

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("Main")

fun main(args: Array<String>) {
    log.info("Firing up routes...")
    Routes.listen()
    log.info("Routes fired up")
}