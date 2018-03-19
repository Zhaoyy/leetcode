package test;

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

  @Test public void testAA() throws Exception {
    Integer a = 2;
    int b = 1;
    aa(a % 2 == 0 ? a : b, 1);
  }

  private void aa(Integer a, int b) {
    System.out.println(a + b);
  }

  private void aa(int a, int b) {
    System.out.println(a * b);
  }
}
