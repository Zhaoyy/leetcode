package leetcode.kotlin

import java.lang.StringBuilder
import kotlin.math.max

fun main(args: Array<String>) {
  val problems = ArrayProblems()
//  println(problems.maximumProduct(
//      intArrayOf(722, 634, -504, -379, 163, -613, -842, -578, 750, 951, -158, 30, -238, -392, -487,
//          -797, -157, -374, 999, -5, -521, -879, -858, 382, 626, 803, -347, 903, -205, 57, -342,
//          186, -736, 17, 83, 726, -960, 343, -984, 937, -758, -122, 577, -595, -544, -559, 903,
//          -183, 192, 825, 368, -674, 57, -959, 884, 29, -681, -339, 582, 969, -95, -455, -275, 205,
//          -548, 79, 258, 35, 233, 203, 20, -936, 878, -868, -458, -882, 867, -664, -892, -687, 322,
//          844, -745, 447, -909, -586, 69, -88, 88, 445, -553, -666, 130, -640, -918, -7, -420, -368,
//          250, -786)))

//  println(problems.findLengthOfLCIS(intArrayOf(1, 3, 5, 4, 7)))
//  println(problems.findErrorNumsBetter(intArrayOf(1, 2, 2, 4)).joinToString(", "))
  println(problems.letterCasePermutationDTS("a1b2"))

  val p = IntProblems()
//  println(p.validPalindrome("abc"))
//  val root = TreeNode(334)
//  root.left = TreeNode(277)
//  root.right = TreeNode(507)
//  root.right.right = TreeNode(678)
//  println(p.findTarget(root, 1014))
}

class ArrayProblems {

  /**
   * https://leetcode.com/problems/rotate-string/description/
   */
  fun rotateString(A: String, B: String): Boolean {
    if (A.length != B.length) return false
    val sb = StringBuilder()
    for (i in B.indices) {
      if (B[i] == A[0]) {
        sb.setLength(0)
        sb.append(B.substring(i, B.length)).append(B.substring(0, i))
        if (sb.toString() == A) return true
      }
    }
    return false
  }

  /**
   * https://leetcode.com/problems/letter-case-permutation/description/
   */

  fun letterCasePermutationDTS(S: String): List<String> {
    val result = ArrayList<String>()
    dfsHelper(S, result, 0)
    return result
  }

  private fun dfsHelper(s: String, list: MutableList<String>, pos: Int) {
    if (s.length == pos) {
      list.add(s)
      return
    }
    if (s[pos] > '9') {
      val chars = s.toCharArray()
      chars[pos] = chars[pos].toLowerCase()
      dfsHelper(String(chars), list, pos + 1)
      chars[pos] = chars[pos].toUpperCase()
      dfsHelper(String(chars), list, pos + 1)
    } else {
      dfsHelper(s, list, pos + 1)
    }
  }

  fun letterCasePermutation(S: String): List<String> {
    val result = ArrayList<String>()
    result.add(S)
    val buffer = StringBuilder()
    for (i in S.indices) {

      if (S[i] > '9') {
        val p = letterPermutation(S[i])
        val n = result.lastIndex
        for (j in 0..n) {
          buffer.setLength(0)
          buffer.append(result[j])
          buffer.setCharAt(i, p)
          result.add(buffer.toString())
        }
      }

    }

    return result
  }

  private fun letterPermutation(c: Char): Char {
    return if (c in 'a'..'z') {
      'A' + (c - 'a')
    } else {
      'a' + (c - 'A')
    }
  }

  /**
   * https://leetcode.com/problems/repeated-string-match/description/
   */
  fun repeatedStringMatch(A: String, B: String): Int {
    val sb = StringBuilder(A)
    for (r in 1..(B.length / A.length + 2)) {
      if (sb.contains(B)) {
        return r
      }
      sb.append(A)
    }
    return -1
  }

  /**
   * https://leetcode.com/problems/baseball-game/description/
   */
  fun calPoints(ops: Array<String>): Int {
    val rPoints = ArrayList<Int>()

    for (p in ops) {
      when (p) {
        "+" -> {
          val lastIndex = rPoints.lastIndex
          rPoints.add(rPoints[lastIndex] + rPoints[lastIndex - 1])
        }
        "D" -> {
          rPoints.add(rPoints[rPoints.lastIndex] * 2)
        }
        "C" -> {
          rPoints.removeAt(rPoints.lastIndex)
        }
        else -> rPoints.add(p.toInt())
      }
    }

    return rPoints.sum()
  }

