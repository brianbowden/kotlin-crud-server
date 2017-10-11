package com.peak.prototype.db

import com.peak.prototype.util.Config
import org.jooq.DSLContext
import org.jooq.impl.DSL

object PeakDb {

    private var dslContext: DSLContext? = null

    fun use(handler: DSLContext.()->Unit) {
        if (dslContext == null) {
            dslContext = DSL.using(
                    Config.DB_URL,
                    Config.DB_USER,
                    Config.DB_PASS
            )
        }

        // !! means "Trust me, compiler, this won't be null at this point, even though you think it could be."
        dslContext!!.apply(handler)
    }
}