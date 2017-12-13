package leetcode.easy;

public class IntegerProblems {
  public static void main(String[] args) {
    IntegerProblems problems = new IntegerProblems();
    for (int i = 1; i < 100 * 10; i++) {
      System.out.println(String.format("%1$5d == %2$s", i, problems.convertToTitle(i)));
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
}
