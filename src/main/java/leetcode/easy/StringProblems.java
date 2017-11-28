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
}
