package leetcode.kotlin

import leetcode.easy.TreeNodeProblems.TreeNode

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

//  println(problems.findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))
//  println(problems.findErrorNumsBetter(intArrayOf(1, 2, 2, 4)).joinToString(", "))

  val p = IntProblems()
  val root = TreeNode(334)
  root.left = TreeNode(277)
  root.right = TreeNode(507)
  root.right.right = TreeNode(678)
  println(p.findTarget(root, 1014))
}

class ArrayProblems {

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