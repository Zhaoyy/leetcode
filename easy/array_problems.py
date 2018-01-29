#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Author  : ZhaoYY

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
        if num < 0:
            flag = True
            num = -num
        result = 0
        while num > 0:
            result += num
            num = num / 10
        return -result if flag else result

if __name__ == '__main__':
    ARRAY_PROBLEM = ArrayProblem()
    print(ARRAY_PROBLEM.reverse(-19))
