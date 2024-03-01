package org.example.task_one.helper;

import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Configuration
public class CurrentTime {
    public LocalDateTime getUTCTime() {
        return OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime();
    }
}
