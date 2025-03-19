package com.miniapp.twinkliservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emir Suleimanov
 */
@RestController
public class TestController {
  @GetMapping("/test")
  public ResponseEntity<?> get() {
    return ResponseEntity.ok("Hello World");
  }
}
