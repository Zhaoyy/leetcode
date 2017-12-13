package leetcode.easy;

import java.util.Stack;

public class MinStack {
  private Stack<Integer> minStack;
  private Stack<Integer> stack;

  /** initialize your data structure here. */
  public MinStack() {
    stack = new Stack<>();
    minStack = new Stack<>();
  }

  public void push(int x) {
    if (minStack.isEmpty() || x < minStack.peek()) minStack.push(x);
    stack.push(x);
  }

  public void pop() {
    int r = stack.pop();
    if (r == minStack.peek()) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }
}
