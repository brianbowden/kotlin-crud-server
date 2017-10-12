package com.peak.prototype

import com.peak.prototype.db.PeakDb
import com.peak.prototype.extensions.*
import com.peak.prototype.gen.Tables.*
import org.jooq.exception.DataAccessException
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
                try {
                    val users = PeakDb.ctx
                        .selectFrom(USERS)
                        .map { User().fromRecord(it) }

                    toJson(UsersResponse(users))

                } catch (e: Exception) {
                    log.error("Error getting users list", e)
                    toError("Something went wrong. This is a bad example of error handling, but it works.", 500)
                }
            }

            get("/:id") {
                try {
                    val id = req.params("id").toIntOrNull()
                    if (id == null) {
                        res.status(400)
                        return@get toJson(ApiError("User ID is missing or invalid"))
                    }

                    val user =
                        PeakDb.ctx
                            .selectFrom(USERS)
                            .where(USERS.ID.eq(id))
                            .map { User(it.id, it.firstName, it.lastName, it.email) }
                            .firstOrNull()

                    toJson(user)

                } catch (e: DataAccessException) {
                    toError("Error accessing database, but you don't need to know that.", 500)
                    log.error("Error accessing database", e)
                } catch (e: Exception) {
                    log.error("Error getting user", e)
                    res.status(500)
                }
            }

            post("") {
                val user = fromJson<User>(req.body())

                // Validation
                when {
                    user == null -> "No valid user parameters provided"
                    user.first_name.isNullOrBlank() -> "First name is required"
                    user.last_name.isNullOrBlank() -> "Last name is required"
                    user.email.isNullOrBlank() -> "Email is required"
                    else -> null
                }?.apply {
                    // This doesn't run if the validation error is null.
                    // Otherwise, it throws an the error.
                    return@post toError(this)
                }

                try {
                    val existingCount = PeakDb.ctx
                        .selectCount()
                        .from(USERS)
                        .where(USERS.EMAIL.eq(user?.email?.trim()))
                        .fetchOne(0) as Int

                    if (existingCount > 0) {
                        return@post toError("User with this email already exists")
                    }

                    val record = user?.toRecord(PeakDb.ctx)
                    record?.store()

                    // pipe ID and such back into the data object
                    user?.fromRecord(record)

                } catch (e: Exception) {
                    log.error(e.message)
                    return@post toError("Unable to save user", 500)
                }

                toJson(user)
            }
        }
    }

}