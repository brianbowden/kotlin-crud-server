package com.peak.prototype

import com.peak.prototype.extensions.res
import com.peak.prototype.extensions.toJson
import com.squareup.moshi.Moshi
import org.slf4j.LoggerFactory
import spark.Spark.initExceptionHandler
import spark.Spark.path
import spark.kotlin.*

object Routes {

    private val log = LoggerFactory.getLogger(Routes::class.java)

    fun listen() {
        initExceptionHandler {
            log.error("Failed to start Spark: ${it.message}")
            stop()
            System.exit(100)
        }

        path("/users") {

            get("") {
                val users = mutableListOf(
                        User("Jim", "jim@gmail.com"),
                        User("Tim", "tim@gmail.com"))

                toJson(UsersResponse(users))
            }
        }
    }

}