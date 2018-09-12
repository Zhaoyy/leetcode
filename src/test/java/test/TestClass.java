package test;

import java.security.Key;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestClass {

  @Test public void calPowerOfFour() throws Exception {
    int result = 0;
    System.out.println(Integer.MAX_VALUE);
    for (int i = 1; i < Integer.MAX_VALUE; i++) {
      result += i;

      System.out.println(result + "\t:" + i);
      if (result < 0) break;
    }
  }

  @Test public void testFor() throws Exception {
    out:
    for (int i = 0; i < 3; i++) {
      System.out.println("outer");
      for (int j = 0; j < 2; j++) {
        System.out.println("inner");
        if (j % 2 == 0) {
          continue out;
        }
      }
    }
  }

  private static Key generateKey(String password) throws Exception {
    return new SecretKeySpec(password.getBytes(), "AES");
  }

  private void aa(Integer a, int b) {
    System.out.println(a + b);
  }

  private void aa(int a, int b) {
    System.out.println(a * b);
  }

  public static String java_openssl_encrypt(String data, String password)
      throws Exception {
    byte[] key = new byte[32];
    for (int i = 0; i < 32; i++) {
      if (i < password.getBytes().length) {
        key[i] = password.getBytes()[i];
      } else {
        key[i] = 0;
      }
    }

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");

    cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));

    String base64Str = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));

    return base64Str;
  }

  public static String encryptBase64(String data, String password, String alg) throws Exception {
    Key key = generateKey(password);
    Cipher cipher = Cipher.getInstance(alg);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encrypted = cipher.doFinal(data.getBytes());
    return new String(Base64.getEncoder().encode(encrypted));
  }

  public static Optional<String> algSelect(String padding) {
    if (padding == null || "pkcs5".equals(padding)) {
      return Optional.of("AES/ECB/PKCS5Padding");
    }
    if ("pkcs7".equals(padding)) {
      return Optional.of("AES/ECB/PKCS7Padding");
    }
    return Optional.empty();
  }

  @Test public void testAA() throws Exception {
    //Integer a = 2;
    //int b = 1;
    //aa(a % 2 == 0 ? a : b, 1);
    String str =
        "thirdUserId=92thirdpartytodiythirdNickName=邹寨奎thirdpartytodiymobile=13816046617thirdpartytodiyemail=xiao@haier.com";
    String password = "9af35048b0f74e7bb48d77781f7beaf5";
    System.out.println(java_openssl_encrypt(str, password));
  }
}
