#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Author  : ZhaoYY
#pylint: disable=invalid-name

'''
problems about int.
'''

class IntProblem:
    '''
    int problems
    '''
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        if x > 0 and x % 10 == 0:
            return False
        t = 0
        z = x
        while x > 0:
            t = t * 10 + x % 10
            x //= 10
            if t == x:
                return True
        return t == z

if __name__ == "__main__":
    problem = IntProblem()
    print(problem.isPalindrome(121))
