package com.peak.prototype.db

import com.peak.prototype.util.Config
import org.jooq.DSLContext
import org.jooq.impl.DSL

object PeakDb {

    val ctx = DSL.using(
        Config.DB_URL,
        Config.DB_USER,
        Config.DB_PASS
    )

    fun use(handler: DSLContext.()->Unit) {
        // !! means "Trust me, compiler, this won't be null at this point, even though you think it could be."
        ctx!!.apply(handler)
    }
}