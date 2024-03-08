package org.example.task_one.exceptions.handle;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandler implements ProblemHandling, SecurityAdviceTrait, DbAdviceTrait {

}