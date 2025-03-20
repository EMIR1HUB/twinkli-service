package com.miniapp.twinkliservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emir Suleimanov
 */
@RestController
public class TestController {
  private final Logger LOGGER = LoggerFactory.getLogger(ValidationController.class);

  @Value("${telegram.bot.token}")
  private String BOT_TOKEN;

  @Value("${api.service.frontend}")
  private String FRONTEND_URL;

  @GetMapping("/test")
  public ResponseEntity<?> get() {
    LOGGER.info("INFO: frontend url: " + FRONTEND_URL);
    LOGGER.info("INFO: BOT_TOKEN receive: " + BOT_TOKEN);
    return ResponseEntity.ok("Hello World");
  }
}