  /**
   * https://leetcode.com/problems/longest-continuous-increasing-subsequence/description/
   */
  fun findLengthOfLCIS(nums: IntArray): Int {
    if (nums.size < 2) return nums.size
    var result = 0
    var count = 1
    var last = nums[0]
    for (i in 1..nums.lastIndex) {
      if (nums[i] > last) {
        count++
      } else {
        result = max(count, result)
        count = 1
      }
      last = nums[i]
    }

    return max(count, result)
  }

  /**
   * https://leetcode.com/problems/non-decreasing-array/description/
   */
  fun checkPossibility(nums: IntArray): Boolean {

    if (nums.isEmpty() || nums.size == 1 || nums.size == 2) {
      return true
    }

    var found = false
    var i = 0
    while (i < nums.lastIndex) {
      if (nums[i] > nums[i + 1]) {
        if (found) return false
        else {
          found = true
          if (i > 0 && nums[i + 1] < nums[i - 1]) {
            nums[i + 1] = nums[i]
          }
        }
      }
      i++
    }

    return true
  }

  /**
   * https://leetcode.com/problems/image-smoother/description/
   */
  fun imageSmoother(M: Array<IntArray>): Array<IntArray> {
    val result = Array(M.size, { i -> IntArray(M[i].size) })
    val t = arrayOf(-1, 0, 1)
    for (x in M.indices) {
      for (y in M[x].indices) {
        var sum = 0
        var count = 0
        for (tx in t) {
          for (ty in t) {
            val a = x + tx
            val b = y + ty
            if (a >= 0 && b >= 0 && a < M.size && b < M[x].size) {
              sum += M[a][b]
              count++
            }
          }
        }

        if (count > 0) {
          result[x][y] = sum / count
        }
      }
    }
    return result
  }

  /**
   * https://leetcode.com/problems/judge-route-circle/description/
   */
  fun judgeCircle(moves: String): Boolean {
    var x = 0
    var y = 0
    moves.forEach {
      when (it) {
        'L' -> x--
        'R' -> x++
        'U' -> y++
        'D' -> y--
      }
    }

    return x == 0 && y == 0
  }

  /**
   * https://leetcode.com/problems/set-mismatch/description/
   */

  fun findErrorNumsBetter(nums: IntArray): IntArray {
    val result = IntArray(2)
    val extraArray = IntArray(nums.size + 1)
    for (n in nums) {
      if (extraArray[n] == 1) {
        result[0] = n
      } else {
        extraArray[n] = 1
      }
    }

    for (i in 1 until extraArray.size) {
      if (extraArray[i] == 0) {
        result[1] = i
        break
      }
    }

    return result
  }

  fun findErrorNums(nums: IntArray): IntArray {
    var extra = 0
    var miss = 0
    nums.sort()
    for (i in 0 until nums.lastIndex) {

      if (miss > 0 && extra > 0) break

      if (miss == 0 && nums[i] != i + 1 && nums[i + 1] != i + 1) {
        miss = i + 1
      }

      if (extra == 0 && nums[i] == nums[i + 1]) {
        extra = nums[i]
      }
    }

    return intArrayOf(extra, if (miss > 0) miss else nums.size)
  }

  /**
   * https://leetcode.com/problems/maximum-average-subarray-i/description/
   */
  fun findMaxAverage(nums: IntArray, k: Int): Double {
    var lastSum = (0 until k).sumBy { nums[it] }
    var maxSum = lastSum
    for (i in 1..nums.size - k) {
      lastSum = lastSum - nums[i - 1] + nums[i + k - 1]
      maxSum = Math.max(maxSum, lastSum)
    }

    return maxSum.toDouble() / k
  }

  /**
   * https://leetcode.com/problems/maximum-product-of-three-numbers/description/
   */
  fun maximumProduct(nums: IntArray): Int {
    nums.sort()
    val last = nums.lastIndex
    val t = nums[last - 1] * nums[last] * nums[last - 2]
    val h = nums[0] * nums[1] * nums[last]
    return if (t > h) {
      t
    } else {
      h
    }
  }
}