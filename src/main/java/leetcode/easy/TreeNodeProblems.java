package leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNodeProblems {
  /**
   * https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
   *
   * 中序遍历
   */
  private int diff = Integer.MAX_VALUE;
  private TreeNode lastNode;

  public static void main(String[] args) {
    TreeNodeProblems problems = new TreeNodeProblems();
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(0);
    //root.left.left = new TreeNode(49);
    //root.left.right = new TreeNode(89);
    //root.left.right.left = new TreeNode(0);
    root.right = new TreeNode(2);
    //root.right.left = new TreeNode(-2);

    System.out.println(problems.trimBST(root, 1, 2));
  }

  /**
   * https://leetcode.com/problems/trim-a-binary-search-tree/description/
   */
  public TreeNode trimBST(TreeNode root, int L, int R) {
    if (root != null) {
      if (root.val < L) return trimBST(root.right, L, R);
      if (root.val > R) return trimBST(root.left, L, R);
      root.left = trimBST(root.left, L, R);
      root.right = trimBST(root.right, L, R);
    }

    return root;
  }

  /**
   * https://leetcode.com/problems/merge-two-binary-trees/description/
   */

  public TreeNode mergeTreesSimple(TreeNode t1, TreeNode t2) {
    if (t1 == null) return t2;
    if (t2 == null) return t1;

    TreeNode node = new TreeNode(t1.val + t2.val);
    node.left = mergeTreesSimple(t1.left, t2.left);
    node.right = mergeTreesSimple(t1.right, t2.right);
    return node;
  }

  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    } else if (t2 == null) {
      return t1;
    } else {
      TreeNode root = new TreeNode(0);
      mergeTreesFun(root, t1, t2);
      return root;
    }
  }

  private void mergeTreesFun(TreeNode r, TreeNode t1, TreeNode t2) {
    r.val = t1 == null ? t2.val : t2 == null ? t1.val : t1.val + t2.val;
    if (t1 == null) {
      r.left = t2.left;
      r.right = t2.right;
      return;
    }

    if (t2 == null) {
      r.left = t1.left;
      r.right = t1.right;
      return;
    }

    if (t1.left != null || t2.left != null) {
      TreeNode left = new TreeNode(0);
      r.left = left;
      mergeTreesFun(left, t1.left, t2.left);
    }

    if (t1.right != null || t2.right != null) {
      TreeNode right = new TreeNode(0);
      r.right = right;
      mergeTreesFun(right, t1.right, t2.right);
    }
  }

  /**
   * https://leetcode.com/problems/construct-string-from-binary-tree/description/
   */
  public String tree2str(TreeNode t) {
    StringBuilder sb = new StringBuilder();
    tree2Str(t, sb);
    return sb.toString();
  }

  private void tree2Str(TreeNode node, StringBuilder sb) {
    if (node != null) {
      sb.append(node.val);
      if (node.left != null || node.right != null) {
        sb.append("(");
        tree2Str(node.left, sb);
        sb.append(")");
        if (node.right != null) {
          sb.append("(");
          tree2Str(node.right, sb);
          sb.append(")");
        }
      }
    }
  }

  public int minDiffInBST(TreeNode root) {
    minDiffBST(root);
    return diff;
  }

  private void minDiffBST(TreeNode node) {
    if (node == null) return;
    if (node.left != null) {
      minDiffBST(node.left);
    }

    if (lastNode != null) {
      diff = Math.min(node.val - lastNode.val, diff);
    }
    lastNode = node;
    if (node.right != null) {
      minDiffBST(node.right);
    }
  }

  /**
   * https://leetcode.com/problems/subtree-of-another-tree/description/
   */
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null || t == null) return false;
    StringBuilder sb = new StringBuilder();
    preOrderTree(s, sb);
    String ss = sb.toString();
    sb.setLength(0);
    preOrderTree(t, sb);
    String st = sb.toString();

    return ss.contains(st);
  }

  private void preOrderTree(TreeNode node, StringBuilder sb) {
    if (node.left == null) {
      sb.append('n');
    } else {
      preOrderTree(node.left, sb);
    }

    sb.append(node.val);

    if (node.right == null) {
      sb.append('n');
    } else {
      preOrderTree(node.right, sb);
    }
  }

  /**
   * https://leetcode.com/problems/binary-tree-tilt/description/
   */

  private int tilt = 0;

  public int findTilt(TreeNode root) {
    tiltSum(root);

    return tilt;
  }

  private int tiltSum(TreeNode node) {
    if (node == null) return 0;
    int l = tiltSum(node.left);
    int r = tiltSum(node.right);
    tilt += Math.abs(l - r);
    return node.val + l + r;
  }

  /**
   * 获取二叉树任意两个叶子节点的最大距离
   */
  int max = 0;

  public int diameterOfBinaryTree(TreeNode root) {
    diameterOfBT(root);
    return max;
  }

  private int diameterOfBT(TreeNode root) {
    if (root == null) return 0;

    int left = diameterOfBT(root.left);
    int right = diameterOfBT(root.right);

    max = Math.max(max, left + right);

    return Math.max(left, right) + 1;
  }

  /**
   * https://leetcode.com/problems/convert-bst-to-greater-tree/description/
   */
  public TreeNode convertBST(TreeNode root) {
    if (root != null) toGreaterTree(root, 0);
    return root;
  }

  public int toGreaterTree(TreeNode node, int preSum) {
    if (node.left == null && node.right == null) {
      node.val += preSum;
      return node.val;
    }

    if (node.right != null) {
      preSum = toGreaterTree(node.right, preSum);
    }

    node.val += preSum;
    preSum = node.val;

    if (node.left != null) preSum = toGreaterTree(node.left, preSum);
    return preSum;
  }

  private int preNodeValue = -1;
  private int minDiff = Integer.MAX_VALUE;

  /**
   * 找到二分查找树任意两个节点的最小差。 中序遍历。
   */
  public int getMinimumDifference(TreeNode root) {
    foundDifference(root);
    return minDiff;
  }

  private void foundDifference(TreeNode node) {

    if (node.left != null) foundDifference(node.left);
    if (preNodeValue >= 0) {
      minDiff = Math.min(minDiff, Math.abs(node.val - preNodeValue));
    }
    preNodeValue = node.val;

    if (node.right != null) foundDifference(node.right);
  }

  /**
   * 返回二叉树中所有路径中值的和等于给定数字的值
   */
  public int pathSum(TreeNode root, int sum) {
    if (root == null) return 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    return findSum(root, 0, sum, map);
  }

  private int findSum(TreeNode node, int sum, int target, Map<Integer, Integer> map) {
    if (node == null) return 0;

    sum += node.val;
    int numPath2Current = map.getOrDefault(sum - target, 0);
    map.put(sum, map.getOrDefault(sum, 0) + 1);

    int res =
        numPath2Current + findSum(node.left, sum, target, map) + findSum(node.right, sum, target,
            map);
    map.put(sum, map.get(sum) - 1);
    return res;
  }

  /**
   * 统计二叉树所有左叶子节点的和
   */
  public int sumOfLeftLeaves(TreeNode root) {
    return foundLeftLeave(root);
  }

  private int foundLeftLeave(TreeNode node) {
    if (node == null) return 0;
    if (node.left == null && node.right == null) {
      return node.val;
    } else {
      int l = foundLeftLeave(node.left);
      if (node.right != null && node.right.left == null && node.right.right == null) {
        return l;
      } else {
        return l + foundLeftLeave(node.right);
      }
    }
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

  /**
   * 返回二叉树所有从根节点到叶子节点的路径集合
   */
  public List<String> binaryTreePaths(TreeNode root) {
    if (root == null) return null;
    List<String> result = new ArrayList<>();
    treePah(root, new StringBuilder(root.val + ""), result);
    return result;
  }

  private void treePah(TreeNode node, StringBuilder sb, List<String> result) {
    if (node.left == null && node.right == null) {
      if (sb.length() > 0) result.add(sb.toString());
    } else {

      if (node.left != null) {
        StringBuilder _sb = new StringBuilder(sb.toString());
        _sb.append("->").append(node.left.val);

        treePah(node.left, _sb, result);
      }

      if (node.right != null) {
        sb.append("->").append(node.right.val);
        treePah(node.right, sb, result);
      }
    }
  }

  public static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }
}
