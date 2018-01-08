package leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class StringProblems {

  public static void main(String[] args) {
    StringProblems problems = new StringProblems();
    System.out.println(
        problems.countSegments("Of all the gin joints in all the towns in all the world,   "));
  }

  /**
   * 求给定字符串由空格间隔的部分数量
   */
  public int countSegments(String s) {
    if (s == null || s.length() == 0) return 0;
    int result = 0, last = s.charAt(0);
    for (char c : s.toCharArray()) {
      if (c == ' ' && last != ' ') result++;
      last = c;
    }

    if (last != ' ') result++;

    return result;
  }

  /**
   * 求两个数字字符串的和
   */
  public String addStrings(String num1, String num2) {
    if (num1.length() < num2.length()) {
      String t = num1;
      num1 = num2;
      num2 = t;
    }
    String result = "";
    boolean carry = false;
    int diff = num1.length() - num2.length();
    for (int i = num2.length() - 1; i >= 0; i--) {
      int a = num2.charAt(i) - '0';
      int b = num1.charAt(i + diff) - '0';
      int sum = a + b;
      if (carry) sum++;
      carry = sum >= 10;
      result = (sum % 10) + result;
    }

    for (int i = num1.length() - num2.length() - 1; i >= 0; i--) {
      int sum = num1.charAt(i) - '0';
      if (carry) sum++;
      carry = sum >= 10;
      result = (sum % 10) + result;
    }

    if (carry) result = '1' + result;
    return result;
  }

  /**
   * 求给定字符串中字母能够组成回文字符串的最大长度（包含大小写字母）。
   */
  public int longestPalindrome(String s) {
    int[] upper = new int[26];
    int[] lower = new int[26];

    for (char c : s.toCharArray()) {
      if (c <= 'Z') {
        upper[c - 'A']++;
      } else if (c <= 'z') lower[c - 'a']++;
    }

    int result = 0, extra = 0;
    for (int n : upper) {
      if (n > 0) result += n / 2;
      if (n % 2 > 0) extra = 1;
    }

    for (int n : lower) {
      if (n > 0) result += n / 2;
      if (n % 2 > 0 && extra == 0) extra = 1;
    }
    return result * 2 + extra;
  }

  /**
   * 判断由'(', ')', '{', '}', '[' and ']'组成的字符串是否匹配。 "()" and "()[]{}" are all valid but "(]" and
   * "([)]" are not
   */
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();

    for (char c : chars) {
      if (stack.empty()) {
        stack.push(c);
        continue;
      }

      char temp = stack.peek();
      int t = Math.abs(temp - c);
      if (t == 1 || t == 2) {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return stack.isEmpty();
  }

  public boolean isValidBetter(String s) {
    Stack<Character> stack = new Stack<>();
    char[] chars = s.toCharArray();

    for (char c : chars) {
      if (c == '(' || c == '[' || c == '{') { // 起始符必须入栈
        stack.push(c);
      } else if (stack.isEmpty()) { // 空栈时如果遇到结束符则不匹配
        return false;
      } else {
        char temp = stack.peek();  // ([])这种也算不匹配
        int t = Math.abs(temp - c);
        return t == 1 || t == 2;
      }
    }
    return false;
  }

  /**
   * 返回needle在haystack中第一次出现的位置，类似{@link String#indexOf(String)}
   */
  public int strStr(String haystack, String needle) {

    if (haystack.isEmpty() && needle.isEmpty()) return 0;

    int result = -1;
    for (int i = 0; i < haystack.length(); i++) {
      if (haystack.substring(i).startsWith(needle)) {
        result = i;
        break;
      }
    }

    return result;
  }

  /**
   * count and say problem
   */
  public String countAndSay(int n) {

    if (n <= 0) return "-1";

    StringBuilder sb = new StringBuilder("1");
    String pre;
    for (int i = 1; i < n; i++) {
      pre = sb.toString();
      sb.setLength(0);

      int count = 0;
      char temp = 0;
      for (int j = 0; j < pre.length(); j++) {
        if (count == 0) {
          temp = pre.charAt(j);
          count++;
        } else if (temp == pre.charAt(j)) {
          count++;
        } else {
          sb.append(count).append(temp);
          count = 1;
          temp = pre.charAt(j);
        }
      }

      if (count > 0) {
        sb.append(count).append(temp);
      }
    }

    return sb.toString();
  }

  /**
   * 返回给定英文语句中最后一个单词的长度，没有则返回0
   */
  public int lengthOfLastWord(String s) {
    if (s == null || s.isEmpty()) return 0;
    String[] ss = s.split(" ");
    return ss.length > 0 ? ss[ss.length - 1].length() : 0;
  }

  public int lengthOfLastWordBetter(String s) {
    if (s == null) return 0;
    s = s.trim();

    if (s.isEmpty()) return 0;

    int index = s.lastIndexOf(" ");
    if (index == -1) return s.length();
    return s.length() - index - 1;
  }

  /**
   * 返回给定两个二进制字符串的和
   */
  public String addBinary(String a, String b) {
    char[] result, as = a.toCharArray(), bs = b.toCharArray();
    int n, i = 1, flag = '1' * 2;
    int la = as.length, lb = bs.length;
    if (la > lb) {
      n = lb;
      result = new char[la + 1];
    } else {
      result = new char[lb + 1];
      n = la;
    }

    int lr = result.length, carry = 0;
    for (; i <= n; i++) {
      char ia = as[la - i];
      char ib = bs[lb - i];

      int sum = ia + ib + carry;

      if (sum >= flag) {
        result[lr - i] = sum > flag ? '1' : '0';
        carry = 1;
      } else {
        result[lr - i] = (char) (sum - '0');
        carry = 0;
      }
    }

    char[] need = la > n ? as : bs;

    flag = flag / 2;
    for (; i <= need.length; i++) {
      int sum = need[need.length - i] + carry;

      if (sum > flag) {
        carry = 1;
        result[lr - i] = '0';
      } else {
        result[lr - i] = (char) sum;
        carry = 0;
      }
    }

    result[0] = carry > 0 ? '1' : ' ';

    return String.valueOf(result).trim();
  }

  /**
   * 字符串中的数字和字母回文 (lc < 65 || lc > 90) && (lc < 97 || lc > 122) && (lc < 48 || lc > 57)
   */
  public boolean isPalindrome(String s) {
    int r = s.length() - 1, l = 0;
    while (l < r) {
      char lc = s.charAt(l);
      if (!isAlphanumeric(lc)) {
        l++;
        continue;
      }
      char rc = s.charAt(r);
      if (!isAlphanumeric(rc)) {
        r--;
        continue;
      }

      if (ignorCaseEqual(lc, rc)) {
        l++;
        r--;
      } else {
        return false;
      }
    }
    return true;
  }

  private boolean isAlphanumeric(char c) {
    return (c >= 65 && c <= 90) || (c >= 97 && c <= 122) || (c >= 48 && c <= 57);
  }

  private boolean ignorCaseEqual(char a, char b) {
    if ((a >= 48 && a <= 57 && b >= 48 && b <= 57)
        || (a >= 65 && a <= 90 && b >= 65 && b <= 90)
        || (a >= 97 && a <= 122 && b >= 97 && b <= 122)) {
      return a == b;
    } else if ((a >= 65 && a <= 90 && b >= 97 && b <= 122)) {
      return a - 65 == b - 97;
    } else if ((b >= 65 && b <= 90 && a >= 97 && a <= 122)) {
      return b - 65 == a - 97;
    }
    return false;
  }

  /**
   * 判断两个单词是否结构相同
   */
  public boolean isIsomorphic(String s, String t) {
    if (s == null || t == null || s.length() != t.length()) return false;
    Map<Integer, Integer> mapS = new HashMap<>();
    Map<Integer, Integer> mapT = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      int cS = s.charAt(i);
      int cT = t.charAt(i);
      if (!Objects.equals(mapS.get(cS), mapT.get(cT))) return false;

      mapS.put(cS, i);
      mapT.put(cT, i);
    }

    return true;
  }

  /**
   * 判断两个单词是否是相同字母词（由相同字母构成）
   */
  public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] map = new int[26];

    for (int i = 0; i < s.length(); i++) {
      char cS = s.charAt(i);
      char cT = t.charAt(i);
      //if (cS == cT) return false;
      map[cS - 'a']++;
      map[cT - 'a']--;
    }

    for (int n : map) {
      if (n != 0) return false;
    }

    return true;
  }

  /**
   * 判断str是否符合pattern的样式
   */
  public boolean wordPattern(String pattern, String str) {

    int patternCount = 0;
    int[] patternMap = new int[26];
    for (int i = 0; i < pattern.length(); i++) {
      int index = pattern.charAt(i) - 'a';
      if (patternMap[index] == 0) patternCount++;
      patternMap[index] += i + 1;
    }

    String[] words = str.split(" ");
    Map<String, Integer> map = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      if (map.containsKey(words[i])) {
        map.put(words[i], map.get(words[i]) + i + 1);
      } else {
        map.put(words[i], i + 1);
      }
    }

    if (map.entrySet().size() != patternCount) return false;

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      boolean found = false;
      for (int n : patternMap) {
        if (n == entry.getValue()) {
          found = true;
          break;
        }
      }

      if (!found) return false;
    }
    return true;
  }

  public String reverseString(String s) {
    if (s == null) return null;

    char[] ss = s.toCharArray();
    int l = 0, r = ss.length - 1;

    while (l < r) {
      char t = ss[l];
      ss[l] = ss[r];
      ss[r] = t;
      l++;
      r--;
    }

    return String.valueOf(ss);
  }

  public String reverseVowels(String s) {
    if (s == null) return null;

    char[] ss = s.toCharArray();
    int l = 0, r = ss.length - 1;

    while (l < r) {
      char cl = ss[l];
      if (!isVowel(cl)) {
        l++;
        continue;
      }

      char cr = ss[r];
      if (!isVowel(cr)) {
        r--;
        continue;
      }

      ss[l] = cr;
      ss[r] = cl;

      l++;
      r--;
    }

    return String.valueOf(ss);
  }

  private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E'
        || c == 'I' || c == 'O' || c == 'U';
  }

  /**
   * 判断magazine中的的字母能否组成ransomNote单词（每个字母只能使用一次）
   */
  public boolean canConstruct(String ransomNote, String magazine) {
    if (magazine.length() < ransomNote.length()) return false;
    char[] ransom = ransomNote.toCharArray();
    char[] mag = magazine.toCharArray();
    Arrays.sort(ransom);
    Arrays.sort(mag);
    int j = 0;
    for (int i = 0; i < ransom.length; i++) {
      char r = ransom[i];
      for (; j < mag.length; j++) {
        if (mag[j] == r) {
          j++;
          break;
        }
      }

      if ((ransom.length - i > mag.length - j + 1) || mag[j - 1] != r) return false;
    }

    return true;
  }

  public boolean canConstructBetter(String ransomNote, String magazine) {
    int[] map = new int[26];

    for (char c : magazine.toCharArray()) map[c - 'a']++;
    for (char c : ransomNote.toCharArray()) {
      if (--map[c - 'a'] < 0) return false;
    }
    return true;
  }

  /**
   * 返回字符串中第一个不重复的字母索引（没有返回-1）
   */
  public int firstUniqChar(String s) {
    int[] map = new int[26];

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int index = c - 'a';
      if (map[index] > 0) {
        map[index] = Integer.MAX_VALUE;
      } else {
        map[index] = i + 1;
      }
    }

    Arrays.sort(map);

    for (int index : map) {
      if (index > 0) return index - 1;
    }
    return -1;
  }

  public int firstUniqCharBetter(String s) {
    int result = s.length();
    for (char c = 'a'; c <= 'z'; c++) {
      if (s.indexOf(c) > -1 && s.indexOf(c) == s.lastIndexOf(c)) {
        result = Math.min(s.indexOf(c), result);
      }
    }

    return result == s.length() ? -1 : result;
  }

  /**
   * t是由s中的字母混排之后又随即添加了一个字母组成，返回这个字母
   */
  public char findTheDifference(String s, String t) {
    int[] map = new int[26];
    for (char c : s.toCharArray()) map[c - 'a']++;
    for (char c : t.toCharArray()) map[c - 'a']--;
    for (int i = 0; i < map.length; i++) {
      if (map[i] < 0) return (char) ('a' + i);
    }
    return ' ';
  }

  /**
   * 异或
   */
  public char findTheDifferenceBetter(String s, String t) {
    int result = 0;
    for (int i = 0; i < s.length(); i++) {
      result = result ^ s.charAt(i) ^ t.charAt(i);
    }

    return (char) (result ^ t.charAt(s.length()));
  }
}
