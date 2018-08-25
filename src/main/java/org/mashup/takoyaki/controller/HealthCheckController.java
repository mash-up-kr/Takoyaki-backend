package org.mashup.takoyaki.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController {

    //private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @GetMapping(value = "/health-check")
    public String healthCheck() {
        log.info("180817 logTest1");

        return "takoyaki server is running now.";
    }

    @GetMapping(value = "/character-encoding-check", produces = "text/plain;charset=utf-8")
    public String encodingCheck(@RequestParam("str") String str) {
        log.info("character-encoding-check msg : {}", str);
        log.info("180817 logTest2");
        return "check character encoding str : " + str;
    }

}
