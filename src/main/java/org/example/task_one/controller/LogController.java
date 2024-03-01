package org.example.task_one.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.example.task_one.model.LogRecord;
import org.example.task_one.utils.enums.RecordType;
import org.example.task_one.log.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logs")
public class LogController {
    private final LogService logService;

    @Operation(description = "Получить записи из логов по типу действия")
    @GetMapping("/{recordType}")
    public List<LogRecord> getLogs(@PathVariable String recordType) {
        var enumRecordType = TryParseRecordType(recordType);

        return logService.Read(enumRecordType);
    }

    private RecordType TryParseRecordType(String recordType) {
        try {
            var type = RecordType.valueOf(recordType);
            return type;
        } catch (Exception e) {
            throw new ValidationException("Такого типа записей нет");
        }
    }
}
