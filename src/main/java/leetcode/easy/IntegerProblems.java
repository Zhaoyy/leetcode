package leetcode.easy;

public class IntegerProblems {
  public static void main(String[] args) {
    IntegerProblems problems = new IntegerProblems();
    //BigInteger result = BigInteger.valueOf(1);
    //for (int i = 1; i < 60; i++) {
    //  System.out.println(String.format("%1$5d ==> %2$d", i, problems.countPrimes(i)));
    //}
    System.out.println(problems.firstBadVersion(6));
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
   * 是否是快乐数字（各位数字的平方之和最终为1，否则进入一种循环）
   */
  public boolean isHappy(int n) {
    int f, s;
    f = s = n;
    do {
      s = sumSquareDigit(s);
      f = sumSquareDigit(f);
      f = sumSquareDigit(f);
    } while (f != s);
    return f == 1;
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

  /**
   * 求小于给定数字的质数个数
   */
  public int countPrimes(int n) {

    boolean[] notPrime = new boolean[n];
    int count = 0;
    for (int i = 2; i < n; i++) {
      if (!notPrime[i]) {
        count++;
        for (int j = 2; i * j < n; j++) {
          notPrime[i * j] = true;
        }
      }
    }

    return count;
  }

  public boolean isPrime(int n) {
    if (n < 2) return false;
    if (n == 2 || n == 3) return true;
    for (int i = 2; i < (n < 8 ? n : Math.sqrt(n) + 1); i++) {
      if (n % i == 0) return false;
    }
    return true;
  }

  /**
   * 判断一个数字是否等于由两个数字得到平方和
   */
  public boolean isPowerSumOfTwo(int n) {
    int m = n > 2 ? n / 2 : n;
    for (int i = 0; i < m; i++) {
      int t = n - i * i;
      int r = (int) Math.sqrt(t);

      if (t == r * r) return true;
    }

    return false;
  }

  /**
   * 判断一个数字是否是2的n次幂
   */
  public boolean isPowerOfTwo(int n) {
    if (n <= 0) return false;
    while (n > 0) {
      if (n == 1 || n == 2) return true;
      if (n % 2 > 0) return false;
      n = n / 2;
    }
    return true;
  }

  /**
   * 把数字各位相加，如果结果大于9则继续处理，找到找到不大于9的结果。
   */
  public int addDigits(int num) {
    int result;
    do {
      result = sumDigits(num);
      num = result;
    } while (result > 9);
    return result;
  }

  public int addDigitsBetter(int num) {
    return (num - 1) % 9 + 1;
  }

  private int sumDigits(int num) {
    int result = 0;
    while (num > 0) {
      result += num % 10;
      num /= 10;
    }

    return result;
  }

  /**
   * 判断一个数字的质因数是否只有2,3,5（数字1也满足条件）。
   */
  public boolean isUgly(int num) {
    if (num < 1) return false;
    if (num == 1) return true;
    do {
      if (num % 2 == 0) {
        num = num / 2;
      } else if (num % 3 == 0) {
        num = num / 3;
      } else if (num % 5 == 0) {
        num = num / 5;
      } else {
        return false;
      }
    } while (num > 5);
    return true;
  }

  /**
   * 找出第一次失败的版本，一旦失败后面的版本都会失败。
   */
  public int firstBadVersion(int n) {
    int l = 1, r = n;
    while (l < r) {
      int m = l / 2 + r / 2 + (r % 2 == 0 ? 1 : 0);
      if (isBadVersion(m)) {
        r = m - 1;
      } else {
        l = m + 1;
      }
    }
    return isBadVersion(r) ? r : r + 1;
  }

  private boolean isBadVersion(int n) {
    return n > 5;
  }
}
