package leetcode.easy;

import java.util.LinkedList;
import java.util.List;

public class Node {
  public int val;
  public List<Node> children;

  public Node() {
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }

  /**
   * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
   */
  public List<Integer> preorder(Node root) {
    List<Integer> ans = new LinkedList<>();
    preorderHelper(root, ans);
    return ans;
  }

  private void preorderHelper(Node node, List<Integer> list) {
    if (node == null) return;
    list.add(node.val);
    if (node.children == null || node.children.size() == 0) return;
    for (Node child : node.children) {
      preorderHelper(child, list);
    }
  }

  /**
   * https://leetcode.com/problems/n-ary-tree-postorder-traversal/
   */
  public List<Integer> postorder(Node root) {
    List<Integer> ans = new LinkedList<>();
    postorderHelper(root, ans);
    return ans;
  }

  private void postorderHelper(Node node, List<Integer> list) {
    if (node == null) return;
    if (node.children != null) {
      for (Node child : node.children) {
        postorderHelper(child, list);
      }
    }

    list.add(node.val);
  }
}
