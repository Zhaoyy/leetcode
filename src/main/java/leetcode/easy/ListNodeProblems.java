package leetcode.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ListNodeProblems {

  public static void main(String[] args) {
    ListNode root = new ListNode(4);
    root.next = new ListNode(2);
    root.next.next = new ListNode(1);
    root.next.next.next = new ListNode(3);
    //root.next.next.next.next = new ListNode(5);
    //root.next.next.next.next.next = root;
    //root.next.next.next.next = new ListNode(5);
    //root.next.next.next.next.next = new ListNode(2);
    //root.next.next.next = new ListNode(4);
    ListNodeProblems problems = new ListNodeProblems();
    //ListNode node = problems.reverseBetween(root, 1, 3);
    //System.out.println(problems.detectCycle(root).val);
    problems.sortList(root);
  }

  /**
   * https://leetcode.com/problems/middle-of-the-linked-list/description/
   */
  public ListNode middleNode(ListNode head) {
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  /**
   * https://leetcode.com/problems/reverse-nodes-in-k-group/description/
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) return head;
    int c = k;
    ListNode t = head;
    while (c > 1 && head != null) {
      head = head.next;
      c--;
    }

    if (c > 1 || head == null) return t;

    ListNode next = reverseKGroup(head.next, k);
    head.next = null;
    head = next;
    ListNode temp;
    while (t != null) {
      temp = t.next;
      t.next = head;
      head = t;
      t = temp;
    }
    return head;
  }

  public static ListNode mergeKListsHelper(ListNode l1, ListNode l2) {
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    if (l1.val < l2.val) {
      l1.next = mergeKListsHelper(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeKListsHelper(l1, l2.next);
      return l2;
    }
  }

  /**
   * https://leetcode.com/problems/merge-k-sorted-lists/description/
   */
  public ListNode mergeKLists(ListNode[] lists) {
    int n = lists.length;
    if (n == 0) return null;
    for (int sz = 1; sz < n; sz <<= 1)
      for (int low = 0; low < n - sz; low += sz << 1)
        lists[low] = mergeKListsHelper(lists[low], lists[low + sz]);
    return lists[0];
  }

  /**
   * https://leetcode.com/problems/insertion-sort-list/description/
   */
  public ListNode insertionSortList(ListNode head) {
    ListNode ans = new ListNode(-1);
    ListNode hp = ans;
    ans.next = head;

    while (head != null) {
      ListNode t = ans.next;
      ListNode p = ans;
      while (t != null && t != head && t.val < head.val) {
        p = t;
        t = t.next;
      }
      ListNode next = head.next;

      if (t != null && t != head) {
        p.next = head;
        head.next = t;
        hp.next = next;
      } else {
        hp = head;
      }

      head = next;
    }

    return ans.next;
  }

  /**
   * https://leetcode.com/problems/sort-list/description/
   */
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p, slow, fast;
    p = slow = fast = head;
    while (fast != null && fast.next != null) {
      p = slow;
      slow = slow.next;
      fast = fast.next.next;
    }

    p.next = null;

    return sortListMerge(sortList(head), sortList(slow));
  }

  private ListNode sortListMerge(ListNode l, ListNode r) {
    if (l == null) return r;
    if (r == null) return l;
    if (l.val < r.val) {
      l.next = sortListMerge(l.next, r);
      return l;
    } else {
      r.next = sortListMerge(l, r.next);
      return r;
    }
  }

  /**
   * https://leetcode.com/problems/linked-list-cycle-ii/description/
   */
  public ListNode detectCycle(ListNode head) {
    ListNode s, q;
    s = q = head;
    while (s != null && q != null && q.next != null) {
      s = s.next;
      q = q.next.next;
      if (s == q) {
        s = head;
        while (s != q) {
          s = s.next;
          q = q.next;
        }

        return s;
      }
    }

    return null;
  }

  /**
   * https://leetcode.com/problems/reorder-list/description/
   */
  public void reorderList(ListNode head) {

    if (head == null || head.next == null || head.next.next == null) return;

    ListNode slow, fast;
    slow = fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode mid = slow;
    ListNode next = slow.next;
    mid.next = null;
    while (next != null) {
      ListNode node = next.next;
      next.next = mid;
      mid = next;
      next = node;
    }

    slow = head;
    fast = mid;

    while (fast.next != null) {
      ListNode t = slow.next;
      ListNode node = fast.next;
      slow.next = fast;
      fast.next = t;
      slow = t;
      fast = node;
    }
  }

  /**
   * https://leetcode.com/problems/reverse-linked-list-ii/description/
   */
  public ListNode reverseBetween(ListNode head, int m, int n) {

    if (head == null) return null;

    ListNode r = new ListNode(-1), pre = r;

    r.next = head;
    for (int i = 1; i < m; i++) {
      pre = pre.next;
    }

    ListNode first = pre.next, second = first.next;

    for (int i = 0; i < n - m; i++) {
      first.next = second.next;
      second.next = pre.next;
      pre.next = second;
      second = first.next;
    }

    return r.next;
  }

  /**
   * https://leetcode.com/problems/partition-list/description/
   */
  public ListNode partition(ListNode head, int x) {
    ListNode ans = new ListNode(-1), p = ans;
    ListNode big = new ListNode(-1), q = big;

    while (head != null) {
      if (head.val < x) {
        p.next = head;
        p = p.next;
      } else {
        q.next = head;
        q = q.next;
      }
      head = head.next;
    }
    p.next = null;
    q.next = null;

    if (big.next != null) {
      p.next = big.next;
    }

    return ans.next;
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
   */
  public ListNode deleteDuplicatesII(ListNode head) {
    ListNode ans = new ListNode(-1);

    ListNode dup = null, p = ans, c = head;

    while (c != null) {
      if (c.next != null && c.val == c.next.val || dup != null && c.val == dup.val) {
        dup = c;
        p.next = null;
      } else {
        p.next = c;
        p = c;
      }
      c = c.next;
    }

    return ans.next;
  }

  /**
   * https://leetcode.com/problems/rotate-list/description/
   */
  public ListNode rotateRight(ListNode head, int k) {

    if (head == null) return head;

    int count = 0;
    ListNode t = head;
    while (t != null) {
      count++;
      t = t.next;
    }

    k = k % count;
    if (k == 0) return head;

    int m = count - k;
    t = head;
    for (int i = 1; i < m; i++) {
      t = t.next;
    }

    ListNode ans = new ListNode(0);
    ans.next = t.next;
    t.next = null;
    t = ans.next;
    for (int i = 1; i < k; i++) {
      t = t.next;
    }
    t.next = head;

    return ans.next;
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
