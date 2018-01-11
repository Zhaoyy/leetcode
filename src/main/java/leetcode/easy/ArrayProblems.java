package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ArrayProblems {

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
