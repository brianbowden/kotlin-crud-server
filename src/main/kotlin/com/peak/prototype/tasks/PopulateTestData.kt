package com.peak.prototype.tasks

import com.peak.prototype.User
import com.peak.prototype.db.PeakDb
import com.peak.prototype.gen.tables.Users.*
import com.peak.prototype.gen.tables.records.UserRecord
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table

class PopulateTestData : BaseTask() {

    companion object {
        val command = "populateTestData"
    }

    override fun runTask(args: List<String>) {

        PeakDb.use {
            truncate(USERS).execute()
            insertInto(USERS, USERS.FIRST_NAME, USERS.LAST_NAME, USERS.EMAIL)
                .values("Brian", "Bowden", "brian@bowden.com")
                .values("Rob", "Scott", "rob@scott.com")
                .values("Lee", "Adkins", "lee@adkins.com")
                .values("Adam", "Tootle", "adam@tootle.com")
                .values("David", "Hunt", "david@hunt.com")
                .values("Michael", "Brooks", "michael@brooks.com")
                .execute()
        }

        /**
        val usersTable = table("users")
        PeakDb.use {
        truncate(usersTable)
        insertInto(usersTable, field("first_name"), field("last_name"), field("email"))
        .values("Brian", "Bowden", "brian@bowden.com")
        .values("Rob", "Scott", "rob@scott.com")
        .values("Lee", "Adkins", "lee@adkins.com")
        .values("Adam", "Tootle", "adam@tootle.com")
        .values("David", "Hunt", "david@hunt.com")
        .values("Michael", "Brooks", "michael@brooks.com")
        .execute()
        }
         **/
    }

}