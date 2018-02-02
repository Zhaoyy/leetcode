#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Author  : ZhaoYY
#pylint: disable=invalid-name

'''
problems about string.
'''

class StrProblem:
    '''
    string problems
    '''

    def longestCommonPrefixBetter(self, strs):
        '''
        :type strs: List[str]
        :rtype: str
        '''
        # *zip()函数能够把所有单词的第n个字母组成list，这样我们可以通过统计list元素相同的为共同前缀。
        zippped, ret = zip(*strs), ''
        for c in zippped:
            if len(set(c)) > 1:
                break
            ret += c[0]
        return ret

    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if not strs:
            return ''
        t = strs[0]
        for s in strs:
            l = self.__commonPrefix(t, s)
            if l < 0:
                return ''
            else:
                t = t[:l + 1]
        return t

    def __commonPrefix(self, s1, s2):
        '''
        :type s1: str
        :type s2: str
        :rtype: int
        '''
        l = len(s1) if len(s1) < len(s2) else len(s2)
        for i in range(l):
            if s1[i] != s2[i]:
                return i - 1
        return l - 1

    def testZip(self, strs):
        '''
        :type strs: List[str]
        :rtype: None
        '''
        zipped = zip(*strs)
        print(list(zipped))

    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        if not s or len(s) % 2 > 0 or s[0] == ')' or s[0] == '}' or s[0] == ']':
            return False
        l = []
        last = None
        for c in s:
            if l:
                last = l[-1]
            if last:
                if last == '(' and c == ')' or last == '[' and c == ']' or last == '{' and c == '}':
                    l.pop()
                else:
                    l.append(c)
            else:
                l.append(c)
        return not l

    def strStr(self, haystack, needle):
        """
        https://leetcode.com/problems/implement-strstr/description/

        The soltion with build-in function:
            return haystack.find(needle)

        :type haystack: str
        :type needle: str
        :rtype: int
        """
        if needle:
            ln = len(needle)
            limit = len(haystack) - ln
            for i, c in enumerate(haystack):
                if i > limit:
                    break
                if c == needle[0] and haystack[i:i + ln] == needle:
                    return i
            return -1
        return 0

if __name__ == '__main__':
    problem = StrProblem()
    print(problem.strStr('hello', 'll'))
