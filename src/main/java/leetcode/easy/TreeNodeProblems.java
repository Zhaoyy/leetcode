package leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeNodeProblems {

  public static void main(String[] args) {
    TreeNodeProblems problems = new TreeNodeProblems();
    TreeNode root = new TreeNode(1);
    System.out.println(problems.isSymmetric(root));
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    return compare(p, q) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  /**
   * 树是否对称
   */
  public boolean isSymmetric(TreeNode root) {

    return root == null || isSymmetric(root.left, root.right);
  }

  private boolean isSymmetric(TreeNode a, TreeNode b) {

    if (a == null && b == null) {
      return true;
    } else if (a != null && b != null && a.val == b.val) {
      return isSymmetric(a.left, b.right) && isSymmetric(a.right, b.left);
    } else {
      return false;
    }
  }

  private boolean compare(TreeNode a, TreeNode b) {
    return a != null && b != null && a.val == b.val || (a == null && b == null);
  }

  /**
   * 树的最大深度
   */
  public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int result = 1;
    result += Math.max(maxDepth(root.left), maxDepth(root.right));
    return result;
  }

  /**
   * 返回由子到根每层节点的列表
   */
  public List<List<Integer>> levelOrderBottom(TreeNode root) {

    List<List<Integer>> result = new ArrayList<>();
    buildOrderBottomList(result, 0, root);

    if (result.size() > 0) Collections.reverse(result);

    return result;
  }

  private void buildOrderBottomList(List<List<Integer>> list, int index, TreeNode node) {
    if (node == null) return;

    List<Integer> subList;
    if (list.size() > index) {
      subList = list.get(index);
    } else {
      subList = new ArrayList<>();
      list.add(subList);
    }

    subList.add(node.val);

    buildOrderBottomList(list, index + 1, node.left);
    buildOrderBottomList(list, index + 1, node.right);
  }

  /**
   * 为给定的升序数列构建二分查找树
   */
  public TreeNode sortedArrayToBST(int[] nums) {
    if (nums.length == 0) {
      return null;
    }
    return helper(nums, 0, nums.length - 1);
  }

  private TreeNode helper(int[] num, int low, int high) {
    if (low > high) { // Done
      return null;
    }
    int mid = (low + high) / 2;
    TreeNode node = new TreeNode(num[mid]);
    node.left = helper(num, low, mid - 1);
    node.right = helper(num, mid + 1, high);
    return node;
  }

  /**
   * 判断是否是平衡二叉树
   */
  public boolean isBalanced(TreeNode root) {
    return root == null || getHeight(root) >= 0;
  }

  private int getHeight(TreeNode node) {
    if (node == null) return 0;
    int left = getHeight(node.left);
    int right = getHeight(node.right);

    if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }
}