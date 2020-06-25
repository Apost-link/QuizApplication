package ru.aniskov.petproject.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class LoggingAspect {

    private static Logger _log = LoggerFactory.getLogger(LoggingAspect.class);

    @After("execution(* *.saveUser(..))")
    public void logAfterSaveUser(){
        _log.info("New user at " + new Date().toString());
    }

}
