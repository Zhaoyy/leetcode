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

  public static class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
      val = x;
    }
  }
}
