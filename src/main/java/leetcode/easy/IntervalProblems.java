package leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IntervalProblems {

  public static void main(String[] args) {
    IntervalProblems problems = new IntervalProblems();
    List<Interval> a = problems.arrayToList(new int[][] {{0, 2}, {5, 10}, {13, 23}, {24, 25}});
    List<Interval> b = problems.arrayToList(new int[][] {{1, 5}, {8, 12}, {15, 24}, {25, 26}});
    problems.intervalIntersection(a.toArray(new Interval[a.size()]),
        b.toArray(new Interval[b.size()]));
  }

  private List<Interval> arrayToList(int[][] array) {
    List<Interval> ans = new ArrayList<>(array.length);

    for (int[] a : array) {
      ans.add(new Interval(a[0], a[1]));
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/interval-list-intersections/
   */
  public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
    List<Interval> list = new LinkedList<>();

    int i = 0, j = 0;
    while (i < A.length && j < B.length) {

      if (A[i].end >= B[j].start && A[i].start <= B[j].end) {
        list.add(new Interval(Math.max(A[i].start, B[j].start), Math.min(A[i].end, B[j].end)));
      }

      if (A[i].end < B[j].end) {
        i++;
      } else {
        j++;
      }
    }

    return list.toArray(new Interval[list.size()]);
  }

  public static class Interval {
    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }
}
