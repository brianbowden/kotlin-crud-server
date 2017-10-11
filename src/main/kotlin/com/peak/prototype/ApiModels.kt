package com.peak.prototype

import com.peak.prototype.gen.tables.records.UserRecord

// A sealed class is similar in concept to an enum; it also seals the
// inheriting class so that it can not inherit from another class.
// I am using the sealed class here to make sure that our models for
// json generation don't accidentally expose unintended elements,
// and the `toJson(..)` method on RouteHandler only serializes models
// that extend JsonModel in order to avoid inadvertent data leakage.

sealed class JsonModel

data class User(
    var id: Int? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var email: String? = null
) : JsonModel() {

    private var record: UserRecord? = null

    companion object {
        fun fromRecord(record: UserRecord): User {
            val user = User(record.id, record.firstName, record.lastName, record.email)
            user.record = record
            return user
        }
    }

    fun toRecord(): UserRecord {
        if (record != null) {
            record = UserRecord(id, firstName, lastName, email, null)
        }
        return record!!
    }

}


data class UsersResponse(var users: MutableList<User>) : JsonModel()