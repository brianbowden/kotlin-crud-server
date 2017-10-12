package com.peak.prototype

import com.peak.prototype.db.PeakDb
import com.peak.prototype.gen.Tables.USERS
import com.peak.prototype.gen.tables.records.UserRecord
import org.jooq.DSLContext

// A sealed class is similar in concept to an enum; it also seals the
// inheriting class so that it can not inherit from another class.
// I am using the sealed class here to make sure that our models for
// json generation don't accidentally expose unintended elements,
// and the `toJson(..)` method on RouteHandler only serializes models
// that extend JsonModel in order to avoid inadvertent data leakage.

sealed class JsonModel
interface Recordable<out Model, Record> {
    fun toRecord(ctx: DSLContext): Record?
    fun fromRecord(record: Record?): Model?
}

data class User(
    var id: Int? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var email: String? = null

) : JsonModel(), Recordable<User, UserRecord> {

    override fun toRecord(ctx: DSLContext): UserRecord? {
        val record = ctx.newRecord(USERS)
        record.id = id
        record.firstName = first_name
        record.lastName = last_name
        record.email = email
        return record
    }

    override fun fromRecord(record: UserRecord?): User {
        id = record?.id
        first_name = record?.firstName
        last_name = record?.lastName
        email = record?.email
        return this
    }

}

data class UsersResponse(var users: List<User>?) : JsonModel()

data class ApiError(var message: String?) : JsonModel()