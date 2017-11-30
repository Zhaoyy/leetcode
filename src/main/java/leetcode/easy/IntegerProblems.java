package leetcode.easy;

public class IntegerProblems {

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
}
