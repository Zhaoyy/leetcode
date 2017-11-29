package leetcode.easy;

import java.util.Stack;

public class StringProblems {

  /**
   * 判断由'(', ')', '{', '}', '[' and ']'组成的字符串是否匹配。 "()" and "()[]{}" are all valid but "(]" and
   * "([)]" are not
   */
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();

    for (char c : chars) {
      if (stack.empty()) {
        stack.push(c);
        continue;
      }

      char temp = stack.peek();
      int t = Math.abs(temp - c);
      if (t == 1 || t == 2) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return stack.isEmpty();
  }

  public boolean isValidBetter(String s) {
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();

    for (char c : chars) {
      if (c == '(' || c == '[' || c == '{') { // 起始符必须入栈
        stack.push(c);
      } else if (stack.isEmpty()) { // 空栈时如果遇到结束符则不匹配
        return false;
      } else {
        char temp = stack.peek();  // ([])这种也算不匹配
        int t = Math.abs(temp - c);
        return t == 1 || t == 2;
      }
    }
    return false;
  }

  /**
   * 返回needle在haystack中第一次出现的位置，类似{@link String#indexOf(String)}
   */
  public int strStr(String haystack, String needle) {

    if (haystack.isEmpty() && needle.isEmpty()) return 0;

    int result = -1;
    for (int i = 0; i < haystack.length(); i++) {
      if (haystack.substring(i).startsWith(needle)) {
        result = i;
        break;
      }
    }

    return result;
  }

  /**
   * count and say problem
   */
  public String countAndSay(int n) {

    if (n <= 0) return "-1";

    StringBuilder sb = new StringBuilder("1");
    String pre;
    for (int i = 1; i < n; i++) {
      pre = sb.toString();
      sb.setLength(0);

      int count = 0;
      char temp = 0;
      for (int j = 0; j < pre.length(); j++) {
        if (count == 0) {
          temp = pre.charAt(j);
          count++;
        } else if (temp == pre.charAt(j)) {
          count++;
        } else {
          sb.append(count).append(temp);
          count = 1;
          temp = pre.charAt(j);
        }
      }

      if (count > 0) {
        sb.append(count).append(temp);
      }
    }

    return sb.toString();
  }

  /**
   * 返回给定英文语句中最后一个单词的长度，没有则返回0
   */
  public int lengthOfLastWord(String s) {
    if (s == null || s.isEmpty()) return 0;
    String[] ss = s.split(" ");
    return ss.length > 0 ? ss[ss.length - 1].length() : 0;
  }

  public int lengthOfLastWordBetter(String s) {
    if (s == null) return 0;
    s = s.trim();

    if (s.isEmpty()) return 0;

    int index = s.lastIndexOf(" ");
    if (index == -1) return s.length();
    return s.length() - index - 1;
  }

  /**
   * 返回给定两个二进制字符串的和
   */
  public String addBinary(String a, String b) {
    char[] result, as = a.toCharArray(), bs = b.toCharArray();
    int n, i = 1, flag = '1' * 2;
    int la = as.length, lb = bs.length;
    if (la > lb) {
      n = lb;
      result = new char[la + 1];
    } else {
      result = new char[lb + 1];
      n = la;
    }

    int lr = result.length, carry = 0;
    for (; i <= n; i++) {
      char ia = as[la - i];
      char ib = bs[lb - i];

      int sum = ia + ib + carry;

      if (sum >= flag) {
        result[lr - i] = sum > flag ? '1' : '0';
        carry = 1;
      } else {
        result[lr - i] = (char) (sum - '0');
        carry = 0;
      }
    }

    char[] need = la > n ? as : bs;

    flag = flag / 2;
    for (; i <= need.length; i++) {
      int sum = need[need.length - i] + carry;

      if (sum > flag) {
        carry = 1;
        result[lr - i] = '0';
      } else {
        result[lr - i] = (char) sum;
        carry = 0;
      }
    }

    result[0] = carry > 0 ? '1' : ' ';

    return String.valueOf(result).trim();
  }
}
