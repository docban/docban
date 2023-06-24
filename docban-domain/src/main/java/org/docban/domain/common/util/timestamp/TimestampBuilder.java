package org.docban.domain.common.util.timestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TimestampBuilder {

    public static final LocalDateTime now() {
        final ZonedDateTime zonedDateTime = ZonedDateTime.now();
        return zonedDateTime.toLocalDateTime();
    }
}
