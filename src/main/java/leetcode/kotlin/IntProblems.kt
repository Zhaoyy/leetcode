package leetcode.kotlin

import leetcode.easy.TreeNodeProblems.TreeNode

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