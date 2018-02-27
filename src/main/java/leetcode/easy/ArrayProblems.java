package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayProblems {

  public static void main(String[] args) {
    ArrayProblems problems = new ArrayProblems();
    //System.out.println(problems.findPairs(
    //    new int[] {2, 9, 0, 8, 9, 6, 5, 9, 8, 1, 9, 6, 9, 2, 8, 8, 7, 5, 7, 8, 8, 3, 7, 4, 1, 1, 6,
    //        2, 9, 9, 3, 9, 2, 4, 6, 5, 6, 5, 1, 5, 9, 9, 8, 1, 4, 3, 2, 8, 5, 3, 5, 4, 5, 7, 0, 0,
    //        7, 6, 4, 7, 2, 4, 9, 3, 6, 6, 5, 0, 0, 0, 8, 9, 9, 6, 5, 4, 6, 2, 1, 3, 2, 5, 0, 1, 4,
    //        2, 6, 9, 5, 4, 9, 6, 0, 8, 3, 8, 0, 0, 2, 1}, 1))
    String[] list1 = new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"};
    String[] list2 =
        new String[] {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
    System.out.println(problems.findRestaurant(list1, list2));
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
        map.put(c, i);//put <char, rowIndex> pair into the map
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
