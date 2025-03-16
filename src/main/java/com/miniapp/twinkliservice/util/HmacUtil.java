package com.miniapp.twinkliservice.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Emir Suleimanov
 * <br/>
 * Документация <a href="https://core.telegram.org/widgets/login#checking-authorization">telegram</a> по проверки авторизации
 */
public class HmacUtil {

  private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

  /**
   * Создаёт HMAC-SHA256 (бинарный результат)
   * <br/>
   * Аналог php метода  <a href="https://www.php.net/manual/ru/function.hash-hmac.php">hash_hmac('sha256', $data, $key, true)</a>
   */
  public static byte[] hmacSha256Raw(byte[] data, byte[] key)
      throws NoSuchAlgorithmException, InvalidKeyException {
    SecretKeySpec secretKeySpec = new SecretKeySpec(key, HMAC_SHA256_ALGORITHM);
    Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
    mac.init(secretKeySpec);
    return mac.doFinal(data);
  }

  /**
   * Создаёт HMAC-SHA256 (результат HEX-строка)
   * <br/>
   * Аналог php метода  <a href="https://www.php.net/manual/ru/function.hash-hmac.php">hash_hmac('sha256', $data, $key, false)</a>
   */
  public static String hmacSha256Hex(byte[] data, byte[] key)
      throws NoSuchAlgorithmException, InvalidKeyException {
    byte[] hash = hmacSha256Raw(data, key);
    HexFormat hex = HexFormat.of();
    return hex.formatHex(hash);
  }

  /**
   * Преобразует строку HEX в байтовый массив
   */
  public static byte[] hexToBytes(String hex) {
    int len = hex.length();
    byte[] data = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                            + Character.digit(hex.charAt(i + 1), 16));
    }
    return data;
  }
}
