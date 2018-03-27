package leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListNodeProblems {

  public static void main(String[] args) {
    ListNode root = new ListNode(1);
    root.next = new ListNode(2);
    root.next.next = new ListNode(3);
    //root.next.next.next = new ListNode(4);
    ListNodeProblems problems = new ListNodeProblems();
    problems.swapPairs(root);
  }

  /**
   * medium
   */
  /**
   * https://leetcode.com/problems/swap-nodes-in-pairs/description/
   */
  public ListNode swapPairs(ListNode head) {
    ListNode ans = new ListNode(-1), p = ans, f = head, s;
    ans.next = f;
    while (f != null && f.next != null) {
      s = f.next;
      p.next = s;
      f.next = s.next;
      s.next = f;
      p = f;
      f = f.next;
    }
    return ans.next;
  }

  /**
   * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || n == 0) return head;
    List<ListNode> temp = new ArrayList<>();
    ListNode node = head;
    while (node != null) {
      temp.add(node);
      node = node.next;
    }

    int delIndex = temp.size() - n;

    if (delIndex == 0) {
      return head.next;
    } else if (delIndex == temp.size() - 1) {
      temp.get(delIndex - 1).next = null;
    } else {
      temp.get(delIndex - 1).next = temp.get(delIndex + 1);
    }
    return head;
  }

  /**
   * easy
   */

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = null;
    ListNode current = null;

    while (l1 != null && l2 != null) {
      ListNode temp;
      if (l1.val < l2.val) {
        temp = l1;
        l1 = l1.next;
      } else {
        temp = l2;
        l2 = l2.next;
      }

      if (current == null) {
        head = temp;
      } else {
        current.next = temp;
      }
      current = temp;
    }

    if (l1 != null) {
      if (current == null) {
        head = l1;
      } else {
        current.next = l1;
      }
    }

    if (l2 != null) {
      if (current == null) {
        head = l2;
      } else {
        current.next = l2;
      }
    }

    return head;
  }

  /**
   * 移除有序链表中重复的节点
   */
  public ListNode deleteDuplicates(ListNode head) {

    if (head == null) return head;

    ListNode pre = head;

    while (pre.next != null) {
      ListNode next = pre.next;
      if (next.val == pre.val) {
        pre.next = next.next;
      } else {
        pre = next;
      }
    }

    return head;
  }

  /**
   * 判断链表是否是循环的（首尾相连） 用两个指针，一个每次前进一步，一个每次前进2步，如果首尾相连则会两者相遇。
   */
  public boolean hasCycle(ListNode head) {
    if (head == null) return false;
    ListNode a = head;
    ListNode b = head;
    while (a.next != null && b.next != null && b.next.next != null) {
      a = a.next;
      b = b.next.next;
      if (a == b) return true;
    }

    return false;
  }

  /**
   * 找到两个链表中尾部相同的部分，返回这部分的起始节点，如果没有则返回null。
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenA = len(headA), lenB = len(headB);
    while (lenA > lenB) {
      headA = headA.next;
      lenA--;
    }
    while (lenA < lenB) {
      headB = headB.next;
      lenB--;
    }
    while (headA != headB) {
      headA = headA.next;
      headB = headB.next;
    }

    return headA;
  }

  private int len(ListNode node) {
    int len = 0;
    while (node != null) {
      len++;
      node = node.next;
    }

    return len;
  }

  /**
   * 除去链表中值为给定val的节点。
   */
  public ListNode removeElements(ListNode head, int val) {
    ListNode result = null, pre = null;
    while (head != null) {
      if (head.val != val) {
        result = pre = head;
        break;
      }
      head = head.next;
    }

    while (pre != null && pre.next != null) {
      if (pre.next.val == val) {
        pre.next = pre.next.next;
      } else {
        pre = pre.next;
      }
    }

    return result;
  }

  public ListNode reverseList(ListNode head) {
    Stack<ListNode> stack = new Stack<>();
    while (head != null) {
      stack.push(head);
      head = head.next;
    }
    ListNode result = new ListNode(0);
    head = result;
    while (!stack.empty()) {
      ListNode node = stack.pop();
      node.next = null;
      head.next = node;
      head = node;
    }

    return result.next;
  }

  /**
   * 判断一个链表是否回文
   */
  public boolean isPalindrome(ListNode head) {
    ListNode fast, slow;
    fast = slow = head;
    // fast每次前进两次，slow每次前进一次，这样fast到达尾部，slow会到达中部
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    // 奇数个元素的情况下, 前移slow
    if (fast != null) slow = slow.next;
    fast = head;

    slow = reverseList(slow);

    while (fast != null && slow != null) {
      if (fast.val != slow.val) return false;
      fast = fast.next;
      slow = slow.next;
    }
    return true;
  }

  /**
   * 删除链表中给定的节点（给定的节点不会是最后一个节点）。
   *
   * 因为单向链表中我们无法获得上一个节点，多以我们可以通过复制下个节点的数据到当前节点，达到删除的效果。
   */
  public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
  }

  public static class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
      val = x;
    }
  }
}
