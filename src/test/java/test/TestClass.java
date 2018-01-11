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
}
