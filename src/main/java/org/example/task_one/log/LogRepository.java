package org.example.task_one.log;

import org.example.task_one.model.LogRecord;
import org.example.task_one.utils.enums.RecordType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<LogRecord, String> {
    List<LogRecord> findAllByRecordType(RecordType recordType);
}
