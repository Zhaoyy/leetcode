package leetcode.easy;

import java.math.BigInteger;

public class IntegerProblems {
  public static void main(String[] args) {
    IntegerProblems problems = new IntegerProblems();
    BigInteger result = BigInteger.valueOf(1);
    for (int i = 1; i < 60; i++) {
      String s = problems.convertToTitle(i);
      result = result.multiply(BigInteger.valueOf(i));
      System.out.println(String.format("%1$5d %3$5d == %2$s n!=%4$d", i, problems.convertToTitle(i),
          problems.titleToNumber(s), result));
    }
  }

  /**
   * 数字翻转（包括负数）
   */
  public int reverseInteger(int x) {
    int f = x < 0 ? -1 : 1;
    int result = 0;
    x = Math.abs(x);
    while (x > 0) {
      result = result * 10 + x % 10;
      x = x / 10;

      if (x > 0 && result > Integer.MAX_VALUE / 10) return 0;
    }

    return result * f;
  }

  /**
   * 判断回文数
   */
  public boolean isPalindrome(int x) {
    if (x >= 10 && x % 10 == 0) return false;
    int y = 0;
    while (y < x) {
      y = y * 10 + x % 10;
      x = x / 10;
    }
    if (x == y || (y / 10) == x) {
      return true;
    }
    return false;
  }

  /**
   * 简化实现开方
   */
  public int mySqrt(int x) {

    if (x < 2) return x;

    int l = 0, r = x;

    while (l + 1 < r) {
      int mid = l + (r - l) / 2;
      if (x / mid == mid) {
        return mid;
      } else if (x / mid > mid) {
        l = mid;
      } else {
        r = mid;
      }
    }

    if (x / r > r) return r;
    return l;
  }

  /**
   * 每次只能蹬一阶或者二阶台阶，求登上n阶台阶有几种方法。 分治法思路（斐波那契数列）
   */
  public int climbStairs(int n) {
    if (n <= 0) return 0;
    if (n == 1) return 1;
    if (n == 2) return 2;
    int pre1 = 2, pre2 = 1;
    int count = 0;
    for (int i = 2; i < n; i++) {
      count = pre1 + pre2;
      pre2 = pre1;
      pre1 = count;
    }

    return count;
  }

  /**
   * 1-A, 2-B, 3-C...26-Z, 27-AA
   */
  public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while (n > 0) {
      int t = n % 26;
      if (t == 0) {
        sb.append("Z");
        n = n / 26 - 1;
      } else {
        sb.append((char) ('A' - 1 + (n % 26)));
        n = n / 26;
      }
    }
    return sb.reverse().toString();
  }

  /**
   * 上面问题的逆向
   */
  public int titleToNumber(String s) {
    int flag = 'A' - 1, result = 0;
    for (int i = 0; i < s.length(); i++) {
      result = result * 26 + s.charAt(i) - flag;
    }

    return result;
  }

  /**
   * 求n的阶乘后面有几个0（只有碰到5的倍数会产生指数个0）
   */
  public int trailingZeroes(int n) {
    int extra = 0;
    while (n > 0) {
      extra += n / 5;
      n = n / 5;
    }
    return extra;
  }

  /**
   * 整数二进制反转（参数看做无符号整数）
   */
  public int reverseBits(int n) {
    int result = 0;
    // 32bit
    for (int i = 0; i < 32; i++) {
      result += n & 0x1;
      n >>>= 1;
      if (i < 31) {
        result <<= 1;
      }
    }

    return result;
  }

  /**
   * 返回无符号整数中二进制1的个数
   */
  public int hammingWeight(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
      result += n & 0x1;
      n >>>= 1;
    }
    return result;
  }

  /**
   * 是否是快乐数字（各位数字的平方之和最终为1，如果进入一种循环则）
   */
  public boolean isHappy(int n) {
    while (n > 1) {
      int t = sumSquareDigit(n);
      if (t == 1) return true;
      if (t == n) return false;
    }
    return false;
  }

  private int sumSquareDigit(int n) {
    int result = 0;
    while (n > 0) {
      int t = n % 10;
      result += t * t;
      n = n / 10;
    }
    return result;
  }
}
