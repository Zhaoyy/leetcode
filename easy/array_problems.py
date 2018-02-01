#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# @Author  : ZhaoYY
#pylint: disable=invalid-name

"""
    problems about array.
"""


class ArrayProblem:
    """
    array problems
    """

    def two_sum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        temp_dict = {}
        for i, num in enumerate(nums):
            temp_dict[num] = i
            if target - num in temp_dict:
                return [temp_dict[target - num], i]

    @classmethod
    def reverse(cls, num):
        """
        :type x: int
        :rtype: int
        """
        flag = False
        if num < 0:
            flag = True
            num = -num
        result = 0
        while num > 0:
            result = result * 10 + num % 10
            num = num // 10
        if result > 2**31:
            result = 0

        return -result if flag else result

    def mergeTwoLists(self, l1, l2):
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        if not l1 or l2 and l1.val > l2.val:
            l1, l2 = l2, l1
        if l1:
            l1.next = self.mergeTwoLists(l1.next, l2)
        return l1

    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        i = 0
        for j in range(1, len(nums)):
            if nums[i] != nums[j]:
                i += 1
                nums[i] = nums[j]
        return i + 1
    def removeElement(self, nums, val):
        """
        https://leetcode.com/problems/remove-element/description/
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        if not nums:
            return 0
        l, r = 0, len(nums) - 1
        while l < r:
            if nums[r] == val:
                r -= 1
                continue
            if nums[l] != val:
                l += 1
                continue
            nums[l], nums[r] = nums[r], nums[l]
            r -= 1
            l += 1
        return l + 1

    def removeDuplicatesBetter(self, nums, val):
        '''
        :type nums: List[int]
        :type val: int
        :rtype: int
        '''
        pre = -1
        for _, n in enumerate(nums):
            if n != val:
                pre += 1
                nums[pre] = n
        return pre + 1

if __name__ == '__main__':
    ARRAY_PROBLEM = ArrayProblem()
    print(ARRAY_PROBLEM.reverse(-190))
