package leetcode.easy;

import java.util.Stack;

public class MinStackBetter {
  private int min;
  private Stack<Integer> stack;

  /** initialize your data structure here. */
  public MinStackBetter() {
    stack = new Stack<>();
    min = Integer.MAX_VALUE;
  }

  public void push(int x) {
    if (x <= min) {
      stack.push(min);
      min = x;
    }
    stack.push(x);
  }

  public void pop() {
    int r = stack.pop();
    if (r == min) {
      min = stack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return min;
  }
}
