package com.peak.prototype.db;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class PeakGenStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String name = super.getJavaClassName(definition, mode);

        if (mode == Mode.POJO) {
            if (name.endsWith("s")) {
                name = name.substring(0, name.length() - 1);
            }
            name = name + "DataModel";

        } else if (mode == Mode.RECORD) {
            if (name.endsWith("sRecord")) {
                name = name.substring(0, name.length() - "sRecord".length()) + "Record";
            }
        }

        return name;
    }
}
