package com.peak.prototype.tasks

import com.peak.prototype.db.PeakDb
import org.jooq.Record
import org.jooq.Table
import org.jooq.TableRecord
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table

class PopulateTestData : BaseTask() {

    companion object {
        val command = "populateTestData"
    }

    private val usersTable = table("users")

    override fun runTask(args: List<String>) {

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
    }

}