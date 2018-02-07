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
    
    def myAtoi(self, str):
        """
        https://leetcode.com/problems/string-to-integer-atoi/description/

        :type str: str
        :rtype: int
        """
        if not str:
            return 0
        start, rate = -1, 0
        max, min = 2 ** 31 -1, -2 ** 31
        flag = False
        for i, s in enumerate(str):
            if s == '-' or s == '+':
                if start < 0 and rate == 0:
                    rate = -1 if s == '-' else 1
                else:
                    flag = True
                    break
            elif '0' <= s <= '9':
                if start < 0:
                    start = i
            else:
                if start >= 0 or rate !=0 or s != ' ':
                    flag = True
                    break
        if rate == 0:
            rate = 1
        result = rate * int(str[start: i if flag else i + 1]) if start >= 0 else 0
        if result > max:
            return max
        elif result < min:
            return min
        else:
            return result

    def myAtoiBetter(self, str):
        trigged_str = str.strip()
        multiplier = 1
        if trigged_str[0] == '-':
            multiplier = -1
            trigged_str = trigged_str[1:]
        elif trigged_str[0] == '+':
            trigged_str = trigged_str[1:]
        
        result = 0
        for s in trigged_str:
            if '0' <= ord(s) <= '9':
                result = result * 10 + ord(s)
            else:
                break
        result = multiplier * result
        max, min = 2 ** 31 -1, -2 ** 31
        if result > max:
            return max
        elif result < min:
            return min
        else:
            return result
    
    def convert(self, s, numRows):
        """
        https://leetcode.com/problems/zigzag-conversion/description/
        :type s: str
        :type numRows: int
        :rtype: str
        """
        if numRows < 2:
            return s

        result = ''
        for i in range(numRows):
            r1 = r2 = numRows * 2 - 2
            if i == 0 or i == numRows - 1:
                t = i
                while t < len(s):
                    result += s[t]
                    t += r1
            else:
                r1 = r1 - i * 2
                t = i
                while True:
                    if t < len(s):
                        result += s[t]
                    else:
                        break
                    if t + r1 < len(s):
                        result += s[t + r1]
                    else:
                        break
                    t += r2
        return result

    def convert_better(self, s, numRows):
        if numRows < 2:
            return s
        
        rate = numRows * 2 - 2
        l = [''] * numRows
        print(l)
        for i in range(len(s)):
            t = i % rate
            if t < numRows:
                l[t] += s[i]
            else:
                l[rate - t] += s[i]
        print(l)
        return ''.join(l)

if __name__ == '__main__':
    problem = StrProblem()
    print(problem.strStr('hello', 'll'))
    print(problem.convert_better('PAYPALISHIRING', 3))