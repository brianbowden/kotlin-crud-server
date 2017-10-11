package com.peak.prototype.util

import java.util.*

object Config {
    val DB_URL: String?
        get() = System.getenv("PEAK_DB_URL")

    val DB_USER: String?
        get() = System.getenv("PEAK_DB_USER")

    val DB_PASS: String?
        get() = System.getenv("PEAK_DB_PASS")
}