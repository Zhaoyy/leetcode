package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class IntegerProblems {
  public static void main(String[] args) {
    IntegerProblems problems = new IntegerProblems();
    //BigInteger result = BigInteger.valueOf(1);
    //for (int i = 1; i < 60; i++) {
    //  if (problems.checkPerfectNumber(i)) System.out.println(i);
    //}
    System.out.println(problems.uniquePaths(4, 4));
  }

  /**
   * https://leetcode.com/problems/rectangle-area/description/
   */
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    long a = A, b = B, c = C, d = D, e = E, f = F, g = G, h = H;
    long s1 = (c - a) * (d - b) + (g - e) * (h - f);
    long s2 = (Math.min(c, g) - Math.max(a, e) <= 0 || Math.min(d, h) - Math.max(b, f) <= 0) ? 0
        : (Math.min(c, g) - Math.max(a, e)) * (Math.min(d, h) - Math.max(b, f));
    return (int) (s1 - s2);
  }

  /**
   * https://leetcode.com/problems/bitwise-and-of-numbers-range/description/
   */
  public int rangeBitwiseAnd(int m, int n) {
    int i = 0;
    for (; m != n; ++i) {
      m >>= 1;
      n >>= 1;
    }
    return n << i;
  }

  /**
   * https://leetcode.com/problems/gray-code/description/
   */
  public List<Integer> grayCode(int n) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < 1 << n; i++) result.add(i ^ i >> 1);
    return result;
  }

  /**
   * https://leetcode.com/problems/unique-paths/description/
   */
  public int uniquePaths(int m, int n) {
    int N = n + m - 2;// how much steps we need to do
    int k = m - 1; // number of steps that need to go down
    double res = 1;
    // here we calculate the total possible path number
    // Combination(N, k) = n! / (k!(n - k)!)
    // reduce the numerator and denominator and get
    // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
    for (int i = 1; i <= k; i++)
      res = res * (N - k + i) / i;
    return (int) res;
  }

  /**
   * https://leetcode.com/problems/powx-n/description/
   */
  public double myPow(double x, int n) {
    if (x == 0) return 0;
    if (n == 0) return 1;

    if (n < 0) {
      if (n == Integer.MIN_VALUE) {
        n++; // Make -2147483648 to -2147483647
        if (x < 0) {
          x = -x; //we made n odd so need to update sign
        }
      }
      n = -n;
      x = 1 / x;
    }

    return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
  }

  /**
   * https://leetcode.com/problems/divide-two-integers/description/
   */
  public int divide(int dividend, int divisor) {
    if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
    boolean neg = (dividend > 0) ^ (divisor > 0);
    long dividendL = Math.abs((long) dividend);
    long divisorL = Math.abs((long) divisor);
    int ans = 0;
    while (divisorL <= dividendL) {
      long temp = divisorL, mul = 1;
      while (dividendL >= temp << 1) {
        temp <<= 1;
        mul <<= 1;
      }
      ans += mul;
      dividendL -= temp;
    }

    return neg ? -ans : ans;
  }

  /**
   * https://leetcode.com/problems/generate-parentheses/description/
   */
  public List<String> generateParenthesis(int n) {
    List<String> ans = new ArrayList<>();
    dfsParenthesis("", 0, 0, n, ans);
    return ans;
  }

  private void dfsParenthesis(String temp, int lCount, int rCount, int n,
      List<String> ans) {
    if (lCount == n && rCount == n) {
      ans.add(temp);
      return;
    }

    if (lCount == n) {
      dfsParenthesis(temp + ")", lCount, rCount + 1, n, ans);
    } else if (lCount == rCount) {
      dfsParenthesis(temp + "(", lCount + 1, rCount, n, ans);
    } else {
      dfsParenthesis(temp + "(", lCount + 1, rCount, n, ans);
      dfsParenthesis(temp + ")", lCount, rCount + 1, n, ans);
    }
  }

  /**
   * https://leetcode.com/problems/binary-number-with-alternating-bits/description/
   */
  public boolean hasAlternatingBits(int n) {
    if (n % 2 != 0) {
      n >>= 1;
    }

    while (n > 0) {
      if (n % 2 == 0 && n % 4 != 0) {
        n >>= 2;
      } else {
        return false;
      }
    }

    return true;
  }

  public boolean hasAlternatingBitsBetter(int n) {
    int x = (n / 2) ^ n;
    return (x & (x + 1)) == 0;
  }

  /**
   * 判断一个数字是否等于其所有除自身之外的约数的和。
   */
  public boolean checkPerfectNumber(int num) {

    if (num < 6) return false;

    int sum = 1;
    double max = Math.sqrt(num);
    for (int i = 2; i < max; i++) {
      if (num % i == 0) {
        sum += i + num / i;
      }
    }

    return sum == num;
  }

  /**
   * 返回7进制字符串
   */
  public String convertToBase7(int num) {
    if (num == 0) return "0";
    StringBuilder sb = new StringBuilder();
    boolean flag = num < 0;
    num = Math.abs(num);
    while (num > 0) {
      sb.append(num % 7);
      num = num / 7;
    }

    if (flag) sb.append('-');

    return sb.reverse().toString();
  }

  /**
   * https://leetcode.com/problems/construct-the-rectangle/description/
   */
  public int[] constructRectangle(int area) {
    int m = (int) Math.sqrt(area), offset = 0;
    while (area % (m - offset) > 0) {
      offset++;
    }

    m = m - offset;
    offset = area / m;
    return new int[] {Math.max(m, offset), Math.min(m, offset)};
  }

  /**
   * https://leetcode.com/problems/largest-palindrome-product/discuss/96306
   */
  public int largestPalindrome(int n) {
    long max = (long) (Math.pow(10, n) - 1);
    long min = max / 10;

    for (long num = max; num > min; num--) {
      long left = num, right = 0;
      for (long i = num; i != 0; right = right * 10 + i % 10, i /= 10, left *= 10) ;
      long p = left + right;

      for (long i = max; i > min; i--) {
        long j = p / i;
        if (j > i || j <= min) break;
        if (p % i == 0) return (int) (p % 1337);
      }
    }

    return 9;
  }

  /**
   * 求给定的正整数的补码
   */
  public int findComplement(int num) {
    boolean flag = false;
    for (int i = 31; i >= 0; i--) {
      if ((num & (1 << i)) > 0) flag = true;

      if (flag) num ^= 1 << i;
    }

    return num;
  }

  /**
   * 找出给定的两个正整数中二进制相同位不同的总数
   */
  public int hammingDistance(int x, int y) {
    int xor = x ^ y;
    int d = 0;
    while (xor > 0) {
      if ((xor & 1) > 0) d++;
      xor >>= 1;
    }
    return d;
  }

  /**
   * 给定n个硬币，进行排列，第n行有n个硬币，求满状态的行数。
   */
  public int arrangeCoins(int n) {
    if (n <= 0) return 0;
    if (n <= 2) return 1;

    int l = 0, r = n, i;

    while (true) {
      i = (l + r) / 2;

      if (i > 65535) i = 65535;

      double t = 0.5 * i * i + 0.5 * i;
      if (t > n) {
        r = i;
      } else if (t < n) {

        if (i == 65535) break;

        double m = 0.5 * i * i + 1.5 * i + 1.5;
        if (m > n) {
          break;
        } else if (m == n) {
          i = i + 1;
          break;
        } else {
          l = i;
        }
      } else if (t == n) break;
    }

    return i;
  }

  /**
   * 3的倍数输出Fizz，5的倍数输出Buzz，同时满足输出FizzBuzz
   */
  public List<String> fizzBuzz(int n) {
    String fizz = "Fizz", buzz = "Buzz";
    List<String> result = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      if (i % 3 == 0 && i % 5 == 0) {
        result.add(fizz + buzz);
      } else if (i % 3 == 0) {
        result.add(fizz);
      } else if (i % 5 == 0) {
        result.add(buzz);
      } else {
        result.add(String.valueOf(i));
      }
    }
    return result;
  }

  public String toHex(int num) {

    String result = "";
    while (num != 0) {
      result = getHexChar(num & 0xf) + result;
      num = num >>> 4;
    }

    return result;
  }

  private char getHexChar(int digit) {
    return (char) (digit < 10 ? '0' + digit : 'a' + digit - 10);
  }

  /**
   * 有4个LED灯（分别表示1,2,4,8）用来表示12个小时，有6个LED灯（1,2,4,8,16,32）来表示60分钟。 求有num个灯亮时，能够表示哪几种时间。
   * 使用DTS-深度优先查找算法。
   */
  public List<String> readBinaryWatch(int num) {
    List<String> result = new ArrayList<>();
    boolean[] chosen = new boolean[10];
    int[] cache = new int[] {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
    dts(result, chosen, cache, 0, num);

    return result;
  }

  private void dts(List<String> res, boolean[] chosen, int[] cache, int start, int k) {
    if (k > 8) return;

    if (k == 0) {
      int h = 0, m = 0;
      for (int i = 0; i < 10; i++) {
        if (chosen[i]) {
          if (i < 4) {
            h += cache[i];
          } else {
            m += cache[i];
          }
        }
      }

      if (h < 12 && m < 60) {
        if (m < 10) {
          res.add(h + ":0" + m);
        } else {
          res.add(h + ":" + m);
        }
      }
    } else {
      for (int i = start; i < 11 - k; i++) {
        chosen[i] = true;
        dts(res, chosen, cache, i + 1, k - 1);
        chosen[i] = false;
      }
    }
  }

  /**
   * 返回数列1,2,3,4,5,6,7,8,9,10.....中索引所在的数字（10被看做两个数字，同理100为三个数字）
   */
  public int findNthDigit(int n) {

    if (n > 0 && n < 10) return n;
    int i = 1, rate = 9, startIndex = 0;

    do {
      startIndex += rate * i;
      rate = rate * 10;
      i++;
    } while (rate < 900000000 && n - startIndex > rate * i);

    startIndex++;

    int r = n - startIndex;
    int t = r % i;
    r = (int) (r / i + Math.pow(10, i - 1));

    return String.valueOf(r).charAt(t) - '0';
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

  /**
   * https://leetcode.com/problems/nim-game/description/
   *
   * 推测可发现如果n为4的倍数，则不能保证成功。
   */
  public boolean canWinNim(int n) {
    return n % 4 != 0;
  }

  /**
   * 判断一个数字是否是3的幂
   *
   * 1162261467是int中最大的3的幂
   */
  public boolean isPowerOfThree(int n) {
    return (n > 0 && 1162261467 % n == 0);
  }

  /**
   * 1073741824
   */
  public boolean isPowerOfFour(int num) {
    if (num <= 0) return false;
    if (num == 1) return true;

    if ((num & 3) > 0) return false;

    int pre = 0;
    while (num > 0) {

      int t = num & 1;
      num = num >>> 1;
      if (t > 0 && num > 0) return false;
      if (t > 0) {
        return pre % 2 == 0;
      } else {
        pre++;
      }
    }

    return true;
  }

  public boolean isPowerOfFourBetter(int num) {
    return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
  }

  /**
   * 牛顿迭代法求一个数的开方
   */
  public boolean isPerfectSquare(int num) {
    float val = num;
    float last;
    do {
      last = val;
      val = (val + num / val) / 2;
    } while (Math.abs(val - last) > 1);

    int r = (int) val;
    return r * r == num;
  }

  /**
   * 位运算求和
   */
  public int getSum(int a, int b) {
    if (b == 0) return a;

    int c = a ^ b;
    int d = (a & b) << 1;
    return getSum(c, d);
  }

  public int guessNumber(int n) {
    int l = 1, r = n;
    int mid = getMid(l, r);
    while (guess(mid) != 0 && r - l > 1) {
      if (guess(mid) > 0) {
        l = mid;
      } else if (guess(mid) < 0) {
        r = mid;
      }
      mid = getMid(l, r);
    }

    return guess(mid) == 0 ? mid : guess(l) == 0 ? l : r;
  }

  private int getMid(int l, int r) {
    int s = l + r;
    int t = s / 2;
    return l / 2 + r / 2 + 1;
  }

  private int guess(int n) {
    int t = 1702766719;
    if (n == t) return 0;
    return n < t ? 1 : -1;
  }
}
