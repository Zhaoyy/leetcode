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

    def countAndSay(self, n):
        """
        https://leetcode.com/problems/count-and-say/description/

        :type n: int
        :rtype: str
        """
        l = []
        for i in range(n):
            if l:
                last, count, t = -1, 0, ''
                for s in l[i - 1]:
                    n = int(s)
                    if last < 0:
                        last = n
                        count = 1
                    elif last == n:
                        count += 1
                    else:
                        t += str(count) + str(last)
                        last = n
                        count = 1
                t += str(count) + str(last)
                l.append(t)
            else:
                l.append('1')
        return l[i]

if __name__ == "__main__":
    problem = IntProblem()
    print(problem.countAndSay(5))
