package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestClass {

  @Test public void calPowerOfFour() throws Exception {
    int result = 1;
    for (int i = 1; i < 100; i++) {
      result *= 4;
      System.out.println(result + "");
    }
  }
}
