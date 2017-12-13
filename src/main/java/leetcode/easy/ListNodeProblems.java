package leetcode.easy;

public class ListNodeProblems {
  public int val;
  public ListNodeProblems next;

  public ListNodeProblems(int val) {
    this.val = val;
  }

  public ListNodeProblems mergeTwoLists(ListNodeProblems l1, ListNodeProblems l2) {
    ListNodeProblems head = null;
    ListNodeProblems current = null;

    while (l1 != null && l2 != null) {
      ListNodeProblems temp;
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

  public static class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
      val = x;
    }
  }
}
