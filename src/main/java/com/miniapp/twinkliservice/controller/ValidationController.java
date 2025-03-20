package com.miniapp.twinkliservice.controller;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.miniapp.twinkliservice.util.HmacUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Emir Suleimanov
 */
@RestController
public class ValidationController {
  private final Logger LOGGER = LoggerFactory.getLogger(ValidationController.class);

  @Value("${telegram.bot.token}")
  private String BOT_TOKEN;

  @Value("${api.service.frontend}")
  private String FRONTEND_URL;

  @PostMapping("/validation")
  public ResponseEntity<Map<String, Object>> validateUser(@RequestBody String[] verifiableData) {
    LOGGER.info("INFO: frontend url: " + FRONTEND_URL);
    LOGGER.info("INFO: BOT_TOKEN receive: " + BOT_TOKEN);
    if (verifiableData.length != 2 || verifiableData[0].isBlank() || verifiableData[1].isBlank()) {
      return ResponseEntity.badRequest().body(Map.of("success", false, "error", "Verifiable data empty"));
    }

    try {
      String dataCheckString = verifiableData[0];
      String hash = verifiableData[1];
      LOGGER.info("INFO: dataCheckString: {} \nhash: {}", dataCheckString, hash);
      byte[] secretKey = HmacUtil.hmacSha256Raw(
          BOT_TOKEN.getBytes(StandardCharsets.UTF_8), "WebAppData".getBytes(StandardCharsets.UTF_8)
      );
      String checkHashHex = HmacUtil.hmacSha256Hex(dataCheckString.getBytes(StandardCharsets.UTF_8), secretKey);
      Map<String, Object> response = new HashMap<>();
      boolean isValid = checkHashHex.equals(hash);
      response.put("success", isValid);
      return ResponseEntity.ok(response);
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
    }
  }
}
