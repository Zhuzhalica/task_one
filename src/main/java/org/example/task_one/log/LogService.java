package org.example.task_one.log;

import lombok.RequiredArgsConstructor;
import org.example.task_one.model.LogRecord;
import org.example.task_one.utils.enums.RecordType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository repository;

    public void Log(LogRecord logRecord) {
        repository.insert(logRecord);
    }

    public List<LogRecord> Read(RecordType recordType) {
        return repository.findAllByRecordType(recordType);
    }
}
