package org.example.task_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableMongoRepositories
public class TaskOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskOneApplication.class, args);
    }

}
