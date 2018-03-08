package leetcode.kotlin

import leetcode.easy.TreeNodeProblems.TreeNode

class IntProblems {

  /**
   * https://leetcode.com/problems/valid-palindrome-ii/description/
   */
  fun validPalindrome(s: String): Boolean {
    if (s.length < 2) return true
    val p = subValidPalindrome(s, 0, s.lastIndex)
    return if (p.first < p.second) {
      val p1 = subValidPalindrome(s, p.first + 1, p.second)
      if (p1.first < p1.second) {
        val p2 = subValidPalindrome(s, p.first, p.second - 1)
        p2.first >= p2.second
      } else {
        true
      }
    } else {
      true
    }
  }

  private fun subValidPalindrome(s: String, l: Int, r: Int): Pair<Int, Int> {
    var a = l
    var b = r
    while (a < b) {
      if (s[a] != s[b]) {
        break
      }
      a++
      b--
    }
    return Pair(a, b)
  }

  /**
   * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
   */

  private val set = hashSetOf<Int>()

  fun findTargetBetter(root: TreeNode?, k: Int): Boolean {
    if (root == null) return false
    val diff = k - root.`val`
    if (set.contains(diff)) return true
    set.add(root.`val`)
    return findTargetBetter(root.left, k) || findTargetBetter(root.right, k)
  }

  private val list = arrayListOf<Int>()

  fun findTarget(root: TreeNode?, k: Int): Boolean {
    buildList(root)

    if (list.size > 1 && (list[0] + list[1]) <= k && k <= (list[list.lastIndex] + list[list.lastIndex - 1])) {
      for (v in list) {
        val diff = k - v
        if (diff != v && list.contains(diff)) {
          return true
        }
      }
    }
    return false
  }

  private fun buildList(node: TreeNode?) {
    node?.let {
      node.left?.let {
        buildList(node.left)
      }

      list.add(node.`val`)

      node.right?.let {
        buildList(node.right)
      }
    }
  }

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

  /**
   * https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
   */
  fun averageOfLevels(root: TreeNode?): DoubleArray {
    return if (root == null) {
      doubleArrayOf()
    } else {
      countNodeWithLevel(root, 0)
      var index = 0
      val result: MutableList<Double> = ArrayList()
      while (levelCount.containsKey(index)) {
        result.add(levelSum[index]!! / levelCount[index]!!)
        index++
      }
      result.toDoubleArray()
    }
  }

  private val levelSum: MutableMap<Int, Double> = HashMap()
  private val levelCount: MutableMap<Int, Int> = HashMap()
  private fun countNodeWithLevel(node: TreeNode, level: Int) {
    levelSum.put(level, levelSum.getOrDefault(level, 0.toDouble()) + node.`val`)
    levelCount[level] = levelCount.getOrDefault(level, 0) + 1
    node.left?.apply {
      countNodeWithLevel(node.left, level + 1)
    }
    node.right?.apply {
      countNodeWithLevel(node.right, level + 1)
    }
  }
}