package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import leetcode.easy.ListNodeProblems.ListNode;

public class TreeNodeProblems {
  /**
   * https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
   *
   * 中序遍历
   */
  private int diff = Integer.MAX_VALUE;
  private TreeNode lastNode;

  /**
   * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
   */
  private int postIndex, inorderIndex;
  private int inIndex = 0, preIndex = 0;

  public static void main(String[] args) {
    TreeNodeProblems problems = new TreeNodeProblems();
    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(2);
    //root.left.left = new TreeNode(4);
    //root.left.right = new TreeNode(5);
    //root.left.right.left = new TreeNode(0);
    root.right = new TreeNode(-5);
    //root.right.left = new TreeNode(6);
    //problems.countNodes(root);
    //generateTreesII(4);
    System.out.println(problems.findFrequentTreeSum(root));
  }

  /**
   * https://leetcode.com/problems/univalued-binary-tree/
   */
  public boolean isUnivalTree(TreeNode root) {
    return root == null || (root.left == null || root.left.val == root.val) && (root.right == null
        || root.right.val == root.val) && isUnivalTree(root.left) && isUnivalTree(root.right);
  }

  /**
   * https://leetcode.com/problems/flip-equivalent-binary-trees/
   */
  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null || root1.val != root2.val) return false;

    return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
        || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
  }

  /**
   * https://leetcode.com/problems/all-possible-full-binary-trees/
   */
  public List<TreeNode> allPossibleFBT(int N) {
    List<TreeNode> list = new LinkedList<>();

    if (N == 1) {
      list.add(new TreeNode(0));
    } else {
      N--;
      for (int i = 1; i < N; i += 2) {
        List<TreeNode> lL = allPossibleFBT(i);
        List<TreeNode> lR = allPossibleFBT(N - i);

        for (TreeNode nL : lL) {
          for (TreeNode nR : lR) {
            TreeNode cur = new TreeNode(0);
            cur.left = nL;
            cur.right = nR;
            list.add(cur);
          }
        }
      }
    }

    return list;
  }

  /**
   * https://leetcode.com/problems/binary-tree-pruning/description/
   */
  public TreeNode pruneTree(TreeNode root) {
    if (root == null) return root;
    root.left = pruneTree(root.left);
    root.right = pruneTree(root.right);

    if (root.val == 0 && root.left == null && root.right == null) {
      return null;
    } else {
      return root;
    }
  }

  /**
   * https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
   */
  public TreeNode insertIntoBST(TreeNode root, int val) {

    if (root.val > val) {
      if (root.left != null) {
        insertIntoBST(root.left, val);
      } else {
        root.left = new TreeNode(val);
      }
    } else {
      if (root.right != null) {
        insertIntoBST(root.right, val);
      } else {
        root.right = new TreeNode(val);
      }
    }

    return root;
  }

  /**
   * https://leetcode.com/problems/range-sum-of-bst/description/
   */
  public int rangeSumBST(TreeNode root, int L, int R) {
    if (root == null) return 0;
    if (root.val < L) return rangeSumBST(root.right, L, R);
    if (root.val > R) return rangeSumBST(root.left, L, R);
    return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
  }

  /**
   * https://leetcode.com/problems/maximum-binary-tree/description/
   */
  public TreeNode constructMaximumBinaryTree(int[] nums) {
    return maxBTHelper(nums, 0, nums.length - 1);
  }

  private TreeNode maxBTHelper(int[] nums, int l, int r) {
    int a = l, b = r;
    while (l < r) {
      if (nums[l] > nums[r]) {
        r--;
      } else {
        l++;
      }
    }

    TreeNode node = new TreeNode(nums[l]);

    if (l > a) {
      node.left = maxBTHelper(nums, a, l - 1);
    }

    if (l < b) {
      node.right = maxBTHelper(nums, l + 1, b);
    }

    return node;
  }

  /**
   * https://leetcode.com/problems/house-robber-iii/description/
   */
  public int rob(TreeNode root) {
    int[] num = dfs(root);
    return Math.max(num[0], num[1]);
  }

  private int[] dfs(TreeNode x) {
    if (x == null) return new int[2];
    int[] left = dfs(x.left);
    int[] right = dfs(x.right);
    int[] res = new int[2];
    res[0] = left[1] + right[1] + x.val;
    res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    return res;
  }

  /**
   * https://leetcode.com/problems/add-one-row-to-tree/description/
   */
  public TreeNode addOneRow(TreeNode root, int v, int d) {
    TreeNode parent = new TreeNode(0);
    parent.left = root;
    addOneRowHelper(parent, v, d, 1);

    return parent.left;
  }

  private void addOneRowHelper(TreeNode parent, int v, int d, int index) {

    if (parent == null) return;

    if (index == d) {
      TreeNode left = parent.left;
      parent.left = new TreeNode(v);
      parent.left.left = left;

      TreeNode right = parent.right;
      parent.right = new TreeNode(v);
      parent.right.right = right;
    } else {
      addOneRowHelper(parent.left, v, d, index + 1);
      addOneRowHelper(parent.right, v, d, index + 1);
    }
  }

  /**
   * https://leetcode.com/problems/increasing-order-search-tree/description/
   */
  public TreeNode increasingBST(TreeNode root) {
    TreeNode ans = new TreeNode(0);
    if (root != null) increasingBSTHelper(root, ans);

    return ans.right;
  }

  private TreeNode increasingBSTHelper(TreeNode node, TreeNode parent) {
    if (node.left != null) parent = increasingBSTHelper(node.left, parent);
    node.left = null;
    parent.right = node;
    parent = node;
    if (node.right != null) parent = increasingBSTHelper(node.right, parent);
    return parent;
  }

  /**
   * https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
   */
  public List<Integer> largestValues(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    largestValuesHelper(list, root, 0);
    return list;
  }

  private void largestValuesHelper(List<Integer> list, TreeNode node, int index) {
    if (node == null) return;

    if (list.size() <= index) {
      list.add(node.val);
    } else {
      list.set(index, Math.max(node.val, list.get(index)));
    }

    largestValuesHelper(list, node.left, index + 1);
    largestValuesHelper(list, node.right, index + 1);
  }

  /**
   * https://leetcode.com/problems/find-bottom-left-tree-value/description/
   */
  public int findBottomLeftValue(TreeNode root) {
    List<Integer> list = new ArrayList<>();

    findBottomLeftValueHelper(list, root, 0);

    return list.get(list.size() - 1);
  }

  private void findBottomLeftValueHelper(List<Integer> list, TreeNode node, int index) {
    if (node == null) return;

    if (list.size() <= index) {
      list.add(node.val);
    }

    findBottomLeftValueHelper(list, node.left, index + 1);
    findBottomLeftValueHelper(list, node.right, index + 1);
  }

  /**
   * https://leetcode.com/problems/most-frequent-subtree-sum/description/
   */
  public int[] findFrequentTreeSum(TreeNode root) {
    Map<Integer, Integer> map = new HashMap<>();

    findFrequentTreeSumHelper(map, root);

    List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

    if (list.size() == 0) return new int[] {};

    list.sort((a, b) -> b.getValue() - a.getValue());
    int max = list.get(0).getValue();
    return list.stream()
        .filter(a -> a.getValue() == max)
        .map(Map.Entry::getKey)
        .mapToInt(value -> value)
        .toArray();
  }

  private int findFrequentTreeSumHelper(Map<Integer, Integer> map, TreeNode node) {

    if (node == null) return 0;

    int left = findFrequentTreeSumHelper(map, node.left);
    int right = findFrequentTreeSumHelper(map, node.right);

    int sum = left + right + node.val;
    map.put(sum, map.getOrDefault(sum, 0) + 1);

    return sum;
  }

  /**
   * https://leetcode.com/problems/leaf-similar-trees/description/
   */
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    leafSimilarHelper(list1, root1);
    leafSimilarHelper(list2, root2);

    if (list1.size() != list2.size()) return false;

    for (int i = 0; i < list1.size(); i++) {
      if (!Objects.equals(list1.get(i), list2.get(i))) return false;
    }

    return true;
  }

  private void leafSimilarHelper(List<Integer> list, TreeNode node) {
    if (node != null) {
      if (node.left == null && node.right == null) {
        list.add(node.val);
      } else {
        leafSimilarHelper(list, node.left);
        leafSimilarHelper(list, node.right);
      }
    }
  }

  /**
   * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
   */
  public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : right == null ? left : root;
  }

  /**
   * https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/
   */
  private Integer kthSmallest;
  private int kth;

  public int kthSmallest(TreeNode root, int k) {
    kth = k;
    kthSmallestHelper(root);
    return kthSmallest;
  }

  private void kthSmallestHelper(TreeNode node) {
    if (node.left != null) kthSmallestHelper(node.left);
    kth--;
    if (kth == 0) {
      if (kthSmallest == null) kthSmallest = node.val;
      return;
    }
    if (node.right != null) kthSmallestHelper(node.right);
  }

  /**
   * https://leetcode.com/problems/count-complete-tree-nodes/description/
   */
  int height(TreeNode root) {
    return root == null ? -1 : 1 + height(root.left);
  }

  public int countNodes(TreeNode root) {
    int h = height(root);
    return h < 0 ? 0 :
        height(root.right) == h - 1 ? (1 << h) + countNodes(root.right)
            : (1 << h - 1) + countNodes(root.left);
  }

  /**
   * https://leetcode.com/problems/binary-tree-right-side-view/description/
   */
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    rightSideViewHelper(root, ans, 0);
    return ans;
  }

  private void rightSideViewHelper(TreeNode node, List<Integer> ans, int level) {
    if (node == null) return;
    if (level >= ans.size()) {
      ans.add(node.val);
    }

    if (node.right != null) {
      rightSideViewHelper(node.right, ans, level + 1);
    }

    if (node.left != null) {
      rightSideViewHelper(node.left, ans, level + 1);
    }
  }

  /**
   * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
   */
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    preorderTraversalHelper(ans, root);
    return ans;
  }

  private void preorderTraversalHelper(List<Integer> ans, TreeNode node) {
    if (node == null) return;
    ans.add(node.val);
    preorderTraversalHelper(ans, node.left);
    preorderTraversalHelper(ans, node.right);
  }

  /**
   * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
   */
  private int sum = 0;

  public int sumNumbers(TreeNode root) {
    if (root != null) sumNumbersHelper(root, 0);
    return sum;
  }

  private void sumNumbersHelper(TreeNode node, int temp) {
    temp = temp * 10 + node.val;
    if (node.left == null && node.right == null) {
      sum += temp;
      return;
    }

    if (node.left != null) sumNumbersHelper(node.left, temp);
    if (node.right != null) sumNumbersHelper(node.right, temp);
  }

  /**
   * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
   */
  public void flatten(TreeNode root) {
    if (root == null) return;

    TreeNode left = root.left;
    TreeNode right = root.right;

    root.left = null;

    flatten(left);
    flatten(right);

    root.right = left;
    TreeNode cur = root;
    while (cur.right != null) cur = cur.right;
    cur.right = right;
  }

  /**
   * https://leetcode.com/problems/path-sum-ii/description/
   */
  public List<List<Integer>> pathSumII(TreeNode root, int sum) {
    List<List<Integer>> ans = new ArrayList<>();
    if (root != null) pathSumIIHelper(ans, new ArrayList<>(), root, sum);
    return ans;
  }

  private void pathSumIIHelper(List<List<Integer>> ans, List<Integer> temp, TreeNode node,
      int sum) {
    temp.add(node.val);
    sum -= node.val;
    if (node.left == null && node.right == null) {
      if (sum == 0) ans.add(new ArrayList<>(temp));
    } else {
      if (node.left != null) {
        pathSumIIHelper(ans, temp, node.left, sum);
      }
      if (node.right != null) {
        pathSumIIHelper(ans, temp, node.right, sum);
      }
    }

    temp.remove(temp.size() - 1);
  }

  /**
   * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/description/
   */
  public TreeNode sortedListToBST(ListNode head) {
    return sortedList2BSTHelper(head, null);
  }

  private TreeNode sortedList2BSTHelper(ListNode head, ListNode tail) {

    if (head == tail) return null;

    ListNode slow, fast;
    slow = fast = head;
    while (fast != tail && fast.next != tail) {
      slow = slow.next;
      fast = fast.next.next;
    }

    TreeNode node = new TreeNode(slow.val);
    node.left = sortedList2BSTHelper(head, slow);
    node.right = sortedList2BSTHelper(slow.next, tail);

    return node;
  }

  public TreeNode buildTreeII(int[] inorder, int[] postorder) {
    inorderIndex = inorder.length - 1;
    postIndex = postorder.length - 1;
    return buildTreeIIHelper(inorder, postorder, Integer.MAX_VALUE);
  }

  private TreeNode buildTreeIIHelper(int[] inorder, int[] postorder, int target) {
    if (inorderIndex < 0 || inorder[inorderIndex] == target) return null;
    TreeNode node = new TreeNode(postorder[postIndex]);
    postIndex--;
    node.right = buildTreeIIHelper(inorder, postorder, node.val);
    inorderIndex--;
    node.left = buildTreeIIHelper(inorder, postorder, target);

    return node;
  }

  /**
   * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
   */
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (inorder.length == 0) return null;
    if (inorder.length == 1) return new TreeNode(inorder[0]);

    TreeNode treeNode = new TreeNode(preorder[0]);

    int index = -1;

    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == treeNode.val) {
        index = i;
        break;
      }
    }

    if (index > 0) {
      treeNode.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1),
          Arrays.copyOfRange(inorder, 0, index));
    }

    if (index > -1 && index < inorder.length - 1) {
      treeNode.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length),
          Arrays.copyOfRange(inorder, index + 1, inorder.length));
    }

    return treeNode;
  }

  public TreeNode buildTreeBetter(int[] preorder, int[] inorder) {
    return helper(preorder, inorder, Integer.MAX_VALUE);
  }

  private TreeNode helper(int[] preorder, int[] inorder, int target) {
    if (inIndex >= inorder.length || inorder[inIndex] == target) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[preIndex]);
    preIndex++;
    root.left = helper(preorder, inorder, root.val);
    inIndex++;
    root.right = helper(preorder, inorder, target);
    return root;
  }

  /**
   * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
   */
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    zigzagLevelOrderHelper(ans, root, 0);

    return ans;
  }

  private void zigzagLevelOrderHelper(List<List<Integer>> ans, TreeNode node, int level) {
    if (node != null) {
      if (ans.size() <= level) ans.add(new ArrayList<>());
      if (level % 2 == 0) {
        ans.get(level).add(node.val);
      } else {
        ans.get(level).add(0, node.val);
      }
      if (node.left != null) zigzagLevelOrderHelper(ans, node.left, level + 1);
      if (node.right != null) zigzagLevelOrderHelper(ans, node.right, level + 1);
    }
  }

  /**
   * https://leetcode.com/problems/binary-tree-level-order-traversal/description/
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();

    levelOrderHelper(ans, root, 0);

    return ans;
  }

  private void levelOrderHelper(List<List<Integer>> ans, TreeNode node, int level) {
    if (node != null) {
      if (ans.size() <= level) ans.add(new ArrayList<>());
      ans.get(level).add(node.val);
      if (node.left != null) levelOrderHelper(ans, node.left, level + 1);
      if (node.right != null) levelOrderHelper(ans, node.right, level + 1);
    }
  }

  /**
   * https://leetcode.com/problems/validate-binary-search-tree/description/
   */
  public boolean isValidBST(TreeNode root) {
    return root == null || BSTChecker(root, new ArrayList<>());
  }

  private boolean BSTChecker(TreeNode node, List<Integer> list) {
    if (node.left != null && !BSTChecker(node.left, list)) return false;
    if (list.size() > 0 && node.val <= list.get(list.size() - 1)) return false;
    list.add(node.val);
    return node.right == null || BSTChecker(node.right, list);
  }

  /**
   * https://leetcode.com/problems/unique-binary-search-trees/description/
   */
  public int numTrees(int n) {
    int[] array = new int[n + 1];
    if (n == 0) return array[0];
    array[0] = 1;
    for (int len = 1; len <= n; len++) {
      int t = 0;
      for (int j = 0; j < len; j++) {
        t += array[j] * array[len - j - 1];
      }
      array[len] = t;
    }

    return array[n];
  }

  /**
   * https://leetcode.com/problems/unique-binary-search-trees-ii/description/
   */
  public List<TreeNode> generateTrees(int n) {
    List<TreeNode>[] trees = new List[n + 1];
    trees[0] = new ArrayList<>();
    if (n == 0) return trees[0];
    trees[0].add(null);

    for (int len = 1; len <= n; len++) {
      trees[len] = new ArrayList<>();
      for (int j = 0; j < len; j++) {
        for (TreeNode l : trees[j]) {
          for (TreeNode r : trees[len - j - 1]) {
            TreeNode node = new TreeNode(j + 1);
            node.left = l;
            node.right = cloneTree(r, j + 1);
            trees[len].add(node);
          }
        }
      }
    }

    return trees[n];
  }

  private TreeNode cloneTree(TreeNode node, int offset) {
    if (node == null) return null;
    TreeNode t = new TreeNode(node.val + offset);
    t.left = cloneTree(node.left, offset);
    t.right = cloneTree(node.right, offset);

    return t;
  }

  /**
   * 中序遍历
   *
   * @link https://leetcode.com/problems/binary-tree-inorder-traversal/description/
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root != null) inorderTraversalHelper(ans, root);
    return ans;
  }

  private void inorderTraversalHelper(List<Integer> ans, TreeNode node) {
    if (node.left != null) inorderTraversalHelper(ans, node.left);
    ans.add(node.val);
    if (node.right != null) inorderTraversalHelper(ans, node.right);
  }

  /**
   * https://leetcode.com/problems/longest-univalue-path/description/
   */
  public int longestUnivaluePath(TreeNode root) {
    int[] res = new int[] {0};
    if (root != null) longestPathCal(root, res);
    return res[0];
  }

  private int longestPathCal(TreeNode node, int[] res) {
    int l = node.left != null ? longestPathCal(node.left, res) : 0;
    int r = node.right != null ? longestPathCal(node.right, res) : 0;
    int resl = node.left != null && node.val == node.left.val ? l + 1 : 0;
    int resR = node.right != null && node.val == node.right.val ? r + 1 : 0;

    res[0] = Math.max(res[0], resl + resR);
    return Math.max(resl, resR);
  }

  /**
   * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/description/
   */
  public int findSecondMinimumValue(TreeNode root) {
    if (root == null || root.left == null || root.right == null) return -1;
    int left = root.left.val;
    int right = root.right.val;

    if (left == root.val) {
      left = findSecondMinimumValue(root.left);
    }

    if (right == root.val) {
      right = findSecondMinimumValue(root.right);
    }

    if (left != -1 && right != -1) {
      return Math.min(left, right);
    }

    if (left != -1) return left;
    return right;
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

  public class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
      stack = new Stack<>();
      push(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
      TreeNode node = stack.pop();
      push(node.right);
      return node.val;
    }

    void push(TreeNode node) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }
  }
}
