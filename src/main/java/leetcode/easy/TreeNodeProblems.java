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
    return root == null || getMaxHeight(root) >= 0;
  }

  private int getMaxHeight(TreeNode node) {
    if (node == null) return 0;
    int left = getMaxHeight(node.left);
    int right = getMaxHeight(node.right);

    if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
      return -1;
    }

    return Math.max(left, right) + 1;
  }

  /**
   * 返回树的最小深度。
   */
  public int minDepth(TreeNode root) {
    return getMinHeight(root);
  }

  private int getMinHeight(TreeNode node) {
    if (node == null) {
      return 0;
    } else if (node.left != null && node.right != null) {
      int left = getMinHeight(node.left);
      int right = getMinHeight(node.right);
      return Math.min(left, right) + 1;
    } else if (node.left == null && node.right != null) {
      return getMinHeight(node.right) + 1;
    } else if (node.left != null) {
      return getMinHeight(node.left) + 1;
    } else {
      return 1;
    }
  }

  public boolean hasPathSum(TreeNode root, int sum) {
    return root != null && foundPathSum(root, 0, sum);
  }

  private boolean foundPathSum(TreeNode root, int pre, int sum) {
    int val = root == null ? 0 : root.val;

    if (root == null) {
      return pre == sum;
    } else if (root.left == null) {
      return foundPathSum(root.right, val + pre, sum);
    } else if (root.right == null) {
      return foundPathSum(root.left, val + pre, sum);
    } else {
      return foundPathSum(root.left, val + pre, sum) || foundPathSum(root.right, val + pre, sum);
    }
  }

  public TreeNode invertTree(TreeNode root) {
    invertChild(root);
    return root;
  }

  private void invertChild(TreeNode node) {
    if (node != null) {
      TreeNode t = node.left;
      node.left = node.right;
      node.right = t;
      invertChild(node.left);
      invertChild(node.right);
    }
  }

  /**
   * 二分查找树中找到给定的两个节点的最小共同祖先
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (p.val > q.val) {
      return foundLowestCommonAncestor(root, q, p);
    } else {
      return foundLowestCommonAncestor(root, p, q);
    }
  }

  private TreeNode foundLowestCommonAncestor(TreeNode node, TreeNode min, TreeNode max) {
    if (node.val == min.val
        || node.val == max.val || (node.val > min.val && node.val < max.val)) {
      return node;
    } else if (node.val < min.val) {
      return foundLowestCommonAncestor(node.right, min, max);
    } else {
      return foundLowestCommonAncestor(node.left, min, max);
    }
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
