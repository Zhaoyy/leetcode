package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class ArrayProblems {

  private int[][] generateArray(int m, int n) {
    int[][] ans = new int[m][n];
    int t = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        t++;
        ans[i][j] = t;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    ArrayProblems problems = new ArrayProblems();
    //System.out.println(problems.findPairs(
    //    new int[] {2, 9, 0, 8, 9, 6, 5, 9, 8, 1, 9, 6, 9, 2, 8, 8, 7, 5, 7, 8, 8, 3, 7, 4, 1, 1, 6,
    //        2, 9, 9, 3, 9, 2, 4, 6, 5, 6, 5, 1, 5, 9, 9, 8, 1, 4, 3, 2, 8, 5, 3, 5, 4, 5, 7, 0, 0,
    //        7, 6, 4, 7, 2, 4, 9, 3, 6, 6, 5, 0, 0, 0, 8, 9, 9, 6, 5, 4, 6, 2, 1, 3, 2, 5, 0, 1, 4,
    //        2, 6, 9, 5, 4, 9, 6, 0, 8, 3, 8, 0, 0, 2, 1}, 1))
    //String[] list1 = new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"};
    //String[] list2 =
    //    new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
    int[][] t = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
    //char[][] chars = {{'b', 'b', 'b', 'b'}, {'b', 'a', 'b', 'b'}, {'b', 'd', 'c', 'b'}};
    //System.out.println(problems.ladderLength("hit", "cog",
    //    Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    //problems.setZeroes(t);
    System.out.println(
        problems.findAndReplacePattern(new String[] {"abc", "deq", "mee", "aqq", "dkd", "ccc"},
            "abb"));
    //System.out.println(
    //    problems.leastBricks(
    //        Arrays.asList(Arrays.asList(1, 2, 2, 1), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 2),
    //            Arrays.asList(2, 4), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 1, 1))));
  }

  /**
   * https://leetcode.com/problems/find-all-duplicates-in-an-array/
   */
  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> list = new LinkedList<>();

    if (nums != null && nums.length > 0) {
      Arrays.sort(nums);
      int last = nums[0];
      for (int i = 1; i < nums.length; i++) {
        if (last == nums[i]) {
          list.add(last);
        }
        last = nums[i];
      }
    }

    return list;
  }

  public List<Integer> findDumplicatesBetter(int[] nums) {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < nums.length; ++i) {
      int index = Math.abs(nums[i]) - 1;
      if (nums[index] < 0) {
        res.add(Math.abs(index + 1));
      }
      nums[index] = -nums[index];
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/stone-game/
   */
  public boolean stoneGame(int[] piles) {
    return true;
  }

  /**
   * https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
   */
  public int repeatedNTimes(int[] A) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int n : A) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }

    int n = A.length / 2;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() == n) return entry.getKey();
    }

    return A[0];
  }

  /**
   * https://leetcode.com/problems/largest-triangle-area/
   */
  public double largestTriangleArea(int[][] points) {
    double max = 0.0;
    for (int i = 0; i < points.length - 2; i++)
      for (int j = i + 1; j < points.length - 1; j++)
        for (int k = j + 1; k < points.length; k++)
          max = Math.max(max, areaCal(points[i], points[j], points[k]));
    return max;
  }

  public double areaCal(int[] pt1, int[] pt2, int[] pt3) {
    return Math.abs(
        pt1[0] * (pt2[1] - pt3[1]) + pt2[0] * (pt3[1] - pt1[1]) + pt3[0] * (pt1[1] - pt2[1])) / 2.0;
  }

  /**
   * https://leetcode.com/problems/reveal-cards-in-increasing-order/
   */
  public int[] deckRevealedIncreasing(int[] deck) {
    List<Integer> list = new LinkedList<>();
    Arrays.sort(deck);

    int index = 0;

    for (int i = deck.length - 1; i >= 0; i--) {
      int val = deck[i];
      if (index > 1) {
        int last = list.remove(list.size() - 1);
        list.add(0, last);
      }

      list.add(0, val);

      index++;
    }

    int[] ans = new int[deck.length];

    for (int i = 0; i < list.size(); i++) {
      ans[i] = list.get(i);
    }

    return ans;
  }

  public int[] deckRevealedIncreasingBetter(int[] deck) {
    if (deck == null || deck.length <= 1) {
      return deck;
    }

    Arrays.sort(deck);
    if (deck.length == 2) {
      return deck;
    }

    deckRevealedIncreasing(deck, 0, deck.length - 1);

    return deck;
  }

  private void deckRevealedIncreasing(int[] deck, int low, int high) {
    if (high - low <= 1) {
      return;
    }

    int mid = low + (high - low) / 2;
    deckRevealedIncreasing(deck, mid + 1, high);

    if (mid + 1 > high) {
      return;
    }

    //奇数个数，需要移一下位
    if (((high - low) & 1) == 0 && mid + 1 < high) {
      int temp = deck[high];
      for (int j = high - 1; j >= mid + 1; j--) {
        deck[j + 1] = deck[j];
      }
      deck[mid + 1] = temp;
    }

    int[] aux = new int[high - low + 1];
    int p = low;
    int q = mid + 1;
    for (int i = 0; i < aux.length; i = i + 2) {
      aux[i] = deck[p++];
      if (q <= high) {
        aux[i + 1] = deck[q++];
      }
    }

    p = low;
    for (int i = 0; i < aux.length; i++) {
      deck[p++] = aux[i];
    }
  }

  /**
   * https://leetcode.com/problems/verifying-an-alien-dictionary/
   */
  public boolean isAlienSorted(String[] words, String order) {
    int[] map = new int[26];

    for (int i = 0; i < order.length(); i++)
      map[order.charAt(i) - 'a'] = i;

    for (int i = 1; i < words.length; i++)
      if (compare(words[i - 1], words[i], map) > 0) {
        return false;
      }

    return true;
  }

  int compare(String s1, String s2,
      int[] map) {              // (-) if s1 < s2, 0 if equal, (+) if s1 > s2
    int n = s1.length(), m = s2.length();

    for (int i = 0, j = 0; i < n && j < m; i++, j++) {
      int pos1 = map[s1.charAt(i) - 'a'];
      int pos2 = map[s2.charAt(j) - 'a'];

      if (pos1 != pos2) {
        return pos1 - pos2;
      }
    }

    return n - m;
  }

  /**
   * https://leetcode.com/problems/battleships-in-a-board/
   */
  public int countBattleships(char[][] board) {
    if (board.length == 0) return 0;
    int n = board[0].length;
    int count = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == '.') continue;
        if (i > 0 && board[i - 1][j] == 'X') continue;
        if (j > 0 && board[i][j - 1] == 'X') continue;

        count++;
      }
    }

    return count;
  }

  /**
   * https://leetcode.com/problems/score-after-flipping-matrix/
   */
  public int matrixScore(int[][] A) {

    // toggle row
    for (int[] a : A) {
      if (a[0] == 0) {
        for (int i = 0; i < a.length; i++) {
          a[i] = a[i] == 0 ? 1 : 0;
        }
      }
    }

    // toggle column
    for (int i = 0; i < A[0].length; i++) {
      int count = 0;
      for (int[] a : A) {
        if (a[i] == 1) count++;
      }

      if (count * 2 < A.length) {
        for (int[] a : A) {
          a[i] = a[i] == 0 ? 1 : 0;
        }
      }
    }

    int ans = 0;
    for (int[] a : A) {
      int temp = 0;
      for (int i = 0; i < a.length; i++) {
        temp = temp * 2 + a[i];
      }
      ans += temp;
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/all-paths-from-source-to-target/
   */
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> ans = new LinkedList<>();

    List<Integer> temp = new LinkedList<>();
    temp.add(0);
    allPathsHelper(graph, temp, ans);

    return ans;
  }

  private void allPathsHelper(int[][] graph, List<Integer> temp,
      List<List<Integer>> ans) {
    int index = temp.get(temp.size() - 1);
    if (graph[index].length == 0) {
      ans.add(new ArrayList<>(temp));
      return;
    }

    for (int n : graph[index]) {
      temp.add(n);
      allPathsHelper(graph, temp, ans);
      temp.remove(temp.size() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/find-and-replace-pattern/description/
   */
  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> ans = new LinkedList<>();

    for (String word : words) {
      int[] p = new int[26], s = new int[26];
      if (word.length() == pattern.length()) {
        boolean isSame = true;
        for (int i = 0; i < pattern.length(); i++) {
          if (s[word.charAt(i) - 'a'] != p[pattern.charAt(i) - 'a']) {
            isSame = false;
            break;
          } else {
            s[word.charAt(i) - 'a'] = p[pattern.charAt(i) - 'a'] = i + 1;
          }
        }

        if (isSame) ans.add(word);
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/delete-columns-to-make-sorted/description/
   */
  public int minDeletionSize(String[] A) {

    if (A == null || A.length == 0) return 0;

    int ans = 0, len = A[0].length();

    for (int i = 0; i < len; i++) {
      char last = A[0].charAt(i);

      for (int j = 1; j < A.length; j++) {
        char t = A[j].charAt(i);
        if (t < last) {
          ans++;
          break;
        }
        last = t;
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/di-string-match/description/
   */
  public int[] diStringMatch(String S) {
    int l = 0, h = S.length();
    int[] ans = new int[h + 1];

    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == 'I') {
        ans[i] = l;
        l++;
      } else {
        ans[i] = h;
        h--;
      }
    }

    ans[S.length()] = l;

    return ans;
  }

  /**
   * https://leetcode.com/problems/valid-mountain-array/description/
   */
  public boolean validMountainArray(int[] A) {
    if (A == null || A.length < 3) return false;

    if (A[0] > A[1] || A[A.length - 1] > A[A.length - 2]) return false;

    boolean desc = false;
    int last = A[0];
    for (int i = 1; i < A.length; i++) {
      if (last == A[i]) return false;
      if (desc) {
        if (A[i] > last) return false;
      } else {
        if (A[i] < last) desc = true;
      }
      last = A[i];
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/max-increase-to-keep-city-skyline/description/
   */
  public int maxIncreaseKeepingSkyline(int[][] grid) {

    if (grid.length < 2 || grid[0].length < 2) return 0;

    int[] xM = new int[grid.length];
    int size = grid[0].length;
    int[] yM = new int[size];

    for (int i = 0; i < xM.length; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[i][j] > xM[i]) {
          xM[i] = grid[i][j];
        }

        if (grid[i][j] > yM[j]) {
          yM[j] = grid[i][j];
        }
      }
    }

    int ans = 0;

    for (int i = 0; i < xM.length; i++) {
      for (int j = 0; j < size; j++) {
        int min = Math.min(xM[i], yM[j]);
        ans += min - grid[i][j];
      }
    }
    return ans;
  }

  /**
   * https://leetcode.com/problems/walking-robot-simulation/description/
   */
  public int robotSim(int[] commands, int[][] obstacles) {
    Set<String> set = new HashSet<>();
    for (int[] o : obstacles) set.add(o[0] + "," + o[1]);
    int[] d = new int[] {0, 1, 0, -1, 0};
    int x = 0, y = 0, k = 0, res = 0;
    for (int c : commands) {
      if (c == -1) {
        k++;
      } else if (c == -2) {
        k += 3;
      } else {
        k %= 4;
        for (int i = 0; i < c; i++) {
          int X = x + d[k], Y = y + d[k + 1];
          if (set.contains(X + "," + Y)) break;
          x = X;
          y = Y;
        }
      }
      res = Math.max(res, x * x + y * y);
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/sort-array-by-parity-ii/description/
   */
  public int[] sortArrayByParityII(int[] A) {
    int l = 0, r = A.length - 1;

    while (l < r) {

      if (l % 2 == 0 && A[l] % 2 == 0 || l % 2 > 0 && A[l] % 2 > 0) {
        l++;
        continue;
      }

      if (r % 2 == 0 && A[r] % 2 == 0 || r % 2 > 0 && A[r] % 2 > 0) {
        r--;
        continue;
      }
      int i = r;
      for (; i > l; i--) {
        if (A[l] % 2 == 0) {
          if (A[i] % 2 > 0) break;
        } else {
          if (A[i] % 2 == 0) break;
        }
      }

      int temp = A[l];
      A[l] = A[i];
      A[i] = temp;
      l++;
    }
    return A;
  }

  /**
   * https://leetcode.com/problems/valid-triangle-number/description/
   */
  public int triangleNumber(int[] nums) {
    Arrays.sort(nums);
    int count = 0, n = nums.length;
    for (int i = n - 1; i >= 2; i--) {
      int l = 0, r = i - 1;
      while (l < r) {
        if (nums[l] + nums[r] > nums[i]) {
          count += r - l;
          r--;
        } else {
          l++;
        }
      }
    }
    return count;
  }

  // a<=b<=c
  private boolean isTriangle(int a, int b, int c) {
    return a + b > c && c - a < b;
  }

  /**
   * https://leetcode.com/problems/find-duplicate-file-in-system/description/
   */
  public List<List<String>> findDuplicate(String[] paths) {
    Map<String, List<String>> map = new HashMap<>();

    for (String path : paths) {

      String[] ss = path.split(" ");
      String p = ss[0];

      for (int i = 1; i < ss.length; i++) {
        int index = ss[i].lastIndexOf("(");

        String content = ss[i].substring(index + 1, ss[i].length() - 1);
        if (!map.containsKey(content)) map.put(content, new ArrayList<>());
        map.get(content).add(p + "/" + ss[i].substring(0, index));
      }
    }

    List<List<String>> ans = new ArrayList<>();

    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
      if (entry.getValue().size() > 1) ans.add(entry.getValue());
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/description/
   */
  public boolean hasGroupsSizeX(int[] deck) {
    if (deck.length < 2) return false;

    Map<Integer, Integer> map = new HashMap<>();
    for (int n : deck) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }

    int min = deck.length;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      min = Math.min(min, entry.getValue());
    }
    if (min < 2) {
      return false;
    }
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (gcd(entry.getValue(), min) == 1) {
        return false;
      }
    }
    return true;
  }

  public int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  /**
   * https://leetcode.com/problems/valid-square/description/
   */
  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    Set set = new HashSet<Integer>();
    set.add(helper(p1, p2));
    set.add(helper(p1, p3));
    set.add(helper(p1, p4));
    set.add(helper(p2, p3));
    set.add(helper(p2, p4));
    set.add(helper(p3, p4));

    return !set.contains(0) && set.size() == 2;
  }

  private int helper(int[] a, int[] b) {
    return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
  }

  /**
   * https://leetcode.com/problems/smallest-range-i/description/
   */
  public int smallestRangeI(int[] A, int K) {

    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    for (int n : A) {
      if (n < min) min = n;
      if (n > max) max = n;
    }

    int ans = max - min - K * 2;

    return ans < 0 ? 0 : ans;
  }

  /**
   * https://leetcode.com/problems/brick-wall/description/
   */
  public int leastBricks(List<List<Integer>> wall) {
    Map<Integer, Integer> map = new HashMap<>();

    for (List<Integer> list : wall) {
      int sum = 0;
      for (int n : list) {
        sum += n;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
      }

      map.remove(sum);
    }

    if (map.isEmpty()) return wall.size();

    Integer max =
        map.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).findFirst().map(
            Map.Entry::getValue).get();
    return wall.size() - max;
  }

  public int leastBricks7(List<List<Integer>> wall) {
    int n = wall.size();
    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;
    for (List<Integer> row : wall) {
      int st = -1;
      int m = row.size();
      for (int j = 0; j < m - 1; j++) {
        st += row.get(j);
        int val = map.getOrDefault(st, 0);
        map.put(st, val + 1);
        if (val + 1 > max) max = val + 1;
      }
    }
    return n - max;
  }

  /**
   * https://leetcode.com/problems/sort-array-by-parity/description/
   */
  public int[] sortArrayByParity(int[] A) {
    int l = 0, r = A.length - 1;
    while (l < r) {
      if (A[l] % 2 == 0) {
        l++;
        continue;
      }

      if (A[r] % 2 > 0) {
        r--;
        continue;
      }

      // swap
      int t = A[l];
      A[l] = A[r];
      A[r] = t;
      l++;
      r--;
    }

    return A;
  }

  /**
   * https://leetcode.com/problems/01-matrix/description/
   */
  public int[][] updateMatrix(int[][] matrix) {
    if (matrix.length == 0) return matrix;

    for (int i = 0; i < matrix.length; i++)
      for (int j = 0; j < matrix[0].length; j++)
        if (matrix[i][j] == 1 && !hasNeiberZero(i, j, matrix)) {
          matrix[i][j] = matrix.length + matrix[0].length + 1;
        }

    for (int i = 0; i < matrix.length; i++)
      for (int j = 0; j < matrix[0].length; j++)
        if (matrix[i][j] == 1) {
          dfs(matrix, i, j, -1);
        }

    return matrix;
  }

  private void dfs(int[][] matrix, int x, int y, int val) {
    if (x < 0 || y < 0 || y >= matrix[0].length || x >= matrix.length || matrix[x][y] <= val) {
      return;
    }

    if (val > 0) matrix[x][y] = val;

    dfs(matrix, x + 1, y, matrix[x][y] + 1);
    dfs(matrix, x - 1, y, matrix[x][y] + 1);
    dfs(matrix, x, y + 1, matrix[x][y] + 1);
    dfs(matrix, x, y - 1, matrix[x][y] + 1);
  }

  private boolean hasNeiberZero(int x, int y, int[][] matrix) {
    return x > 0 && matrix[x - 1][y] == 0
        || x < matrix.length - 1 && matrix[x + 1][y] == 0
        || y > 0 && matrix[x][y - 1] == 0
        || y < matrix[0].length - 1 && matrix[x][y + 1] == 0;
  }

  /**
   * https://leetcode.com/problems/single-element-in-a-sorted-array/description/
   */
  public int singleNonDuplicate(int[] nums) {
    int i = 0, max = nums.length - 1;
    while (i < max) {
      if (nums[i] != nums[i + 1]) {
        if (i == 0 || i != nums[i - 1]) return nums[i];

        if (i + 1 == max || i != nums[i + 2]) return nums[i + 1];
      }

      i += 2;
    }

    return nums[max];
  }

  private int[][] dirs =
      new int[][] {{1, 1}, {1, 0}, {1, -1}, {0, 1}, {0, -1}, {-1, 1}, {-1, 0}, {-1, -1}};

  /**
   * https://leetcode.com/problems/minesweeper/description/
   */
  public char[][] updateBoard(char[][] board, int[] click) {
    if (board[click[0]][click[1]] == 'M') {
      board[click[0]][click[1]] = 'X';
      return board;
    }
    dfs(click[0], click[1], board);
    return board;
  }

  private void dfs(int x, int y, char[][] board) {
    if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1 || board[x][y] != 'E') {
      return;
    }
    int mines = 0;
    for (int[] dir : dirs) {
      if (isMine(x + dir[0], y + dir[1], board)) mines++;
    }
    if (mines == 0) {
      board[x][y] = 'B';
      for (int[] dir : dirs) {
        dfs(x + dir[0], y + dir[1], board);
      }
    } else {
      board[x][y] = (char) ('0' + mines);
    }
  }

  private boolean isMine(int x, int y, char[][] board) {
    return x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 'M';
  }

  /**
   * https://leetcode.com/problems/monotonic-array/description/
   */
  public boolean isMonotonic(int[] A) {
    int flag = 0; // increment 1, decrement -1
    int last = A[0];

    for (int n : A) {

      if (n > last) {
        if (flag < 0) return false;
        flag = 1;
      } else if (n < last) {
        if (flag > 0) return false;
        flag = -1;
      }

      last = n;
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/continuous-subarray-sum/description/
   */
  public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>() {{
      put(0, -1);
    }};
    int runningSum = 0;
    for (int i = 0; i < nums.length; i++) {
      runningSum += nums[i];
      if (k != 0) runningSum %= k;
      Integer prev = map.get(runningSum);
      if (prev != null) {
        if (i - prev > 1) return true;
      } else {
        map.put(runningSum, i);
      }
    }
    return false;
  }

  /**
   * https://leetcode.com/problems/surface-area-of-3d-shapes/description/
   */
  public int surfaceArea(int[][] grid) {
    int total = 0, self = 0, around = 0;

    for (int i = 0; i < grid.length; i++) {
      int len = grid[i].length;
      for (int j = 0; j < len; j++) {
        total += grid[i][j];
        self += grid[i][j] > 0 ? grid[i][j] - 1 : 0;

        if (j < len - 1) { // right
          around += Math.min(grid[i][j], grid[i][j + 1]);
        }

        if (i < grid.length - 1) { // bottom
          around += Math.min(grid[i][j], grid[i + 1][j]);
        }
      }
    }

    return total * 6 - 2 * (self + around);
  }

  /**
   * https://leetcode.com/problems/coin-change-2/description/
   */
  public int change(int amount, int[] coins) {
    int[] ways = new int[amount + 1];
    ways[0] = 1;
    for (int val : coins) {
      for (int i = val; i <= amount; ++i) {
        ways[i] += ways[i - val];
      }
    }
    return ways[amount];
  }

  /**
   * https://leetcode.com/problems/fair-candy-swap/description/
   */
  public int[] fairCandySwap(int[] A, int[] B) {
    int aTotal = 0;
    int bTotal = 0;
    Set<Integer> aNums = new HashSet<>();

    for (int i : A) {
      aTotal += i;
      aNums.add(i);
    }

    for (int i : B) {
      bTotal += i;
    }

    int target = (bTotal - aTotal) / 2; //Equals A-B

    for (int i : B) {
      if (aNums.contains(i - target)) {
        return new int[] {i - target, i};
      }
    }

    return new int[] {0, 0};
  }

  /**
   * https://leetcode.com/problems/next-greater-element-ii/description/
   */
  public int[] nextGreaterElements(int[] nums) {
    int n = nums.length, next[] = new int[n];
    Arrays.fill(next, -1);
    Stack<Integer> stack = new Stack<>(); // index stack
    for (int i = 0; i < n * 2; i++) {
      int num = nums[i % n];
      while (!stack.isEmpty() && nums[stack.peek()] < num) {
        next[stack.pop()] = num;
      }
      if (i < n) stack.push(i);
    }
    return next;
  }

  /**
   * https://leetcode.com/problems/diagonal-traverse/description/
   */
  public int[] findDiagonalOrder(int[][] matrix) {
    if (matrix.length == 0) return new int[] {};
    int len = matrix[0].length;
    int[] ans = new int[matrix.length * len];

    int x = 0, y = 0, index = 0;
    boolean up = true;

    while (index < ans.length) {
      ans[index++] = matrix[x][y];

      if (index == ans.length) break;

      if ((x == 0 || y + 1 == len) && up) {
        if (y == len - 1) {
          x++;
        } else {
          y++;
        }

        up = false;
      } else if ((y == 0 || x + 1 == matrix.length) && !up) {
        if (x + 1 == matrix.length) {
          ++y;
        } else {
          ++x;
        }
        up = true;
      } else {
        x += up ? -1 : 1;
        y += up ? 1 : -1;
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/target-sum/description/
   */
  private int sumWays = 0;

  public int findTargetSumWays(int[] nums, int S) {
    findTargetSumWaysHelper(nums, 0, 0, S);
    return sumWays;
  }

  private void findTargetSumWaysHelper(int[] nums, int index, int temp, int S) {
    if (index == nums.length) {
      if (temp == S) sumWays++;
    } else {
      temp += nums[index];
      findTargetSumWaysHelper(nums, index + 1, temp, S);
      temp -= nums[index] * 2;
      findTargetSumWaysHelper(nums, index + 1, temp, S);
    }
  }

  /**
   * https://leetcode.com/problems/increasing-subsequences/description/
   */
  public List<List<Integer>> findSubsequences(int[] nums) {
    Set<List<Integer>> res = new HashSet<>();
    List<Integer> holder = new ArrayList<>();
    findSequence(res, holder, 0, nums);
    return new ArrayList<>(res);
  }

  public void findSequence(Set<List<Integer>> res, List<Integer> holder, int index, int[] nums) {
    if (holder.size() >= 2) {
      res.add(new ArrayList<>(holder));
    }
    for (int i = index; i < nums.length; i++) {
      if (holder.size() == 0 || holder.get(holder.size() - 1) <= nums[i]) {
        holder.add(nums[i]);
        findSequence(res, holder, i + 1, nums);
        holder.remove(holder.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/predict-the-winner/description/
   */
  public boolean PredictTheWinner(int[] nums) {
    int length = nums.length;

    int sum = 0;
    for (int num : nums) sum += num;

    int[][] dp = new int[length][length];

    for (int j = 0; j < length; j++) {
      int curSum = 0;
      for (int i = j; i >= 0; i--) {
        curSum += nums[i];
        if (i == j) {
          dp[i][j] = nums[j];
        } else {
          dp[i][j] = Math.max(curSum - dp[i][j - 1], curSum - dp[i + 1][j]);
        }
      }
    }
    return dp[0][length - 1] * 2 >= sum;
  }

  /**
   * https://leetcode.com/problems/total-hamming-distance/description/
   */
  public int totalHammingDistance(int[] nums) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      count += getHamming(nums, i);
    }
    return count;
  }

  private int getHamming(int[] arr, int b) {
    int len = arr.length;
    int count = 0;
    for (int anArr : arr) {
      count += (anArr >> b) & (0x01);
    }
    return count * (len - count);
  }

  /**
   * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
   */
  public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
    List<int[]> res = new ArrayList<>();
    if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
    for (int i = 0; i < nums1.length && i < k; i++) que.offer(new int[] {nums1[i], nums2[0], 0});
    while (k-- > 0 && !que.isEmpty()) {
      int[] cur = que.poll();
      res.add(new int[] {cur[0], cur[1]});
      if (cur[2] == nums2.length - 1) continue;
      que.offer(new int[] {cur[0], nums2[cur[2] + 1], cur[2] + 1});
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/projection-area-of-3d-shapes/description/
   */
  public int projectionArea(int[][] grid) {
    int ans = 0;
    int size = 0;

    for (int[] array : grid) {
      size = Math.max(size, array.length);
    }

    int[] zMax = new int[size];

    for (int[] array : grid) {
      int len = array.length;

      int yMax = 0;
      for (int j = 0; j < len; j++) {
        if (array[j] > 0) {
          ans++;
          zMax[j] = Math.max(zMax[j], array[j]);
          yMax = Math.max(yMax, array[j]);
        }
      }
      ans += yMax;
    }

    for (int n : zMax) {
      ans += n;
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/transpose-matrix/description/
   */
  public int[][] transpose(int[][] A) {
    if (A.length == 0) return A;
    int[][] ans = new int[A[0].length][A.length];

    for (int i = 0; i < A.length; i++) {
      int len = A[i].length;
      for (int j = 0; j < len; j++) {
        ans[j][i] = A[i][j];
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/lemonade-change/description/
   */
  public boolean lemonadeChange(int[] bills) {
    int five = 0, ten = 0;
    for (int i : bills) {
      if (i == 5) {
        five++;
      } else if (i == 10) {
        five--;
        ten++;
      } else if (ten > 0) {
        ten--;
        five--;
      } else {
        five -= 3;
      }
      if (five < 0) return false;
    }
    return true;
  }

  /**
   * https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
   */
  public int peakIndexInMountainArray(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int left = 0, right = A.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (A[mid] < A[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }

  /**
   * https://leetcode.com/problems/maximize-distance-to-closest-person/description/
   */
  public int maxDistToClosest(int[] seats) {
    int max = 0, last = -1;

    for (int i = 0; i < seats.length; i++) {
      if (seats[i] == 1) {
        max = Math.max(max, last < 0 ? i : (i - last) / 2);
        last = i;
      }
    }

    if (last < seats.length - 1) {
      max = Math.max(max, seats.length - last - 1);
    }

    return max;
  }

  /**
   * https://leetcode.com/problems/rectangle-overlap/description/
   */
  public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
    int left = Math.max(rec1[0], rec2[0]);
    int right = Math.min(rec1[2], rec2[2]);
    int top = Math.min(rec1[3], rec2[3]);
    int bot = Math.max(rec1[1], rec2[1]);
    return bot < top && right > left;
  }

  /**
   * https://leetcode.com/problems/flipping-an-image/description/
   */
  public int[][] flipAndInvertImage(int[][] A) {
    if (A.length == 0) return A;
    int last = A[0].length - 1;
    int[][] ans = new int[A.length][last + 1];

    for (int i = 0; i < ans.length; i++) {
      for (int j = last; j >= 0; j--) {
        ans[i][last - j] = A[i][j] ^ 1;
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/trapping-rain-water/description/
   */
  public int trap(int[] height) {
    if (height.length == 0) return 0;
    int ans = 0, start = 0;
    while (start < height.length && height[start] == 0) start++;
    while (start < height.length) {
      int h = height[start], t = height[start];
      int b = start + 1;
      for (; b < height.length; b++) {
        if (height[b] >= h) {
          break;
        } else {
          t += height[b];
        }
      }

      if (b < height.length) {
        int temp = h * (b - start) - t;
        if (temp > 0) ans += temp;
        start = b;
      } else {
        start++;
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/first-missing-positive/description/
   */
  public int firstMissingPositive(int[] nums) {
    int i = 0, n = nums.length;
    while (i < n) {
      if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
        int t = nums[nums[i] - 1];
        nums[nums[i] - 1] = nums[i];
        nums[i] = t;
      } else {
        i++;
      }
    }

    for (i = 0; i < n; i++) {
      if (nums[i] != i + 1) return i + 1;
    }
    return n + 1;
  }

  /**
   * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
    int n1 = nums1.length, n2 = nums2.length;
    int lo = 0, hi = n1;
    while (lo <= hi) {
      int c1 = (lo + hi) / 2;
      int c2 = (n1 + n2) / 2 - c1;
      int l1 = c1 == 0 ? Integer.MIN_VALUE : nums1[c1 - 1];
      int l2 = c2 == 0 ? Integer.MIN_VALUE : nums2[c2 - 1];
      int h1 = c1 == n1 ? Integer.MAX_VALUE : nums1[c1];
      int h2 = c2 == n2 ? Integer.MAX_VALUE : nums2[c2];
      if (l1 > h2) {
        hi = c1 - 1;
      } else if (l2 > h1) {
        lo = c1 + 1;
      } else {
        return (n1 + n2) % 2 > 0 ? Math.min(h1, h2)
            : (Integer.max(l1, l2) + Math.min(h1, h2)) / 2.0;
      }
    }
    return 0;
  }

  /**
   * https://leetcode.com/problems/longest-increasing-subsequence/description/
   */
  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    int size = 0;
    int[] tail = new int[nums.length];
    for (int n : nums) {
      int l = 0, r = size;
      while (l != r) {
        int m = (l + r) / 2;
        if (tail[m] < n) {
          l = m + 1;
        } else {
          r = m;
        }
      }
      tail[l] = n;
      if (l == size) size++;
    }
    return size;
  }

  /**
   * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
   */
  public boolean searchMatrixII(int[][] matrix, int target) {

    if (matrix.length == 0) return false;
    int m = matrix[0].length;
    for (int[] a : matrix) {
      if (a[0] == target || a[m - 1] == target) return true;
      if (a[0] < target && a[m - 1] > target) {
        if (Arrays.binarySearch(a, target) >= 0) return true;
      }
    }

    return false;
  }

  /**
   * https://leetcode.com/problems/product-of-array-except-self/description/
   */
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) {
      res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
      res[i] *= right;
      right *= nums[i];
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/majority-element-ii/description/
   */
  public List<Integer> majorityElementII(int[] nums) {
    List<Integer> ans = new ArrayList<>();
    if (nums.length == 0) return ans;
    int flag = nums.length / 3;

    Arrays.sort(nums);
    int count = 0;

    for (int i = 0; i < nums.length; i++) {
      if (i == 0) {
        count = 1;
      } else if (nums[i] != nums[i - 1]) {
        if (count > flag) ans.add(nums[i - 1]);
        count = 1;
      } else {
        count++;
      }
    }
    if (count > flag) ans.add(nums[nums.length - 1]);
    return ans;
  }

  /**
   * https://leetcode.com/problems/summary-ranges/description/
   */
  public List<String> summaryRanges(int[] nums) {
    List<String> ans = new ArrayList<>();
    if (nums == null || nums.length == 0) return ans;
    StringBuilder sb = new StringBuilder();
    long pre = 0, start = 0;
    for (int i = 0; i < nums.length; i++) {
      long cur = nums[i];
      if (i == 0) {
        sb.append(cur);
        start = cur;
      } else if (cur - pre > 1) {
        if (pre != start) sb.append("->").append(pre);
        ans.add(sb.toString());
        sb.setLength(0);
        sb.append(cur);
        start = cur;
      }
      pre = cur;
    }

    if (pre != start) sb.append("->").append(pre);
    ans.add(sb.toString());

    return ans;
  }

  /**
   * https://leetcode.com/problems/maximal-square/description/
   */
  public int maximalSquare(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return 0;
    int max = 0;
    int len = matrix[0].length;
    for (int x = 0; x < matrix.length; x++) {
      for (int y = 0; y < len; y++) {
        if (matrix[x][y] == '1') {
          max = Math.max(max, maximalSquareHelper(matrix, x, y));
        }
      }
    }
    return max;
  }

  private int maximalSquareHelper(char[][] matrix, int x, int y) {
    int len = Math.min(matrix.length - x, matrix[x].length - y);
    int i = 1;
    boolean found = false;
    for (; i < len; i++) {
      if (found) break;
      for (int j = y; j <= y + i; j++) {
        if (matrix[x + i][j] != '1') {
          found = true;
          break;
        }
      }

      for (int j = x; j < x + i; j++) {
        if (matrix[j][y + i] != '1') {
          found = true;
          break;
        }
      }
    }
    if (found) i--;
    return i * i;
  }

  /**
   * https://leetcode.com/problems/contains-duplicate-iii/description/
   */
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    TreeSet<Long> set = new TreeSet<>();
    for (int i = 0; i < nums.length; i++) {

      Long ceiling = set.ceiling((long) nums[i]);
      Long floor = set.floor((long) nums[i]);
      if (ceiling != null && ceiling - nums[i] <= t || floor != null && nums[i] - floor <= t) {
        return true;
      }
      set.add((long) nums[i]);
      if (i >= k) {
        set.remove((long) nums[i - k]);
      }
    }
    return false;
  }

  /**
   * https://leetcode.com/problems/combination-sum-iii/description/
   */
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> ans = new ArrayList<>();
    combinationSum3Helper(ans, new ArrayList<>(), k, n);
    return ans;
  }

  private void combinationSum3Helper(List<List<Integer>> ans, List<Integer> temp, int k, int n) {
    if (n < 0 || k == 0 && n > 0) return;
    if (k == 0 && n == 0) {
      ans.add(new ArrayList<>(temp));
      return;
    }

    int start = temp.size() > 0 ? temp.get(temp.size() - 1) : 0;
    for (int i = start + 1; i <= 9 && i <= n; i++) {
      temp.add(i);
      combinationSum3Helper(ans, temp, k - 1, n - i);
      temp.remove(temp.size() - 1);
    }
  }

  /**
   * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
   */
  public int findKthLargest(int[] nums, int k) {
    List<Integer> list = new ArrayList<>();

    for (int n : nums) {
      if (list.size() == k) {
        if (n > list.get(0)) {
          list.remove(0);
          sortedInsert(list, n);
        }
      } else {
        sortedInsert(list, n);
      }
    }

    return list.get(0);
  }

  private void sortedInsert(List<Integer> list, int n) {
    int l = 0, r = list.size() - 1;
    while (l <= r) {
      if (list.get(l) >= n) break;
      if (list.get(r) <= n) {
        l = r + 1;
        break;
      }
      l++;
      r--;
    }
    list.add(l, n);
  }

  /**
   * https://leetcode.com/problems/house-robber-ii/description/
   */
  public int robII(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }

    int a = 0, b = 0;

    for (int i = 0; i < nums.length - 1; i++) {
      if (i % 2 == 0) {
        a = Math.max(b, a + nums[i]);
      } else {
        b = Math.max(a, b + nums[i]);
      }
    }

    int max = Math.max(a, b);
    a = 0;
    b = 0;

    for (int i = 1; i < nums.length; i++) {
      if (i % 2 == 0) {
        a = Math.max(b, a + nums[i]);
      } else {
        b = Math.max(a, b + nums[i]);
      }
    }

    max = Math.max(max, Math.max(a, b));

    return max;
  }

  /**
   * https://leetcode.com/problems/course-schedule-ii/description/
   */
  //public int[] findOrder(int numCourses, int[][] prerequisites) {
  //  Map<Integer, List<Integer>> sortMap = new HashMap<>(numCourses);
  //  for (int[] c : prerequisites) {
  //    List<Integer> child = sortMap.computeIfAbsent(c[1], k -> new ArrayList<>());
  //    child.add(c[0]);
  //  }
  //}

  /**
   * https://leetcode.com/problems/minimum-size-subarray-sum/description/
   */

  public int minSubArrayLen(int s, int[] nums) {
    int min_len = Integer.MAX_VALUE;
    int left = 0;
    int right = 0;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      while (sum >= s) {
        min_len = Math.min(min_len, i - left + 1);
        sum -= nums[left++];
      }
    }

    return min_len == Integer.MAX_VALUE ? 0 : min_len;
  }

  /**
   * https://leetcode.com/problems/course-schedule/description/
   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, Course> map = new HashMap<>(numCourses);

    for (int[] prerequisite : prerequisites) {
      Course child = map.get(prerequisite[0]);
      if (child == null) {
        child = new Course(prerequisite[0]);
        map.put(prerequisite[0], child);
      }

      if (prerequisite.length == 1) continue;
      Course parent = map.get(prerequisite[1]);
      if (parent == null) {
        parent = new Course(prerequisite[1], child);
        map.put(prerequisite[1], parent);
      } else {
        parent.children.add(child);
      }
    }

    for (int i = 0; i < numCourses; i++) {
      if (!map.containsKey(i) || map.get(i).children.isEmpty()) continue;
      if (map.get(i).isCyclic()) return false;
    }

    return true;
  }

  private static class Course {
    private boolean visited;
    private boolean done;
    private int value;
    private List<Course> children = new ArrayList<>();

    public Course(int value) {
      this.value = value;
    }

    public Course(int value, Course child) {
      this.value = value;
      children.add(child);
    }

    public boolean isCyclic() {
      if (done) return false;
      if (visited) return true;
      visited = true;
      for (Course c : children) {
        if (c.isCyclic()) return true;
      }

      done = true;
      return false;
    }
  }

  /**
   * https://leetcode.com/problems/number-of-islands/description/
   */
  public int numIslands(char[][] grid) {
    if (grid.length == 0) return 0;
    int ans = 0;
    int size = grid[0].length;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < size; j++) {
        if (grid[i][j] == '0') continue;
        numIslandsHelper(grid, i, j);
        ans++;
      }
    }

    return ans;
  }

  private void numIslandsHelper(char[][] grid, int x, int y) {
    grid[x][y] = '0';
    // left
    if (x > 0 && grid[x - 1][y] == '1') {
      numIslandsHelper(grid, x - 1, y);
    }

    // top
    if (y < grid[x].length - 1 && grid[x][y + 1] == '1') {
      numIslandsHelper(grid, x, y + 1);
    }

    // right
    if (x < grid.length - 1 && grid[x + 1][y] == '1') {
      numIslandsHelper(grid, x + 1, y);
    }

    // bottom
    if (y > 0 && grid[x][y - 1] == '1') {
      numIslandsHelper(grid, x, y - 1);
    }
  }

  /**
   * https://leetcode.com/problems/find-peak-element/description/
   */
  public int findPeakElement(int[] nums) {
    if (nums.length < 2) return 0;
    int l = 0, r = nums.length - 1;
    while (l <= r) {
      if ((l == 0 || nums[l] > nums[l - 1]) && nums[l] > nums[l + 1]) {
        return l;
      } else {
        l++;
      }

      if (nums[r] > nums[r - 1] && (r == nums.length - 1 || nums[r] > nums[r + 1])) {
        return r;
      } else {
        r--;
      }
    }

    return 0;
  }

  /**
   * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
   */
  public int findMin(int[] nums) {
    int l = 0, r = nums.length - 1;
    while (l < r) {
      int mid = (l + r) / 2;
      if (nums[mid] > nums[r]) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }

    return nums[l];
  }

  /**
   * https://leetcode.com/problems/maximum-product-subarray/description/
   */
  public int maxProduct(int[] nums) {
    int firstNeg = 0;
    int p = nums[0];
    int maxP = nums[0];
    for (int i = 1; i < nums.length; i++) {
      if (p < 0 && firstNeg == 0) {
        firstNeg = p;
      }

      if (nums[i - 1] == 0 && nums[i] != 0) {
        p = nums[i];
        firstNeg = 0;
      } else {
        p = p * nums[i];
      }

      if (p < 0 && firstNeg < 0) {
        maxP = Math.max(maxP, p / firstNeg);
      } else {
        maxP = Math.max(maxP, p);
      }
    }

    return maxP;
  }

  /**
   * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
   */
  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
      switch (token) {
        case "+":
          stack.push(stack.pop() + stack.pop());
          break;

        case "-":
          stack.push(-stack.pop() + stack.pop());
          break;

        case "*":
          stack.push(stack.pop() * stack.pop());
          break;

        case "/":
          int n1 = stack.pop(), n2 = stack.pop();
          stack.push(n2 / n1);
          break;

        default:
          stack.push(Integer.parseInt(token));
      }
    }

    return stack.pop();
  }

  /**
   * https://leetcode.com/problems/single-number-ii/description/
   */
  public int singleNumberII(int[] nums) {
    int ans = 0;
    for (int i = 0; i < 32; i++) {
      int sum = 0;
      for (int j = 0; j < nums.length; j++) {
        if ((nums[j] >> i & 1) == 1) {
          sum = (sum + 1) % 3;
        }
      }

      if (sum != 0) {
        ans |= 1 << i;
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/gas-station/description/
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int gasSum = 0;
    int costSum = 0;
    int tank = 0;
    int start = 0;
    for (int i = 0; i < gas.length; i++) {
      gasSum += gas[i];
      costSum += cost[i];
      tank += (gas[i] - cost[i]);
      if (tank < 0) {
        tank = 0;
        start = i + 1;
      }
    }
    if (gasSum < costSum) {
      return -1;
    }
    return start;
  }

  /**
   * https://leetcode.com/problems/surrounded-regions/description/
   */
  public void solve(char[][] board) {

    if (board.length < 2 || board[0].length < 2) return;

    int[][] keep = new int[board.length][board[0].length];

    for (int i = 0; i < board.length; i++) {
      if (board[i][0] == 'O') {
        solveHelper(board, keep, i, 0);
      }

      if (board[i][board[0].length - 1] == 'O') {
        solveHelper(board, keep, i, board[0].length - 1);
      }
    }

    for (int i = 0; i < board[0].length; i++) {
      if (board[0][i] == 'O') {
        solveHelper(board, keep, 0, i);
      }

      if (board[board.length - 1][i] == 'O') {
        solveHelper(board, keep, board.length - 1, i);
      }
    }

    for (int x = 1; x < board.length - 1; x++) {
      for (int y = 1; y < board[0].length - 1; y++) {
        if (keep[x][y] == 0) board[x][y] = 'X';
      }
    }
  }

  private void solveHelper(char[][] board, int[][] keep, int x, int y) {
    if (keep[x][y] == 1) return;
    keep[x][y] = 1;

    // left
    if (y > 0 && board[x][y - 1] == 'O') {
      solveHelper(board, keep, x, y - 1);
    }

    // top
    if (x > 0 && board[x - 1][y] == 'O') {
      solveHelper(board, keep, x - 1, y);
    }

    // right
    if (y < board[0].length - 1 && board[x][y + 1] == 'O') {
      solveHelper(board, keep, x, y + 1);
    }

    // bottom
    if (x < board.length - 1 && board[x + 1][y] == 'O') {
      solveHelper(board, keep, x + 1, y);
    }
  }

  /**
   * https://leetcode.com/problems/word-ladder/description/
   */
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    int index = -1;
    for (int i = 0; i < wordList.size(); i++) {
      if (wordList.get(i).equals(endWord)) {
        index = i;
        break;
      }
    }
    if (index < 0) return 0;
    int step = 0;
    for (; index >= 0; index--) {
      step++;
      if (ladderHelper(beginWord, wordList.get(index))) return step;
      if (step > 1 && !ladderHelper(wordList.get(index), wordList.get(index + 1))) return 0;
    }

    return 0;
  }

  private boolean ladderHelper(String a, String b) {
    boolean found = false;

    for (char c : a.toCharArray()) {
      if (b.indexOf(c) < 0) {
        if (found) return false;
        found = true;
      }
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/triangle/description/
   */

  public int minimumTotal(List<List<Integer>> triangle) {
    if (triangle == null) return 0;
    int len = triangle.size();
    int[] dp = new int[len];
    for (int i = 0; i < dp.length; i++)
      dp[i] = triangle.get(len - 1).get(i);
    for (int i = len - 2; i >= 0; i--) {
      List<Integer> row = triangle.get(i);
      for (int j = 0; j < row.size(); j++)
        dp[j] = row.get(j) + Math.min(dp[j], dp[j + 1]);
    }
    return dp[0];
  }

  /**
   * https://leetcode.com/problems/subsets-ii/description/
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    ans.add(new ArrayList<>());
    for (int i = 0, prev = 0; i < nums.length; i++) {
      int size = ans.size();
      for (int j = (i == 0 || nums[i] != nums[i - 1]) ? 0 : prev; j < size; j++) {
        List<Integer> cur = new ArrayList<>(ans.get(j));
        cur.add(nums[i]);
        ans.add(cur);
      }
      prev = size;
    }
    return ans;
  }

  /**
   * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
   */
  public boolean search(int[] nums, int target) {
    if (nums == null || nums.length == 0) return false;

    int l = 0, r = nums.length - 1;
    while (l < r) {

      while (l < nums.length - 1 && nums[l] == nums[l + 1]) l++;
      while (r > 0 && nums[r] == nums[r - 1]) r--;

      int mid = (l + r) / 2;
      if (nums[mid] == target || nums[l] == target || nums[r] == target) return true;
      if (nums[mid] >= nums[r]) {
        if (target > nums[mid] || target < nums[r]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      } else {
        if (target > nums[mid] && target < nums[r]) {
          l = mid + 1;
        } else {
          r = mid;
        }
      }
    }

    return nums[l] == target;
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
   */
  public int removeDuplicatesII(int[] nums) {
    if (nums.length < 3) return nums.length;
    int ans = nums.length - 1;
    int count = 1;
    int last = nums[ans];
    for (int i = ans - 1; i >= 0; i--) {
      if (nums[i] == last) {
        count++;
        if (count > 2) {
          int t = nums[i];
          nums[i] = nums[ans];
          nums[ans] = t;
          ans--;
          count--;
        }
      } else {
        count = 1;
        last = nums[i];
      }
    }

    Arrays.sort(nums, 0, ans + 1);
    return ans + 1;
  }

  /**
   * https://leetcode.com/problems/word-search/description/
   */
  public boolean exist(char[][] board, String word) {

    char[] w = word.toCharArray();
    for (int y = 0; y < board.length; y++) {
      for (int x = 0; x < board[y].length; x++) {
        if (exist(board, y, x, w, 0)) return true;
      }
    }
    return false;
  }

  private boolean exist(char[][] board, int y, int x, char[] word, int i) {
    if (i == word.length) return true;
    if (y < 0 || x < 0 || y == board.length || x == board[y].length) return false;
    if (board[y][x] != word[i]) return false;
    board[y][x] ^= 256;
    boolean exist = exist(board, y, x + 1, word, i + 1)
        || exist(board, y, x - 1, word, i + 1)
        || exist(board, y + 1, x, word, i + 1)
        || exist(board, y - 1, x, word, i + 1);
    board[y][x] ^= 256;
    return exist;
  }

  /**
   * https://leetcode.com/problems/subsets/description/
   */
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    ans.add(new ArrayList<>());

    for (int i = 1; i <= nums.length; i++) {
      subsetsHelper(ans, new ArrayList<>(), nums, -1, i);
    }

    return ans;
  }

  private void subsetsHelper(List<List<Integer>> ans, List<Integer> t, int[] nums, int start,
      int size) {
    if (t.size() == size) {
      ans.add(new ArrayList<>(t));
    } else {
      int last = nums.length + t.size() - size;
      for (int i = start + 1; i <= last; i++) {
        t.add(nums[i]);
        subsetsHelper(ans, t, nums, i, size);
        t.remove(t.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/combinations/description/
   */
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    combineHelper(n, k, ans, new ArrayList<>());
    return ans;
  }

  private void combineHelper(int n, int k, List<List<Integer>> ans, List<Integer> t) {
    if (t.size() == k) {
      ans.add(new ArrayList<>(t));
    } else {
      int start = t.size() > 0 ? t.get(t.size() - 1) : 0;
      int last = n + 1 + t.size() - k;
      for (int i = start + 1; i <= last; i++) {
        t.add(i);
        combineHelper(n, k, ans, t);
        t.remove(t.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/sort-colors/description/
   */
  public void sortColors(int[] nums) {
    int n0 = -1, n1 = -1, n2 = -1;
    for (int n : nums) {
      if (n == 0) {
        nums[++n2] = 2;
        nums[++n1] = 1;
        nums[++n0] = 0;
      } else if (n == 1) {
        nums[++n2] = 2;
        nums[++n1] = 1;
      } else if (n == 2) {
        nums[++n2] = 2;
      }
    }
  }

  /**
   * https://leetcode.com/problems/search-a-2d-matrix/description/
   */
  public boolean searchMatrix(int[][] matrix, int target) {

    if (matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0] || target > matrix[
        matrix.length
            - 1][matrix[0].length - 1]) {
      return false;
    }

    int cols = matrix[0].length;

    for (int[] aMatrix : matrix) {
      if (target > aMatrix[0] && target < aMatrix[cols - 1]) {
        return searchMatrixBS(aMatrix, target);
      } else if (target == aMatrix[0] || target == aMatrix[cols - 1]) return true;
    }

    return false;
  }

  private boolean searchMatrixBS(int[] nums, int target) {
    int l = 0, r = nums.length - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] == target) {
        return true;
      } else if (nums[mid] < target) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }

    return false;
  }

  /**
   * https://leetcode.com/problems/set-matrix-zeroes/description/
   */
  public void setZeroes(int[][] matrix) {
    boolean start = false;
    int rows = matrix.length;
    int cols = matrix[0].length;
    // use matrix[x][0] & matrix[0][x] to store the information.
    for (int i = 0; i < rows; i++) {
      if (matrix[i][0] == 0) start = true;
      for (int j = 1; j < cols; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = matrix[0][j] = 0;
        }
      }
    }

    for (int i = rows - 1; i >= 0; i--) {
      for (int j = cols - 1; j > 0; j--) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }

      if (start) matrix[i][0] = 0;
    }
  }

  /**
   * https://leetcode.com/problems/minimum-path-sum/description/
   */
  public int minPathSum(int[][] grid) {
    return minPathSumHelper(grid, 0, 0);
  }

  private int minPathSumHelper(int[][] grid, int x, int y) {
    int sum = grid[x][y];
    if (x == grid.length - 1 && y == grid[0].length - 1) {
      return sum;
    }

    if (x == grid.length - 1) {
      sum += minPathSumHelper(grid, x, y + 1);
    } else if (y == grid[0].length - 1) {
      sum += minPathSumHelper(grid, x + 1, y);
    } else {
      sum += Math.min(minPathSumHelper(grid, x, y + 1), minPathSumHelper(grid, x + 1, y));
    }

    return sum;
  }

  /**
   * https://leetcode.com/problems/unique-paths-ii/description/
   */
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int width = obstacleGrid[0].length;
    int[] dp = new int[width];
    dp[0] = 1;
    for (int[] row : obstacleGrid) {
      for (int j = 0; j < width; j++) {
        if (row[j] == 1) {
          dp[j] = 0;
        } else if (j > 0) {
          dp[j] += dp[j - 1];
        }
      }
    }
    return dp[width - 1];
  }

  /**
   * https://leetcode.com/problems/permutation-sequence/description/
   */
  public String getPermutation(int n, int k) {
    StringBuilder sb = new StringBuilder();
    ArrayList<Integer> num = new ArrayList<Integer>();
    int fact = 1;
    for (int i = 1; i <= n; i++) {
      fact *= i;
      num.add(i);
    }
    for (int i = 0, l = k - 1; i < n; i++) {
      fact /= (n - i);
      int index = (l / fact);
      sb.append(num.remove(index));
      l -= index * fact;
    }
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/jump-game/description/
   */
  public boolean canJump(int[] nums) {
    int leftMostGoodPosition = nums.length - 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      if (i + nums[i] >= leftMostGoodPosition) {
        leftMostGoodPosition = i;
      }
    }

    return leftMostGoodPosition == 0;
  }

  /**
   * https://leetcode.com/problems/spiral-matrix-ii/description/
   */
  public int[][] generateMatrix(int n) {
    if (n == 0) {
      return new int[0][0];
    }
    int rMatrix[][] = new int[n][n];
    int stx = 0;
    int enx = n - 1;
    int sty = 0;
    int eny = n - 1;
    int count = 1;
    while (stx <= enx && sty <= eny) {
      for (int i = stx; i <= enx; i++)
        rMatrix[sty][i] = count++;
      sty++;
      for (int i = sty; i <= eny; i++)
        rMatrix[i][enx] = count++;
      enx--;
      for (int i = enx; i >= stx; i--) {
        rMatrix[eny][i] = count++;
      }
      eny--;
      for (int i = eny; i >= sty; i--) {
        rMatrix[i][stx] = count++;
      }
      stx++;
    }
    return rMatrix;
  }

  /**
   * https://leetcode.com/problems/spiral-matrix/description/
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> ans = new ArrayList<>();
    if (matrix == null || matrix.length == 0) return ans;

    int m = matrix.length, n = matrix[0].length;
    int size = m * n;

    for (int i = 0; i < n - 1; i++) {
      ans.add(matrix[0][i]);
    }
    if (ans.size() == size) return ans;
    for (int i = 0; i < m; i++) {
      int k = n - 1 - i;
      int j = i;
      // (i, n-1-i) -> (m-1-i, n-1-i)
      for (; j < m - i; j++) {
        ans.add(matrix[j][k]);
        if (ans.size() == size) return ans;
      }
      // (m-1-i, n-1-i) -> (m-1-i, i)
      j--;
      k--;
      for (; k > i; k--) {
        ans.add(matrix[j][k]);
        if (ans.size() == size) return ans;
      }

      // (m-1-i, i) -> (i+1, i)
      for (; j > i; j--) {
        ans.add(matrix[j][i]);
        if (ans.size() == size) return ans;
      }

      // (i+1,i) -> (i+1, n-1-i-1)
      j++;
      k = n - 2 - i;
      for (int t = i + 1; t < k; t++) {
        ans.add(matrix[j][t]);
        if (ans.size() == size) return ans;
      }
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/group-anagrams/description/
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
      char[] t = s.toCharArray();
      Arrays.sort(t);
      String key = String.valueOf(t);
      if (!map.containsKey(key)) map.put(key, new ArrayList<>());
      map.get(key).add(s);
    }

    return new ArrayList<>(map.values());
  }

  /**
   * https://leetcode.com/problems/rotate-image/description/
   */
  public void rotate(int[][] matrix) {

    int n = matrix.length - 1;

    for (int i = 0; i < (matrix.length + 1) / 2; i++) {
      for (int j = 0; j < matrix.length / 2; j++) {
        int t = matrix[i][j];
        matrix[i][j] = matrix[n - j][i];
        matrix[n - j][i] = matrix[n - i][n - j];
        matrix[n - i][n - j] = matrix[j][n - i];
        matrix[j][n - i] = t;
      }
    }
  }

  /**
   * https://leetcode.com/problems/combination-sum-ii/description/
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();

    Arrays.sort(candidates);
    combinationSum2Helper(ans, new ArrayList<>(), candidates, target, 0);

    return ans;
  }

  private void combinationSum2Helper(List<List<Integer>> ans, List<Integer> temp, int[] candidates,
      int target,
      int index) {
    if (target < 0) return;
    if (temp.size() > 0 && target == 0) {
      ans.add(new ArrayList<>(temp));
    } else {
      for (int i = index; i < candidates.length; i++) {

        if (i > index && candidates[i] == candidates[i - 1]) continue;

        temp.add(candidates[i]);
        combinationSum2Helper(ans, temp, candidates, target - candidates[i], i + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/combination-sum/description/
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();

    Arrays.sort(candidates);
    combinationSumHelper(ans, new ArrayList<>(), candidates, target, 0);

    return ans;
  }

  private void combinationSumHelper(List<List<Integer>> ans, List<Integer> temp, int[] candidates,
      int target,
      int index) {
    if (target < 0) return;
    if (temp.size() > 0 && target == 0) {
      ans.add(new ArrayList<>(temp));
    } else {
      for (int i = index; i < candidates.length; i++) {
        temp.add(candidates[i]);
        combinationSumHelper(ans, temp, candidates, target - candidates[i], i);
        temp.remove(temp.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/valid-sudoku/description/
   */
  public boolean isValidSudoku(char[][] board) {
    int[] temp;

    // line check
    for (char[] l : board) {
      temp = new int[10];
      for (char c : l) {
        if (!checkSudoku(c, temp)) return false;
      }
    }

    // row check
    for (int i = 0; i < board.length; i++) {
      temp = new int[10];
      for (char[] r : board) {
        char c = r[i];
        if (!checkSudoku(c, temp)) return false;
      }
    }

    // square check
    for (int i = 1; i < 9; i += 3) {
      for (int j = 1; j < 9; j += 3) {
        temp = new int[10];
        if (!squareCheckSudoku(board, i, j, temp)) return false;
      }
    }
    return true;
  }

  private boolean checkSudoku(char c, int[] temp) {
    if (c == '.') return true;
    int index = c - '0';
    if (temp[index] > 0) {
      return false;
    } else {
      temp[index] = 1;
      return true;
    }
  }

  private boolean squareCheckSudoku(char[][] board, int x, int y, int[] temp) {
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        char c = board[i][j];
        if (!checkSudoku(c, temp)) return false;
      }
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/search-for-a-range/description/
   */
  public int[] searchRange(int[] nums, int target) {
    int[] ans = new int[] {-1, -1};

    if (nums.length == 0 || nums[0] > target || nums[nums.length - 1] < target) return ans;

    int l = 0, r = nums.length - 1;

    while (l < r) {
      int mid = (l + r) / 2;
      if (nums[mid] < target) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }

    if (nums[l] != target) {
      return ans;
    } else {
      ans[0] = l;
    }

    r = nums.length - 1;
    while (l < r) {
      int mid = (l + r) / 2 + 1;
      if (nums[mid] > target) {
        r = mid - 1;
      } else {
        l = mid;
      }
    }

    ans[1] = r;

    return ans;
  }

  /**
   * https://leetcode.com/problems/search-in-rotated-sorted-array/description/ Search in Rotated
   * Sorted Array
   */
  public int searchRotatedSortedArray(int[] nums, int target) {
    if (nums == null || nums.length == 0) return -1;
    int start = 0, end = nums.length - 1;
    while (start < end) {
      int mid = (start + end) / 2;
      if (nums[mid] > nums[end]) {  // eg. 3,4,5,6,1,2
        if (target > nums[mid] || target <= nums[end]) {
          start = mid + 1;
        } else {
          end = mid;
        }
      } else {  // eg. 5,6,1,2,3,4
        if (target > nums[mid] && target <= nums[end]) {
          start = mid + 1;
        } else {
          end = mid;
        }
      }
    }
    if (start == end && target != nums[start]) return -1;
    return start;
  }

  /**
   * https://leetcode.com/problems/permutations-ii/description/
   */
  public List<List<Integer>> permuteUniqueBetter(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    puHelper(nums, 0, ans);
    return ans;
  }

  private void puHelper(int[] nums, int index, List<List<Integer>> ret) {
    if (index == nums.length - 1) {
      List<Integer> list = new ArrayList<>();
      for (int n : nums) list.add(n);
      ret.add(list);
    } else {
      for (int i = index; i < nums.length; i++) {
        boolean flag = false;
        for (int j = index; j < i; j++) {
          if (nums[j] == nums[i]) {
            flag = true;
            break;
          }
        }

        if (flag) continue;

        puSwap(nums, index, i);
        puHelper(nums, index + 1, ret);
        puSwap(nums, index, i);
      }
    }
  }

  private void puSwap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    permuteUniqueHelper(ans, new ArrayList<>(), nums, new ArrayList<>());
    return ans;
  }

  private void permuteUniqueHelper(List<List<Integer>> ans, List<Integer> temp, int[] nums,
      List<Integer> indexs) {
    if (temp.size() == nums.length) {
      ans.add(new ArrayList<>(temp));
    } else {
      for (int i = 0; i < nums.length; i++) {
        if (i > 0 && nums[i] == nums[i - 1] && indexs.contains(i - 1) || indexs.contains(i)) {
          continue;
        }
        temp.add(nums[i]);
        indexs.add(i);
        permuteUniqueHelper(ans, temp, nums, indexs);
        indexs.remove(indexs.size() - 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/permutations/description/
   */
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    permuteHelper(ans, new ArrayList<>(), nums);
    return ans;
  }

  private void permuteHelper(List<List<Integer>> ans, List<Integer> temp, int[] nums) {
    if (temp.size() == nums.length) {
      ans.add(new ArrayList<>(temp));
    } else {
      for (int n : nums) {
        if (temp.contains(n)) continue;
        temp.add(n);
        permuteHelper(ans, temp, nums);
        temp.remove(temp.size() - 1);
      }
    }
  }

  /**
   * https://leetcode.com/problems/next-permutation/description/
   */
  public void nextPermutation(int[] nums) {

    if (nums == null || nums.length < 2) return;

    int index = nums.length - 1;
    while (index > 0 && nums[index] <= nums[index - 1]) {
      index--;
    }
    if (index == 0) {
      Arrays.sort(nums);
      return;
    }

    int t = nums[index - 1];
    int i = index;
    for (; i < nums.length; i++) {
      if (nums[i] <= t) {
        break;
      }
    }

    nums[index - 1] = nums[i - 1];
    nums[i - 1] = t;

    Arrays.sort(nums, index, nums.length);
  }

  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < nums.length - 2; i++) {
      if (i == 0 || nums[i] != nums[i - 1]) {
        int l = i + 1, r = nums.length - 1, diff = -nums[i];
        while (l < r) {
          int sum = nums[l] + nums[r];
          if (sum == diff) {
            result.add(Arrays.asList(nums[i], nums[l], nums[r]));
            while (l < r && nums[l] == nums[l + 1]) l++;
            while (l < r && nums[r] == nums[r - 1]) r--;
            l++;
            r--;
          } else if (sum < diff) {
            while (l < r && nums[l] == nums[l + 1]) l++;
            l++;
          } else {
            while (l < r && nums[r] == nums[r - 1]) r--;
            r--;
          }
        }
      }
    }
    return result;
  }

  /**
   * https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
   */
  public char nextGreatestLetter(char[] letters, char target) {
    if (letters[letters.length - 1] <= target) return letters[0];
    int l = 0, r = letters.length - 1;
    while (l < r) {
      int mid = (l + r) / 2;
      if (letters[mid] <= target) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    return letters[l];
  }

  /**
   * https://leetcode.com/problems/flood-fill/description/
   */
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    int t = image[sr][sc];
    if (t == newColor) return image;
    image[sr][sc] = newColor;
    // left
    if (sr - 1 >= 0 && image[sr - 1][sc] == t) {
      floodFill(image, sr - 1, sc, newColor);
    }
    // top
    if (sc - 1 >= 0 && image[sr][sc - 1] == t) {
      floodFill(image, sr, sc - 1, newColor);
    }
    // right
    if (sr + 1 < image.length && image[sr + 1][sc] == t) {
      floodFill(image, sr + 1, sc, newColor);
    }
    // bottom
    if (sc + 1 < image[sr].length && image[sr][sc + 1] == t) {
      floodFill(image, sr, sc + 1, newColor);
    }
    return image;
  }

  /**
   * https://leetcode.com/problems/self-dividing-numbers/description/
   */
  public List<Integer> selfDividingNumbers(int left, int right) {
    List<Integer> r = new ArrayList<>();
    for (int i = left; i <= right; i++) {
      if (checkSelfDividing(i)) r.add(i);
    }

    return r;
  }

  private boolean checkSelfDividing(int n) {
    if (n > 0 && n < 10) return true;
    int t = n;

    do {
      int e = t % 10;
      if (e == 0 || n % e != 0) return false;
      t /= 10;
    } while (t > 0);
    return true;
  }

  /**
   * https://leetcode.com/problems/find-pivot-index/description/
   */
  public int pivotIndex(int[] nums) {
    int sum = 0, left = 0;
    for (int n : nums) sum += n;
    for (int i = 0; i < nums.length; left += nums[i++]) {
      if (left * 2 == sum - nums[i]) return i;
    }
    return -1;
  }

  /**
   * https://leetcode.com/problems/1-bit-and-2-bit-characters/description/
   */
  public boolean isOneBitCharacter(int[] bits) {
    int i = 0;
    for (; i < bits.length - 1; i++)
      if (bits[i] != 0) i++;
    return i != bits.length;
  }

  /**
   * https://leetcode.com/problems/degree-of-an-array/description/
   */
  public int findShortestSubArray(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> startMap = new HashMap<>();
    Map<Integer, Integer> endMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      map.put(n, map.getOrDefault(n, 0) + 1);
      if (startMap.containsKey(n)) {
        endMap.put(n, i);
      } else {
        startMap.put(n, i);
      }
    }

    if (endMap.isEmpty()) return 1;

    int times = 1, result = nums.length - 1;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > times) {
        times = entry.getValue();
        result = endMap.get(entry.getKey()) - startMap.get(entry.getKey());
      } else if (entry.getValue() == times) {
        if (endMap.containsKey(entry.getKey())) {
          result = Math.min(result, endMap.get(entry.getKey()) - startMap.get(entry.getKey()));
        }
      }
    }

    return result + 1;
  }

  /**
   * https://leetcode.com/problems/max-area-of-island/description/
   */
  public int maxAreaOfIsland(int[][] grid) {
    int area = 0;
    Set<Integer> record = new HashSet<>();

    for (int i = 0; i < grid.length; i++) {
      int[] col = grid[i];
      for (int j = 0; j < col.length; j++) {
        int tag = getTag(i, j);
        if (col[j] > 0 && !record.contains(tag)) {
          record.add(tag);
          area = Math.max(area, getArea(grid, i, j, record));
        }
      }
    }

    return area;
  }

  private int getArea(int[][] grid, int x, int y, Set<Integer> set) {
    int area = 1;
    // top
    int tag = getTag(x, y - 1);
    if (y - 1 >= 0 && !set.contains(tag) && grid[x][y - 1] == 1) {
      set.add(tag);
      area += getArea(grid, x, y - 1, set);
    }
    // right
    tag = getTag(x + 1, y);
    if (x + 1 < grid.length && !set.contains(tag) && grid[x + 1][y] == 1) {
      set.add(tag);
      area += getArea(grid, x + 1, y, set);
    }
    // bottom
    tag = getTag(x, y + 1);
    if (y + 1 < grid[x].length && !set.contains(tag) && grid[x][y + 1] == 1) {
      set.add(tag);
      area += getArea(grid, x, y + 1, set);
    }
    // left
    tag = getTag(x - 1, y);
    if (x - 1 >= 0 && !set.contains(tag) && grid[x - 1][y] == 1) {
      set.add(tag);
      area += getArea(grid, x - 1, y, set);
    }
    return area;
  }

  private int getTag(int x, int y) {
    return x * 100 + y;
  }

  /**
   * https://leetcode.com/problems/can-place-flowers/description/
   */
  public boolean canPlaceFlowers(int[] flowerbed, int n) {
    int empty = 0, last = -1;

    for (int i = 0; i < flowerbed.length - 1; i++) {
      int val = flowerbed[i];

      if (last < 0) {
        if (val == 0 && flowerbed[i + 1] == 0) {
          val = 1;
          empty++;
        }
      } else {
        if (last == 0 && val == 0 && flowerbed[i + 1] == 0) {
          val = 1;
          empty++;
        }
      }

      if (empty >= n) return true;

      last = val;
    }

    if (last == 0 && flowerbed[flowerbed.length - 1] == 0) empty++;

    return empty >= n;
  }

  public boolean canPlaceFlowersBetter(int[] flowerbed, int n) {
    int empty = 0;
    for (int i = 0; i < flowerbed.length; i++) {
      if (flowerbed[i] == 1) {
        i += 1;
      } else if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) {
        i += 2;
      } else {
        empty++;
        i += 1;
      }
    }

    return empty >= n;
  }

  /**
   * https://leetcode.com/problems/minimum-index-sum-of-two-lists/description/
   */
  public String[] findRestaurant(String[] list1, String[] list2) {
    Map<String, Integer> map = new HashMap<>();
    List<String> res = new LinkedList<>();
    int minSum = Integer.MAX_VALUE;
    for (int i = 0; i < list1.length; i++) map.put(list1[i], i);
    for (int i = 0; i < list2.length; i++) {
      Integer j = map.get(list2[i]);
      if (j != null && i + j <= minSum) {
        if (i + j < minSum) {
          res.clear();
          minSum = i + j;
        }
        res.add(list2[i]);
      }
    }
    return res.toArray(new String[res.size()]);
  }

  /**
   * https://leetcode.com/problems/range-addition-ii/description/
   */
  public int maxCount(int m, int n, int[][] ops) {
    for (int[] sub : ops) {
      m = Math.min(sub[0], m);
      n = Math.min(sub[1], n);
    }

    return n * m;
  }

  /**
   * https://leetcode.com/problems/longest-harmonious-subsequence/description/
   */
  public int findLHS(int[] nums) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int n : nums) {
      map.put(n, map.getOrDefault(n, 0) + 1);
    }

    int[] key = Arrays.stream(nums).distinct().sorted().toArray();

    int last = key[0], max = 0;

    for (int n : key) {
      if (n - last == 1) {
        max = Math.max(map.get(last) + map.get(n), max);
      }
      last = n;
    }
    return max;
  }

  public int findLHSBetter(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Arrays.sort(nums);
    int current = nums[0];
    int count = 1;
    int previous = 0;
    int result = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == current) {
        count++;
      } else {
        if (previous > 0) {
          result = Math.max(result, previous + count);
        }
        if (nums[i] == current + 1) {
          previous = count;
        } else {
          previous = 0;
        }
        current = nums[i];
        count = 1;
      }
    }
    return previous > 0 ? Math.max(result, previous + count) : result;
  }

  /**
   * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/
   */
  public int findUnsortedSubarray(int[] nums) {
    int[] copy = Arrays.copyOf(nums, nums.length);

    Arrays.sort(nums);
    int l = 0, r = nums.length - 1;
    while (l < r) {

      if (copy[l] != nums[l] && copy[r] != nums[r]) break;

      if (copy[l] == nums[l]) {
        l++;
      }
      if (copy[r] == nums[r]) r--;
    }

    return l < r ? r - l + 1 : 0;
  }

  /**
   * https://leetcode.com/problems/distribute-candies/description/
   */
  public int distributeCandies(int[] candies) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i : candies) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    int result = 0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > 0) result++;
    }

    return Math.min(result, candies.length / 2);
  }

  /**
   * https://leetcode.com/problems/reshape-the-matrix/description/
   */
  public int[][] matrixReshape(int[][] nums, int r, int c) {
    int srcCount = 0;
    if (nums != null) {
      for (int[] row : nums) {
        srcCount += row.length;
      }
    }

    if (srcCount != r * c) return nums;

    int[][] result = new int[r][c];

    int i = 0, j = 0;

    for (int[] row : nums) {
      for (int e : row) {
        result[i][j] = e;
        j++;
        if (j == c) {
          i++;
          j = 0;
        }
      }
    }

    return result;
  }

  /**
   * https://leetcode.com/problems/array-partition-i/description/
   */
  public int arrayPairSum(int[] nums) {
    int result = 0;
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i += 2) {
      result += Math.min(nums[i], nums[i + 1]);
    }

    return result;
  }

  /**
   * 返回给定的数组中任意两个数字差为k的组合个数(数字不分先后，且相同组合算一组)。 冒泡
   */
  public int findPairs(int[] nums, int k) {

    if (nums == null || nums.length <= 1 || k < 0) return 0;

    int result = 0;
    HashMap<Integer, Integer> map = new HashMap<>(nums.length);

    for (int i : nums) {
      map.put(i, map.getOrDefault(i, 0) + 1);
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (k == 0) {
        if (entry.getValue() >= 2) result++;
      } else {
        if (map.containsKey(entry.getKey() + k)) result++;
      }
    }

    return result;
  }

  /**
   * https://leetcode.com/problems/relative-ranks/description/
   */
  public String[] findRelativeRanks(int[] nums) {
    String[] top3 = new String[] {"Gold Medal", "Silver Medal", "Bronze Medal"};

    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], i);
    }

    Arrays.sort(nums);
    String[] result = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      int index = map.get(n);
      int rate = nums.length - i - 1;
      if (rate < top3.length) {
        result[index] = top3[rate];
      } else {
        result[index] = String.valueOf(rate + 1);
      }
    }

    return result;
  }

  /**
   * https://leetcode.com/problems/keyboard-row/description/
   */
  public String[] findWords(String[] words) {
    String[] strs = {"QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM"};
    Map<Character, Integer> map = new HashMap<>(26);
    for (int i = 0; i < strs.length; i++) {
      for (char c : strs[i].toCharArray()) {
        map.put(c, i);//put <char, rowIndex> pair into the sortMap
      }
    }

    List<String> res = new LinkedList<>();
    for (String w : words) {
      if (w.equals("")) continue;
      int index = map.get(w.toUpperCase().charAt(0));
      for (char c : w.toUpperCase().toCharArray()) {
        if (map.get(c) != index) {
          index = -1; //don't need a boolean flag.
          break;
        }
      }
      if (index != -1) res.add(w);//if index != -1, this is a valid string
    }
    return res.toArray(new String[0]);
  }

  /**
   * https://leetcode.com/problems/next-greater-element-i/description/
   */
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int[] result = new int[nums1.length];

    for (int j = 0; j < nums1.length; j++) {
      int n = nums1[j];
      boolean found = false;
      int i = 0;
      for (; i < nums2.length; i++) {
        if (nums2[i] == n) found = true;
        if (found && nums2[i] > n) {
          result[j] = nums2[i];
          break;
        }
      }

      if (i == nums2.length) result[j] = -1;
    }

    return result;
  }

  /**
   * 统计0/1数组中连续数字1的最长值。
   */
  public int findMaxConsecutiveOnes(int[] nums) {
    int result = 0, t = 0;

    for (int n : nums) {
      if (n == 1) {
        t++;
        result = Math.max(result, t);
      } else {
        t = 0;
      }
    }

    return result;
  }

  /**
   * https://leetcode.com/problems/heaters/description/
   */
  public int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(heaters);
    int result = 0;

    for (int house : houses) {
      int index = Arrays.binarySearch(heaters, house);
      if (index < 0) index = ~index;

      int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
      int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

      result = Math.max(result, Math.min(dist1, dist2));
    }

    return result;
  }

  /**
   * https://leetcode.com/problems/island-perimeter/description/
   */
  public int islandPerimeter(int[][] grid) {
    int result = 0;
    for (int i = 0; i < grid.length; i++) {
      int[] subGrid = grid[i];

      for (int j = 0; j < subGrid.length; j++) {
        if (subGrid[j] == 1) {
          result += getIslandPerimeter(grid, i, j);
        }
      }
    }
    return result;
  }

  private int getIslandPerimeter(int[][] grid, int i, int j) {
    int iLen = grid.length, jLen = grid[i].length, result = 4;

    if (j > 0 && grid[i][j - 1] == 1) result--; // left

    if (i > 0 && grid[i - 1][j] == 1) result--; // top

    if (j + 1 < jLen && grid[i][j + 1] == 1) result--; // right

    if (i + 1 < iLen && grid[i + 1][j] == 1) result--; // bottom

    return result;
  }

  /**
   * https://leetcode.com/problems/poor-pigs/description/
   *
   * https://leetcode.com/problems/poor-pigs/discuss/94266
   */
  public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
    int pigs = 0;
    while (Math.pow(minutesToTest / minutesToDie + 1, pigs) < buckets) pigs++;
    return pigs;
  }

  /**
   * https://leetcode.com/problems/assign-cookies/description/
   */
  public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);

    int result = 0, i = 0, j = 0;

    for (; i < g.length; i++) {
      int size = g[i];
      while (j < s.length && s[j] < size) {
        j++;
      }

      if (s[j] >= size) result++;
      if (j >= s.length) break;
      j++;
    }

    return result;
  }

  /**
   * 数组的每次处理为数组中(n-1)个元素自增，经过m次处理之后元素中所有的数字都为x的最少处理次数为m。
   *
   * 这个可以通过数学处理方式： sum为原数组中数字的和, min 为原数组中最小的数字。 x * n = sum + m * (n - 1)
   * //因为每次(n-1)个元素自增，所以整体和增加每次为(n-1) 而x = min + m so : m  = sum - min * n;
   */
  public int minMoves(int[] nums) {
    // get min & sum
    int min = nums[0];
    int sum = 0;
    for (int n : nums) {
      if (n < min) min = n;
      sum += n;
    }

    return sum - min * nums.length;
  }

  /**
   * 找出长度为n的数组中[1..n]中未出现的数字(数组中每个数字最多出现两次)。
   */
  public List<Integer> findDisappearedNumbers(int[] nums) {
    Arrays.sort(nums);
    List<Integer> result = new ArrayList<>();
    int sum = 0, cur = 0;
    for (int i = 1; i <= nums.length; i++) {
      sum += i;
      int t = nums[i - 1];
      cur += t;

      if (sum != cur) {
        int index = result.indexOf(t);
        if (index >= 0) result.remove(index);
        result.add(i);
        cur = sum;
      }
    }

    return result;
  }

  /**
   * 因为1<=nums[n]<=n所有通过nums[nums[n] - 1] += n来使出现数字位置的数字大于n， 所以如果处理后的nums[n] <= n则(n + 1)不在数组中。
   */
  public List<Integer> findDisappearedNumbersBetter(int[] nums) {
    List<Integer> result = new ArrayList<>();
    int n = nums.length;

    for (int i = 0; i < n; i++) nums[(nums[i] - 1) % n] += n;
    for (int i = 0; i < n; i++) {
      if (nums[i] <= n) result.add(i + 1);
    }
    return result;
  }

  /**
   * https://leetcode.com/problems/number-of-boomerangs/description/
   */
  public int numberOfBoomerangs(int[][] points) {
    int result = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int[] i : points) {
      for (int[] j : points) {
        if (i == j) continue;

        int dist = (i[0] - j[0]) * (i[0] - j[0]) + (i[1] - j[1]) * (i[1] - j[1]);

        int pre = map.getOrDefault(dist, 0);
        result += 2 * pre;

        map.put(dist, pre + 1);
      }
      map.clear();
    }
    return result;
  }

  /**
   * https://leetcode.com/problems/string-compression/description/
   */
  public int compress(char[] chars) {
    int[] count = new int[127];

    char last = chars[0];
    StringBuilder sb = new StringBuilder();
    for (char c : chars) {
      if (c != last) {
        int index = sb.length();
        sb.append(last);
        if (count[last] > 1) sb.append(count[last]);

        for (; index < sb.length(); index++) {
          chars[index] = sb.charAt(index);
        }
        count[last] = 0;
      }
      count[c]++;
      last = c;
    }

    if (count[last] > 0) {
      int index = sb.length();
      sb.append(last);
      if (count[last] > 1) sb.append(count[last]);

      for (; index < sb.length(); index++) {
        chars[index] = sb.charAt(index);
      }
    }

    return sb.length();
  }

  /**
   * 返回数组中第三大的数，如果不足三个则返回最大值
   */
  public int thirdMax(int[] nums) {
    Integer a = nums[0], b = null, c = null;
    for (int n : nums) {
      if (n > a) {
        c = b;
        b = a;
        a = n;
      } else if ((b == null || n > b) && n != a) {
        c = b;
        b = n;
      } else if ((c == null || n > c) && b != null && n != b && n != a) {
        c = n;
      }
    }

    return c != null ? c : a;
  }

  /**
   * 从数组中找到两者元素相加和为给定的数字。
   */
  public int[] towSum(int[] nums, int target) {

    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      int temp = target - nums[i];
      if (map.containsKey(temp)) {
        return new int[] {map.get(temp), i};
      }
      map.put(nums[i], i);
    }
    return null;
  }

  // 数组为升序数组
  public int[] twoSum(int[] numbers, int target) {
    int l = 0, r = numbers.length - 1;
    while (l < r) {
      int t = numbers[l] + numbers[r];
      if (t == target) {
        return new int[] {l + 1, r + 1};
      } else if (t > target) {
        r--;
      } else if (t < target) {
        l++;
      }
    }
    return null;
  }

  /**
   * 字符数组最长公共前缀
   */
  public String longestCommonPrefix(String[] strs) {

    if (strs.length <= 0) return "";

    String result = strs[0];
    for (int i = 1; i < strs.length; i++) {
      String temp = strs[i];
      if (result.length() > temp.length()) {
        temp = result;
        result = strs[i];
      }

      if (temp.startsWith(result)) continue;

      for (int j = result.length() - 1; j >= 0; j--) {

        result = result.substring(0, j);

        if (temp.startsWith(result)) break;

        if (j == 0) return "";
      }
    }
    return result;
  }

  /**
   * 返回有序数组中有几个不同的数字 with O(1) extra memory。 因为不能再额外申请一个数组，所以只能对原来的数组进行调整使其前部分没有重复数字，函数返回没有重复数字部分的长度。
   */
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
      if (nums[i] != nums[j]) {
        i++;
        nums[i] = nums[j];
      }
    }

    return i + 1;
  }

  /**
   * 一个数组和一个数字，对数组进行调整使数组前部分不存在给定的数字，并返回这部分的长度。 O(1) extra memory。
   */
  public int removeElement(int[] nums, int val) {
    int i = 0;
    int j = nums.length - 1;
    while (i <= j) {
      if (nums[i] == val) {
        if (nums[j] == val) {
          j--;
          continue;
        }
        nums[i] = nums[j];
        nums[j] = val;
        continue;
      }
      i++;
    }

    return i;
  }

  /**
   * 在给定的升序数组中找到给定数字的索引，或者是其应该插入的位置。
   */
  public int searchInsert(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] >= target) return i;
    }

    return nums.length;
  }

  // 二分查找法
  public int searchInsertBetter(int[] nums, int target) {
    return binarySearch(nums, 0, nums.length - 1, target);
  }

  private int binarySearch(int[] nums, int l, int r, int target) {
    if (r >= l) {
      int mid = (l + r) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        return binarySearch(nums, mid + 1, r, target);
      } else {
        return binarySearch(nums, l, mid - 1, target);
      }
    }

    return r == l ? r : r + 1;
  }

  /**
   * 返回给定数组中连续值的最大和
   */
  public int maxSubArray(int[] nums) {

    int result = Integer.MIN_VALUE;
    int sum = 0;

    for (int num : nums) {
      if (sum < 0) {
        sum = num;
      } else {
        sum += num;
      }

      if (sum > result) result = sum;
    }

    return result;
  }

  /**
   * 数组每个数字代表一位，返回自增的结果
   */
  public int[] plusOne(int[] digits) {
    int carry = 1;
    for (int i = digits.length - 1; i >= 0; i--) {
      carry = digits[i] + carry;
      digits[i] = carry % 10;
      carry = carry / 10;
    }

    if (carry > 0) {
      int[] result = new int[digits.length + 1];
      result[0] = carry;
      System.arraycopy(digits, 0, result, 1, digits.length);

      return result;
    }
    return digits;
  }

  /**
   * 合并两个有序数组，num1有足够空间。
   *
   * @param nums1 有序数组1，也是合并之后的数组
   * @param m 原数组1的需要处理数字数量
   */
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    while (n > 0) {
      nums1[m + n - 1] = m > 0 && (nums1[m - 1] > nums2[n - 1]) ? nums1[--m] : nums2[--n];
    }
  }

  /**
   * 求前后数字的最大差值。
   */
  public int maxProfit(int[] prices) {
    if (prices.length < 2) return 0;
    int result = 0, min;
    min = prices[0];
    for (int i = 1; i < prices.length; i++) {
      int t = prices[i];
      if (t < min) {
        min = t;
      }

      int r = t - min;
      if (r > result) result = r;
    }

    return result;
  }

  public int maxProfitII(int[] prices) {
    if (prices.length < 2) return 0;
    int result = 0;

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) result += prices[i] - prices[i - 1];
    }

    return result;
  }

  /**
   * 给定数组中只有一个数字只出现一次，返回这个数字
   */
  public int singleNumber(int[] nums) {
    boolean found;
    int n = nums.length - 1;
    for (int i = 0; i <= n; i++) {
      int a = nums[i];
      found = false;
      for (int j = i + 1; j <= n; j++) {
        if (a == nums[j]) {
          nums[j] = nums[n];
          nums[n--] = a;
          found = true;
          break;
        }
      }

      if (found) continue;
      return a;
    }
    return 0;
  }

  // 使用异或
  public int singleNumberBetter(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int result = nums[0];
    for (int i = 1; i < nums.length; i++) {
      result ^= nums[i];
    }
    return result;
  }

  /**
   * 找到数组中出现次数超过len/2的元素（必定存在）
   */
  public int majorityElement(int[] nums) {
    int majority = nums[0], count = 1;
    for (int i = 1; i < nums.length; i++) {
      if (count == 0) {
        majority = nums[i];
        count++;
      } else if (majority == nums[i]) {
        count++;
      } else {
        count--;
      }
    }
    return majority;
  }

  /**
   * 数列元素向右平移k位
   */
  public void rotate(int[] nums, int k) {
    k %= nums.length;
    int l = 0, r = nums.length - 1;
    reverseArray(nums, l, r);
    reverseArray(nums, l, k - 1);
    reverseArray(nums, k, r);
  }

  private void reverseArray(int[] nums, int l, int r) {
    while (l < r) {
      swap(nums, l, r);
      l++;
      r--;
    }
  }

  private void swap(int[] nums, int a, int b) {
    int t = nums[a];
    nums[a] = nums[b];
    nums[b] = t;
  }

  /**
   * 正整数数列，不能取相邻数值，求最大和
   */
  public int rob(int[] nums) {
    int preY = 0, preN = 0, temp;
    for (int n : nums) {
      temp = preN;
      preN = Math.max(preN, preY + n);
      preY = temp;
    }
    return preN;
  }

  /**
   * 数组是否存在重复的数字
   */
  public boolean containsDuplicate(int[] nums) {

    Set<Integer> set = new HashSet<>();

    for (int n : nums) {
      if (set.contains(n)) return true;
      set.add(n);
    }

    return false;
  }

  public boolean containsDuplicateBetter(int[] nums) {
    // empty array or only one element
    if (nums == null || nums.length < 2) return false;
    // found max&min in array
    int max = nums[0], min = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (max < nums[i]) max = nums[i];
      if (min > nums[i]) min = nums[i];
    }

    // if the array's length is one more than the difference between the max & min, return true.
    if (max - min + 1 < nums.length) return true;

    boolean[] set = new boolean[max - min + 1];
    for (int n : nums) {
      if (set[n - min]) return true;
      set[n - min] = true;
    }
    return false;
  }

  /**
   * 数组中是否存在相同的元素，且距离不超过k
   */
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    // empty array or only one element
    if (nums == null || nums.length < 2) return false;
    Map<Integer, Integer> set = new HashMap<>(nums.length);

    for (int i = 0; i < nums.length; i++) {
      int n = nums[i];
      if (set.containsKey(n) && i - set.get(n) <= k) {
        return true;
      }
      set.put(n, i);
    }

    return false;
  }

  /**
   * 无序数组int[n]中存在不重复的0-n数字，求数组遗漏的数字
   */
  public int missingNumber(int[] nums) {
    int n = nums.length;
    if (n <= 0) return 0;
    n = (n + 1) * n / 2;

    for (int t : nums) {
      n -= t;
    }
    return n;
  }

  /**
   * 把数组中的0移动到数组末尾
   */
  public void moveZeroes(int[] nums) {
    int r = nums.length - 1;
    for (int i = r; i >= 0; i--) {
      if (nums[i] == 0) {
        if (i != r) {
          System.arraycopy(nums, i + 1, nums, i, r - i);
          nums[r] = 0;
        }
        r--;
      }
    }
  }

  /**
   * 找出两个数组中相同的元素（不重复）
   */
  public int[] intersection(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int n : nums1) {
      map.put(n, 1);
    }

    List<Integer> result = new ArrayList<>();
    for (int n : nums2) {
      if (map.containsKey(n) && map.get(n) == 1) {
        result.add(n);
        map.put(n, 2);
      }
    }

    int[] r = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      r[i] = result.get(i);
    }
    return r;
  }

  public int[] intersectionJava8(int[] nums1, int[] nums2) {
    return Arrays.stream(nums1).distinct()
        .filter(value -> Arrays.stream(nums2).distinct().anyMatch(value1 -> value == value1))
        .toArray();
  }

  /**
   * 找出两个数组中相同的元素（包括重复）
   */
  public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    if (nums1.length < nums2.length) {
      int[] temp = nums1;
      nums1 = nums2;
      nums2 = temp;
    }

    List<Integer> list = new ArrayList<>();
    int i = 0;
    for (int n : nums1) {
      for (int j = i; j < nums2.length; j++) {
        if (n == nums2[j]) {
          list.add(n);
          i = j + 1;
          break;
        }
        if (n < nums2[j]) break;
      }
    }

    int[] r = new int[list.size()];
    for (int j = 0; j < list.size(); j++) {
      r[j] = list.get(j);
    }
    return r;
  }
}
