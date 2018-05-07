package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class TreeLinkNodeProblems {

  /**
   * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
   */
  public void connectBetter(TreeLinkNode root) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      root.left.next = root.right;
    }

    if (root.right != null) {
      if (root.next != null) {
        root.right.next = root.next.left;
      }
    }

    connectBetter(root.left);
    connectBetter(root.right);
  }

  public void connect(TreeLinkNode root) {
    List<List<TreeLinkNode>> list = new ArrayList<>();
    levelList(list, root, 0);

    TreeLinkNode last;
    for (List<TreeLinkNode> l : list) {
      last = null;
      for (TreeLinkNode node : l) {
        if (last != null) {
          last.next = node;
        }
        last = node;
      }
    }
  }

  private void levelList(List<List<TreeLinkNode>> list, TreeLinkNode node, int level) {
    if (node == null) return;
    if (level >= list.size()) list.add(new ArrayList<>());

    list.get(level).add(node);
    levelList(list, node.left, level + 1);
    levelList(list, node.right, level + 1);
  }

  public class TreeLinkNode {
    TreeLinkNode left;
    TreeLinkNode right;
    TreeLinkNode next;
  }
}
