package org.example.task_one.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.task_one.utils.enums.RecordType;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class LogRecord {
    @Id
    private String id;

    private RecordType recordType;
    private String recordMessage;
    private LocalDateTime recordUTCTime;

    public LogRecord(RecordType recordType, String recordMessage, LocalDateTime recordUTCTime)
    {
        this.recordType = recordType;
        this.recordMessage = recordMessage;
        this.recordUTCTime = recordUTCTime;
    }
}
