/*
 * This file is generated by jOOQ.
*/
package com.peak.prototype.gen;


import com.peak.prototype.gen.tables.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PeakProtoDev extends SchemaImpl {

    private static final long serialVersionUID = 1896675400;

    /**
     * The reference instance of <code>peak_proto_dev</code>
     */
    public static final PeakProtoDev PEAK_PROTO_DEV = new PeakProtoDev();

    /**
     * The table <code>peak_proto_dev.users</code>.
     */
    public final Users USERS = com.peak.prototype.gen.tables.Users.USERS;

    /**
     * No further instances allowed
     */
    private PeakProtoDev() {
        super("peak_proto_dev", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Users.USERS);
    }
}
