package leetcode.kotlin

class ArrayProblems {
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

fun main(args: Array<String>) {
  val problems = ArrayProblems()
  println(problems.maximumProduct(
      intArrayOf(722, 634, -504, -379, 163, -613, -842, -578, 750, 951, -158, 30, -238, -392, -487,
          -797, -157, -374, 999, -5, -521, -879, -858, 382, 626, 803, -347, 903, -205, 57, -342,
          186, -736, 17, 83, 726, -960, 343, -984, 937, -758, -122, 577, -595, -544, -559, 903,
          -183, 192, 825, 368, -674, 57, -959, 884, 29, -681, -339, 582, 969, -95, -455, -275, 205,
          -548, 79, 258, 35, 233, 203, 20, -936, 878, -868, -458, -882, 867, -664, -892, -687, 322,
          844, -745, 447, -909, -586, 69, -88, 88, 445, -553, -666, 130, -640, -918, -7, -420, -368,
          250, -786)))
}