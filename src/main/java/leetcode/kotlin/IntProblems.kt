package leetcode.kotlin

class IntProblems {
  /**
   * https://leetcode.com/problems/sum-of-square-numbers/description/
   */
  fun judgeSquareSum(c: Int): Boolean {
    var l = 0
    var r = Math.sqrt(c.toDouble()).toInt()

    while (l <= r) {
      val t = l * l + r * r
      if (t == c) return true
      if (t > c) r--
      if (t < c) l++
    }
    return false
  }
}