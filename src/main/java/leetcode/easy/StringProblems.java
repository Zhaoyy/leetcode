package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class StringProblems {

  /**
   * https://leetcode.com/problems/unique-morse-code-words/description/
   */
  private String[] morse =
      new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
          ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--",
          "-..-", "-.--", "--.."};

  public static void main(String[] args) {
    StringProblems problems = new StringProblems();
    //System.out.println(
    //    problems.reverseStr(
    //        "hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl",
    //        39));
    //System.out.println(
    //    problems.findMinDifference(Arrays.asList("01:01", "02:01", "03:00", "03:01")));
    //problems.customSortString("cba", "abcd");
    System.out.println(problems.minAddToMakeValid("()))(("));
  }

  /**
   * https://leetcode.com/problems/string-without-aaa-or-bbb/
   */
  public String strWithout3a3b(int A, int B) {
    int len = A + B, max = A, min = B;
    char maxC = 'a', minC = 'b';
    StringBuilder sb = new StringBuilder(len);

    if (A < B) {
      max = B;
      min = A;
      maxC = 'b';
      minC = 'a';
    }

    while (max > 0) {
      sb.append(maxC);
      max--;
    }

    int index = 2;
    while (min > 0) {
      if (index >= sb.length()) index = 0;
      sb.insert(index, minC);
      min--;
      index += 3;
    }

    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/partition-labels/
   */
  public List<Integer> partitionLabels(String S) {
    List<Integer> list = new ArrayList<>();

    if (S == null || S.length() == 0) {
      return list;
    }

    int[] hash = new int[26];

    char[] sc = S.toCharArray();

    for (int i = 0; i < sc.length; ++i) {
      hash[sc[i] - 'a'] = i;
    }

    int start = 0;
    int end = 0;

    for (int i = 0; i < sc.length; ++i) {
      char c = sc[i];
      end = Math.max(end, hash[c - 'a']);
      if (end == i) {
        list.add(end + 1 - start);
        start = end + 1;
        end++;
      }
    }

    return list;
  }

  /**
   * https://leetcode.com/problems/to-lower-case/description/
   */
  public String toLowerCase(String str) {
    StringBuilder sb = new StringBuilder(str.length());

    for (char c : str.toCharArray()) {
      if (c >= 'A' && c <= 'Z') {
        sb.append((char) (c + 32));
      } else {
        sb.append(c);
      }
    }

    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/reorder-log-files/description/
   */
  public String[] reorderLogFiles(String[] logs) {
    Arrays.sort(logs, (o1, o2) -> {

      String[] words1 = o1.split(" ");
      String[] words2 = o2.split(" ");

      boolean isLetter1 = Character.isLetter(words1[1].charAt(0));
      boolean isLetter2 = Character.isLetter(words2[1].charAt(0));

      int index1 = o1.indexOf(" ");
      int index2 = o2.indexOf(" ");

      if (isLetter1 && isLetter2) {
        return o1.substring(index1 + 1).compareTo(o2.substring(index2 + 1));
      } else if (isLetter1 != isLetter2) {
        return isLetter1 ? -1 : 1;
      }

      return 0;
    });
    return logs;
  }

  /**
   * https://leetcode.com/problems/unique-email-addresses/description/
   */
  public int numUniqueEmails(String[] emails) {
    Set<String> set = new HashSet<>();

    for (String s : emails) {
      int at = s.indexOf('@');
      int plus = s.indexOf('+');
      String real = s.substring(0, plus > 0 && plus < at ? plus : at).replaceAll("\\.", "");
      set.add(real + s.substring(at));
    }

    return set.size();
  }

  /**
   * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/
   */
  public int minAddToMakeValid(String S) {
    if (S.isEmpty()) return 0;
    Stack<Character> stack = new Stack<>();

    for (char c : S.toCharArray()) {
      if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    return stack.size();
  }

  /**
   * https://leetcode.com/problems/flip-string-to-monotone-increasing/discuss/183874/O(n)-Java-with-O(1)-space
   */
  public int minFlipsMonoIncr(String S) {
    int r0 = 0, l1 = 0;
    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == '0') {
        r0++;
      }
    }
    int res = r0;
    for (int j = 0; j < S.length(); j++) {
      if (S.charAt(j) == '0') {
        r0--;
      } else {
        l1++;
      }
      res = Math.min(l1 + r0, res);
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/long-pressed-name/description/
   */
  public boolean isLongPressedName(String name, String typed) {
    if (name.length() > typed.length()) return false;
    if (name.length() == typed.length()) return name.equals(typed);

    int n = 0, t = 0;

    while (n < name.length() && t < typed.length()) {

      if (name.charAt(n) != typed.charAt(t)) {

        if (n == 0) return false;

        if (typed.charAt(t) == name.charAt(n - 1)) {
          t++;
        } else {
          return false;
        }
      } else {
        n++;
        t++;
      }
    }

    if (n < name.length()) return false;

    while (t < typed.length()) {
      if (typed.charAt(t) != name.charAt(n - 1)) return false;
      t++;
    }

    return true;
  }

  public boolean isLongPressedNameBetter(String name, String typed) {
    int difference = 0;
    for (int i = 0; i < typed.length(); ) {
      //letters are equal -> go next
      if (difference <= i && i - difference < name.length() && typed.charAt(i) == name.charAt(
          i - difference)) {
        i++;
      } else if (difference < i
          && i - difference - 1 < name.length()
          && typed.charAt(i) == name.charAt(i - difference - 1)) {
        // letters are not equal,  but we can link typed letter to name letter from the previous iteration
        difference++;
      } else {
        return false;
      }
    }
    // check that at the end of name there's no odd symbols
    return typed.length() - difference == name.length();
  }

  /**
   * https://leetcode.com/problems/reverse-only-letters/description/
   */
  public String reverseOnlyLetters(String S) {
    char[] chars = S.toCharArray();

    int l = 0, r = S.length() - 1;

    while (l < r) {
      if (!Character.isAlphabetic(chars[l])) {
        l++;
        continue;
      }

      if (!Character.isAlphabetic(chars[r])) {
        r--;
        continue;
      }

      char temp = chars[l];
      chars[l] = chars[r];
      chars[r] = temp;
      l++;
      r--;
    }

    return String.valueOf(chars);
  }

  /**
   * https://leetcode.com/problems/fraction-addition-and-subtraction/description/
   */
  public String fractionAddition(String expression) {
    int denominator = 5 * 7 * 8 * 9;
    long numerator = 0L;
    int rate = 1;
    int x = 0;
    int y = 0;
    boolean flag = false;
    for (char ch : expression.toCharArray()) {
      switch (ch) {
        case '+':
        case '-':
          if (y == 0) {
            y = 1;
          }
          numerator += denominator * rate * x / y;
          rate = ch == '+' ? 1 : -1;
          x = 0;
          y = 0;
          flag = false;
          break;
        case '/':
          flag = true;
          break;
        default:
          if (flag) {
            y *= 10;
            y += ch - '0';
          } else {
            x *= 10;
            x += ch - '0';
          }
      }
    }
    if (y == 0) {
      y = 1;
    }
    numerator += denominator * rate * x / y;
    for (int i = 9; i > 1; i--) {
      if ((denominator % i == 0) && (numerator % i) == 0) {
        denominator /= i;
        numerator /= i;
      }
    }
    return String.valueOf(numerator) + "/" + denominator;
  }

  /**
   * https://leetcode.com/problems/delete-operation-for-two-strings/description/
   */
  public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i <= word1.length(); i++) {
      dp[i][0] = i;
    }
    for (int i = 0; i <= word2.length(); i++) {
      dp[0][i] = i;
    }

    for (int i = 0; i < word1.length(); i++) {
      for (int j = 0; j < word2.length(); j++) {
        if (word1.charAt(i) == word2.charAt(j)) {
          dp[i + 1][j + 1] = dp[i][j];
        } else {
          dp[i + 1][j + 1] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
        }
      }
    }

    return dp[word1.length()][word2.length()];
  }

  /**
   * https://leetcode.com/problems/permutation-in-string/description/
   */
  public boolean checkInclusion(String s1, String s2) {
    Map<Character, Integer> map = new HashMap<>(26);
    for (char c : s1.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int len = s2.length() - s1.length();
    for (int i = 0; i <= len; i++) {
      if (checkInclusionHelper(s2, map, i, s1.length())) return true;
    }

    return false;
  }

  private boolean checkInclusionHelper(String s, Map<Character, Integer> map, int index, int len) {

    Map<Character, Integer> temp = new HashMap<>(map);

    for (int i = index; i < index + len; i++) {
      char c = s.charAt(i);
      if (temp.containsKey(c)) {
        if (temp.get(c) == 0) return false;
        temp.put(c, temp.get(c) - 1);
      } else {
        return false;
      }
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/optimal-division/description/
   */
  public String optimalDivision(int[] nums) {
    int n = nums.length;
    if (n == 0) return "";
    if (n == 1) return String.valueOf(nums[0]);// one num
    if (n == 2) return String.valueOf(nums[0] + "/" + nums[1]);// two nums
    StringBuilder sb = new StringBuilder(nums[0] + "");
    sb.append("/(").append(nums[1]);
    for (int i = 2; i < n; i++) { //n >= 3, res = x1 /(x2/x3/x4/.../xn)
      sb.append("/").append(nums[i]);
    }
    sb.append(")");
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/minimum-time-difference/description/
   */
  public int findMinDifference(List<String> timePoints) {

    int day = 1440;

    Integer[] mins = timePoints.stream().map(s -> {
      String[] v = s.split(":");
      return Integer.valueOf(v[0]) * 60 + Integer.valueOf(v[1]);
    }).sorted().toArray(Integer[]::new);

    int ans = Integer.MAX_VALUE;
    int last = mins[0];

    for (int i = 1; i < mins.length; i++) {
      ans = Math.min(mins[i] - last, ans);
      ans = Math.min(last - mins[i] + day, ans);
      last = mins[i];
    }

    ans = Math.min(mins[0] + day - mins[mins.length - 1], ans);

    return ans;
  }

  /**
   * https://leetcode.com/problems/complex-number-multiplication/description/
   */
  public String complexNumberMultiply(String a, String b) {
    int i1 = Integer.valueOf(a.substring(0, a.indexOf('+')));
    int i2 = Integer.valueOf(a.substring(a.indexOf('+') + 1, a.length() - 1));

    int j1 = Integer.valueOf(b.substring(0, b.indexOf('+')));
    int j2 = Integer.valueOf(b.substring(b.indexOf('+') + 1, b.length() - 1));

    return String.format("%1$d+%2$di", (i1 * j1 - i2 * j2), (i1 * j2 + i2 * j1));
  }

  /**
   * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/
   */
  public String findLongestWord(String s, List<String> d) {
    d.sort((a, b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length())
        : a.compareTo(b));
    for (String dictWord : d) {
      int i = 0;
      for (char c : s.toCharArray())
        if (i < dictWord.length() && c == dictWord.charAt(i)) i++;
      if (i == dictWord.length()) return dictWord;
    }
    return "";
  }

  /**
   * https://leetcode.com/problems/groups-of-special-equivalent-strings/description/
   */
  public int numSpecialEquivGroups(String[] A) {
    Set<String> set = new HashSet<>();
    for (String s : A) {
      int[] map1 = new int[256];
      int[] map2 = new int[256];
      for (int i = 0; i < s.length(); i++) {
        if (i % 2 == 0) {
          map1[s.charAt(i)]++;
        } else {
          map2[s.charAt(i)]++;
        }
      }
      set.add(Arrays.toString(map1) + " " + Arrays.toString(map2));
    }
    return set.size();
  }

  /**
   * https://leetcode.com/problems/longest-palindromic-subsequence/description/
   */
  public int longestPalindromeSubseq(String s) {

    return longestPalindromeSubSqyHelper(s, 0, s.length() - 1);
  }

  private int longestPalindromeSubSqyHelper(String s, int l, int r) {
    if (l == r) return 1;
    if (l > r) return 0;

    return s.charAt(l) == s.charAt(r) ? 2 + longestPalindromeSubSqyHelper(s, l + 1, r - 1)
        : Math.max(longestPalindromeSubSqyHelper(s, l + 1, r),
            longestPalindromeSubSqyHelper(s, l, r - 1));
  }

  public int longestPalindromeSubSquBetter(String s) {
    int len = s.length();
    int[] dp = new int[len];
    char[] input = s.toCharArray();
    Arrays.fill(dp, 1); //a single char is count as a palindrome with length 1
    for (int i = 0; i < len; i++) {
      //i and j start with difference as 1, hence no character between them initially(curLong==0)
      int curLong = 0;
      for (int j = i - 1; j >= 0; j--) {
        int temp = dp[j];
        if (input[j] == input[i]) {
          dp[j] = curLong + 2;
        }
        curLong = Math.max(curLong, temp);
      }
    }
    int max = 0;
    for (int n : dp) max = Math.max(max, n);
    return max;
  }

  /**
   * https://leetcode.com/problems/uncommon-words-from-two-sentences/description/
   */
  public String[] uncommonFromSentences(String A, String B) {
    String[] sA = A.split(" ");
    String[] sB = B.split(" ");
    Map<String, Integer> map = new HashMap<>();
    for (String s : sA) {
      map.put(s, map.getOrDefault(s, 0) + 1);
    }

    for (String s : sB) {
      map.put(s, map.getOrDefault(s, 0) + 1);
    }

    return map.entrySet()
        .stream()
        .filter(entry -> entry.getValue() == 1)
        .map(Map.Entry::getKey)
        .toArray(String[]::new);
  }

  /**
   * https://leetcode.com/problems/buddy-strings/description/
   */
  public boolean buddyStrings(String A, String B) {
    if (A.length() != B.length() || A.length() < 2) return false;
    if (A.equals(B)) {
      if (A.length() > 26) return true;
      boolean[] found = new boolean[128];
      for (char c : A.toCharArray()) {
        if (found[c]) {
          return true;
        } else {
          found[c] = true;
        }
      }
      return false;
    }
    int first = -1, second = -1;
    char[] aa = A.toCharArray(), bb = B.toCharArray();
    for (int i = 0; i < aa.length; i++) {
      if (aa[i] != bb[i]) {
        if (first == -1) {
          first = i;
        } else if (second == -1) {
          second = i;
        } else {
          return false;
        }
      }
    }
    return second != -1 && aa[first] == bb[second] && aa[second] == bb[first];
  }

  /**
   * https://leetcode.com/problems/backspace-string-compare/description/
   */
  public boolean backspaceCompare(String S, String T) {
    return S != null && backspaceHelper(S).equals(backspaceHelper(T));
  }

  private String backspaceHelper(String s) {
    StringBuilder sb = new StringBuilder();

    for (char c : s.toCharArray()) {
      if (c == '#') {
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
      } else {
        sb.append(c);
      }
    }
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/positions-of-large-groups/description/
   */
  public List<List<Integer>> largeGroupPositions(String S) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> t = new ArrayList<>();
    int last = -1;
    char lastChar = '0';
    for (int i = 0; i < S.length(); i++) {
      if (i == 0) {
        last = 0;
        lastChar = S.charAt(i);
      } else if (lastChar != S.charAt(i)) {
        if (i - last >= 3) {
          t.add(last);
          t.add(i - 1);
          ans.add(new ArrayList<>(t));
          t.clear();
        }
        last = i;
        lastChar = S.charAt(i);
      }
    }

    if (S.length() - last >= 3) {
      t.add(last);
      t.add(S.length() - 1);
      ans.add(new ArrayList<>(t));
    }
    return ans;
  }

  /**
   * https://leetcode.com/problems/goat-latin/description/
   */
  public String toGoatLatin(String S) {
    String[] words = S.split(" ");
    String extra = "ma";
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      char first = word.charAt(0);
      if (first == 'a' || first == 'A'
          || first == 'e' || first == 'E'
          || first == 'i' || first == 'I'
          || first == 'o' || first == 'O'
          || first == 'u' || first == 'U') {
        sb.append(word).append(extra);
      } else {
        sb.append(word.substring(1)).append(first).append(extra);
      }

      for (int j = 0; j <= i; j++) {
        sb.append('a');
      }
      sb.append(' ');
    }
    if (sb.length() > 0) sb.setLength(sb.length() - 1);
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/reorganize-string/description/
   */
  public String reorganizeString(String S) {

    int N = S.length();
    int[] counts = new int[26];
    for (char ch : S.toCharArray()) counts[ch - 'a'] += 100;
    for (int i = 0; i < 26; i++) counts[i] += i;
    Arrays.sort(counts);

    char[] ans = new char[N];
    int t = 1;
    for (int code : counts) {
      int ct = code / 100; // actual count
      char ch = (char) ('a' + code % 100); // actual letter
      if (ct > (N + 1) / 2) return "";
      for (int i = 0; i < ct; i++) {
        if (t >= N) t = 0;
        ans[t] = ch;
        t += 2;
      }
    }
    return String.valueOf(ans);
  }

  /**
   * https://leetcode.com/problems/custom-sort-string/description/
   */
  public String customSortString(String S, String T) {
    Map<Character, Integer> sortMap = new HashMap<>(26);
    for (int i = 0; i < S.length(); i++) {
      sortMap.put(S.charAt(i), i);
    }

    Character[] temp = new Character[T.length()];
    for (int i = 0; i < T.length(); i++) {
      temp[i] = T.charAt(i);
    }
    Arrays.sort(temp, new Comparator<Character>() {
      int t = 26;

      @Override public int compare(Character o1, Character o2) {
        return sortMap.getOrDefault(o1, t++) - sortMap.getOrDefault(o2, t++);
      }
    });
    StringBuilder sb = new StringBuilder();
    for (char c : temp) {
      sb.append(c);
    }
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/shortest-distance-to-a-character/description/
   */
  public int[] shortestToChar(String S, char C) {
    int[] ans = new int[S.length()];
    int index = S.indexOf(C), last = -1;
    while (index >= 0) {

      if (last >= 0) {
        int l = last + 1, r = index - 1;
        while (l <= r) {
          ans[l] = l - last;
          ans[r] = index - r;
          l++;
          r--;
        }
      } else {
        for (int i = 0; i < index; i++) {
          ans[i] = index - i;
        }
      }

      last = index;
      index = S.indexOf(C, index + 1);
    }

    for (int i = last + 1; i < ans.length; i++) {
      ans[i] = i - last;
    }

    return ans;
  }

  /**
   * https://leetcode.com/problems/subdomain-visit-count/description/
   */
  public List<String> subdomainVisits(String[] cpdomains) {
    List<String> ans = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();
    for (String s : cpdomains) {
      subdomainVisitsHelper(map, s);
    }
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      sb.setLength(0);
      sb.append(entry.getValue()).append(" ").append(entry.getKey());
      ans.add(sb.toString());
    }

    return ans;
  }

  private void subdomainVisitsHelper(Map<String, Integer> map, String cpdomains) {
    String[] cp = cpdomains.split(" ");
    if (cp.length == 2) {
      int time = Integer.valueOf(cp[0]);
      int index = cp[1].indexOf('.');
      while (index > 0) {
        map.put(cp[1], map.getOrDefault(cp[1], 0) + time);
        cp[1] = cp[1].substring(index + 1);
        index = cp[1].indexOf('.');
      }
      map.put(cp[1], map.getOrDefault(cp[1], 0) + time);
    }
  }

  public int uniqueMorseRepresentations(String[] words) {
    Set<String> set = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    for (String s : words) {
      sb.setLength(0);
      for (char c : s.toCharArray()) {
        sb.append(morse[c - 'a']);
      }
      set.add(sb.toString());
    }
    return set.size();
  }

  /**
   * https://leetcode.com/problems/valid-number/description/
   */
  public boolean isNumber(String s) {
    if (s.isEmpty()) return false;

    int r = s.length() - 1;
    while (r > 0 && s.charAt(r) == ' ') r--;

    int flag = 0, count = 0;
    boolean e = false, dot = false;

    for (char c : s.substring(0, r + 1).toCharArray()) {
      if (c < '0' || c > '9') {
        if (c == 'e') {
          if (flag == 0 || e || count == r) return false;
          e = true;
          flag = 0;
        } else if (c == '.') {
          if (flag == 0 || dot || count == r) return false;
          dot = true;
          flag = 0;
        } else if (c == '+' || c == '-') {
          if (flag > 0) return false;
        } else {
          return false;
        }
      } else {
        flag++;
      }

      count++;
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/longest-valid-parentheses/description/
   */
  public int longestValidParentheses(String s) {
    int longest = 0;
    Stack<Integer> stack = new Stack<>();
    stack.push(0);
    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(0);
      } else {
        if (stack.size() > 1) {
          int t = stack.pop();
          stack.push(stack.pop() + t + 2);
          longest = Math.max(longest, stack.peek());
        } else {
          stack.clear();
          stack.push(0);
        }
      }
    }

    return longest;
  }

  /**
   * https://leetcode.com/problems/bulls-and-cows/description/
   */
  public String getHint(String secret, String guess) {
    int bulls = 0;
    int cows = 0;
    int[] c = new int[10];
    char[] cs = secret.toCharArray();
    char[] cg = guess.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      int s = cs[i] - '0';
      int g = cg[i] - '0';
      if (s == g) {
        bulls++;
      } else {
        if (c[s]++ < 0) {
          cows++;
        }
        if (c[g]-- > 0) {
          cows++;
        }
      }
    }
    return bulls + "A" + cows + "B";
  }

  /**
   * https://leetcode.com/problems/ugly-number-ii/description/
   */
  public int nthUglyNumber(int n) {
    TreeSet<Long> ans = new TreeSet<>();
    ans.add(1L);
    n--;
    for (int i = 0; i < n; ++i) {
      long first = ans.pollFirst();
      n--;
      ans.add(first * 2);
      ans.add(first * 3);
      ans.add(first * 5);
    }
    return ans.first().intValue();
  }

  /**
   * https://leetcode.com/problems/basic-calculator-ii/description/
   */
  public int calculate(String s) {
    Stack<Character> op = new Stack<>();
    Stack<Long> num = new Stack<>();
    long ans = 0;
    for (char c : s.toCharArray()) {
      if (c == ' ') continue;

      if (c >= '0' && c <= '9') {
        ans = ans * 10 + (c - '0');
      } else {
        if (op.isEmpty() || op.peek() == '+' || op.peek() == '-') {
          num.push(ans);
        } else {
          num.push(calculateHelper(num.pop(), ans, op.pop()));
        }
        op.push(c);
        ans = 0;
      }
    }

    if (op.isEmpty() || op.peek() == '+' || op.peek() == '-') {
      num.push(ans);
    } else {
      num.push(calculateHelper(num.pop(), ans, op.pop()));
    }

    ans = 0;

    while (!num.isEmpty()) {
      if (!op.isEmpty()) {
        ans += calculateHelper(0, num.pop(), op.pop());
      } else {
        ans += num.pop();
      }
    }

    return (int) ans;
  }

  private long calculateHelper(long a, long b, char c) {
    switch (c) {
      case '-':
        return a - b;
      case '*':
        return a * b;
      case '/':
        return a / b;
      case '+':
        return a + b;
      default:
        return 0;
    }
  }

  /**
   * https://leetcode.com/problems/repeated-dna-sequences/description/
   */
  public List<String> findRepeatedDnaSequences(String s) {
    Set<String> set = new HashSet<>();
    List<String> result = new ArrayList<>();

    if (s == null || s.length() < 10) {
      return result;
    }

    for (int i = 0; i < s.length() - 9; i++) {
      String subStr = s.substring(i, i + 10);

      if (set.contains(subStr) && !result.contains(subStr)) {
        result.add(subStr);
      } else {
        set.add(subStr);
      }
    }

    return result;
  }

  /**
   * https://leetcode.com/problems/largest-number/description/
   */
  public String largestNumber(int[] nums) {

    List<Integer> list = new ArrayList<>(nums.length);
    for (int n : nums) {
      list.add(n);
    }

    list.sort(new Comparator<Integer>() {
      @Override public int compare(Integer o1, Integer o2) {

        char[] chars1 = o1.toString().toCharArray();
        char[] chars2 = o2.toString().toCharArray();
        int len = Math.min(chars1.length, chars2.length);
        for (int i = 0; i < len; i++) {
          if (chars1[i] > chars2[i]) return -1;
          if (chars1[i] < chars2[i]) return 1;
        }

        if (chars2.length > len) {
          return chars2[len] > chars1[0] ? 1 : -1;
        }

        if (chars1.length > len) {
          return chars1[len] > chars2[0] ? -1 : 1;
        }

        return 0;
      }
    });

    StringBuilder sb = new StringBuilder();
    for (int n : list) {
      sb.append(n);
    }

    while (sb.length() > 1 && sb.charAt(0) == '0') {
      sb.deleteCharAt(0);
    }

    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/fraction-to-recurring-decimal/description/
   */
  public String fractionToDecimal(int numerator, int denominator) {

    if (numerator == 0) return "0";
    if (denominator == 0) return "";

    StringBuilder sb = new StringBuilder();

    if (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0) sb.append('-');
    long lNumerator = Math.abs((long) numerator);
    long lDenominator = Math.abs((long) denominator);

    int dotIndex = -1;
    sb.append(lNumerator / lDenominator);
    long last = lNumerator % lDenominator;
    List<Long> history = new ArrayList<>();
    history.add(last);
    if (last > 0) {
      dotIndex = sb.length();
      sb.append('.');
    }

    while (last > 0) {
      long t = last * 10;
      sb.append(t / lDenominator);
      last = t % lDenominator;
      int index = history.indexOf(last);
      if (index >= 0) {
        sb.insert(dotIndex + 1 + index, "(");
        sb.append(")");
        break;
      }
      history.add(last);
    }

    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/compare-version-numbers/description/
   */
  public int compareVersion(String version1, String version2) {
    String[] v1 = version1.split("\\.");
    String[] v2 = version2.split("\\.");
    int len = Math.min(v1.length, v2.length);

    for (int i = 0; i < len; i++) {
      if (Integer.valueOf(v1[i]) > Integer.valueOf(v2[i])) return 1;
      if (Integer.valueOf(v1[i]) < Integer.valueOf(v2[i])) return -1;
    }

    for (int i = len; i < v1.length; i++) {
      if (Integer.valueOf(v1[i]) > 0) return 1;
    }

    for (int i = len; i < v2.length; i++) {
      if (Integer.valueOf(v2[i]) > 0) return -1;
    }

    return 0;
  }

  /**
   * https://leetcode.com/problems/reverse-words-in-a-string/description/
   */
  public String reverseWordsII(String s) {
    StringBuilder sb = new StringBuilder();
    StringBuilder ans = new StringBuilder();

    for (char c : s.toCharArray()) {
      if (c == ' ') {
        if (sb.length() > 0) {
          if (ans.length() > 0) ans.insert(0, ' ');
          ans.insert(0, sb.toString());
          sb.setLength(0);
        }
      } else {
        sb.append(c);
      }
    }
    if (sb.length() > 0) {
      if (ans.length() > 0) ans.insert(0, ' ');
      ans.insert(0, sb.toString());
      sb.setLength(0);
    }

    return ans.toString();
  }

  /**
   * https://leetcode.com/problems/word-break/description/
   */
  public boolean wordBreak(String s, List<String> wordDict) {
    int mL = Integer.MIN_VALUE;
    for (String e : wordDict) mL = Math.max(mL, e.length());

    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i <= s.length(); i++) {
      for (int j = i - 1; i - j <= mL && j >= 0; j--) {
        if (dp[j] && wordDict.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[s.length()];
  }

  /**
   * https://leetcode.com/problems/palindrome-partitioning/description/
   */
  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    if (s == null || s.length() == 0) return result;
    solver(result, new ArrayList<>(), s, 0);
    return result;
  }

  private void solver(List<List<String>> result, List<String> temp, String s, int left) {
    if (left == s.length()) {
      result.add(new ArrayList<String>(temp));
    }

    for (int right = left; right < s.length(); right++) {
      if (checkPartition(s, left, right)) {
        temp.add(s.substring(left, right + 1));
        solver(result, temp, s, right + 1);
        temp.remove(temp.size() - 1);
      }
    }
  }

  private boolean checkPartition(String s, int l, int r) {
    while (l < r) {
      if (s.charAt(l) != s.charAt(r)) return false;
      l++;
      r--;
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/restore-ip-addresses/description/
   */
  public List<String> restoreIpAddresses(String s) {
    List<String> ans = new ArrayList<>();

    if (s == null || s.length() < 4 || s.length() > 12) return ans;

    restoreIPAddressHelper(ans, s.toCharArray(), new StringBuilder(), 0, 0);

    return ans;
  }

  private void restoreIPAddressHelper(List<String> ans, char[] chars, StringBuilder sb, int index,
      int ipCount) {
    if (sb.length() > 0) sb.append(".");

    int r = chars.length - index;
    if (ipCount == 3) {
      if (r < 4) {
        int t = 0;
        for (int i = index; i < chars.length; i++) {

          if ((sb.charAt(sb.length() - 1) == '0' || t == 0 && i < chars.length - 1)
              && chars[i] == '0') {
            return;
          }

          t = t * 10 + (chars[i] - '0');
          sb.append(chars[i]);
        }

        if (t < 256) ans.add(sb.toString());
      }
    } else {

      int len = sb.length();
      int t = chars[index] - '0';
      // 1 item
      sb.append(chars[index]);
      restoreIPAddressHelper(ans, chars, sb, index + 1, ipCount + 1);

      if (t > 0 && chars.length - 4 - index + ipCount > 0 && (chars[index] != '0'
          || chars[index + 1] != '0')) {
        // 2 items
        t = t * 10 + chars[index + 1] - '0';
        sb.setLength(len + 1);
        sb.append(chars[index + 1]);
        restoreIPAddressHelper(ans, chars, sb, index + 2, ipCount + 1);

        // 3 items
        t = t * 10 + chars[index + 2] - '0';
        if (chars.length - 5 - index + ipCount > 0 && t < 256) {
          sb.setLength(len + 2);
          sb.append(chars[index + 2]);
          restoreIPAddressHelper(ans, chars, sb, index + 3, ipCount + 1);
        }
      }

      sb.setLength(len);
    }
  }

  /**
   * https://leetcode.com/problems/decode-ways/description/
   */
  public int numDecodings(String s) {
    int n = s.length();
    if (n == 0) return 0;

    int[] memo = new int[n + 1];
    memo[n] = 1;
    memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

    for (int i = n - 2; i >= 0; i--)
      if (s.charAt(i) != '0') {
        memo[i] =
            (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2]
                : memo[i + 1];
      }

    return memo[0];
  }

  /**
   * https://leetcode.com/problems/simplify-path/description/
   */
  public String simplifyPath(String path) {
    Stack<String> stack = new Stack<>();
    for (String s : path.split("/")) {
      if (s != null && s.length() != 0 && !s.equals(".")) {
        if (s.equals("..")) {
          if (!stack.isEmpty()) stack.pop();
        } else {
          stack.push(s);
        }
      }
    }

    StringBuilder sb = new StringBuilder("/");
    for (String s : stack.subList(0, stack.size())) {
      sb.append(s).append("/");
    }
    if (sb.length() > 1) sb.setLength(sb.length() - 1);
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/multiply-strings/description/
   */
  public String multiplyBetter(String num1, String num2) {

    if (num1.length() == 1 && num1.charAt(0) == '0' || num2.length() == 1 & num2.charAt(0) == '0') {
      return "0";
    }

    if (num2.length() > num1.length()) {
      String t = num1;
      num1 = num2;
      num2 = t;
    }

    char[] n1 = num1.toCharArray();
    char[] n2 = num2.toCharArray();
    int[] ans = new int[n1.length + n2.length];
    for (int i = n1.length - 1; i >= 0; i--) {
      for (int j = n2.length - 1; j >= 0; j--) {
        ans[i + j + 1] += (n1[i] - '0') * (n2[j] - '0');
      }
    }

    for (int i = ans.length - 1; i > 0; i--) {
      ans[i - 1] += ans[i] / 10;
      ans[i] %= 10;
    }

    StringBuilder sb = new StringBuilder();
    if (ans[0] > 0) sb.append(ans[0]);
    for (int i = 1; i < ans.length; i++) {
      sb.append(ans[i]);
    }
    return sb.toString();
  }

  public String multiply(String num1, String num2) {

    if (num2.length() > num1.length()) {
      String t = num1;
      num1 = num2;
      num2 = t;
    }

    StringBuilder ans = new StringBuilder();

    char[] chars1 = num1.toCharArray();
    char[] chars2 = num2.toCharArray();
    int chars1LastIndex = chars1.length - 1;
    List<Integer> temp = new ArrayList<>();

    for (int i = chars2.length - 1; i >= 0; i--) {

      if (temp.size() == 0) {
        temp.add(0);
      }

      int n = chars2[i] - '0';
      if (n == 0) {
        ans.append(temp.remove(0));
        continue;
      }
      int carry = 0;
      for (int j = chars1LastIndex; j >= 0; j--) {
        int m = chars1[j] - '0';
        int p = m * n + carry;

        int index = chars1LastIndex - j;
        if (temp.size() > index) {
          p += temp.get(index);
          int r = p % 10;
          temp.set(index, r);
        } else {
          int r = p % 10;
          temp.add(r);
        }

        carry = p / 10;
      }

      if (carry > 0) temp.add(carry);

      ans.append(temp.remove(0));
    }

    for (int n : temp) {
      ans.append(n);
    }

    return ans.reverse().toString();
  }

  /**
   * https://leetcode.com/problems/count-binary-substrings/description/
   */
  public int countBinarySubstrings(String s) {
    if (s == null || s.isEmpty()) return 0;
    int count = 0, one = 0, zero = 0;
    char last = s.charAt(0);
    for (char c : s.toCharArray()) {
      if (c != last) {
        count += Math.min(one, zero);
        last = c;
        if (c == '1') {
          one = 1;
        } else {
          zero = 1;
        }
      } else {
        if (c == '1') {
          one++;
        } else {
          zero++;
        }
      }
    }
    count += Math.min(one, zero);
    return count;
  }

  /**
   * https://leetcode.com/problems/repeated-string-match/description/
   */
  public int repeatedStringMatch(String A, String B) {
    StringBuilder sb = new StringBuilder(A);
    for (int r = 1; r <= B.length() / A.length() + 2; r++) {
      if (sb.toString().contains(B)) return r;
      sb.append(A);
    }
    return -1;
  }

  /**
   * https://leetcode.com/problems/valid-palindrome-ii/description/
   */
  public boolean validPalindrome(String s) {
    if (s == null) return false;
    if (s.length() < 2) return true;

    int[] p = subValidPalindrome(s, 0, s.length() - 1);
    if (p[0] < p[1]) {
      int[] p1 = subValidPalindrome(s, p[0] + 1, p[1]);
      if (p1[0] < p1[1]) {
        int[] p2 = subValidPalindrome(s, p[0], p[1] - 1);
        return p2[0] >= p[1];
      } else {
        return true;
      }
    } else {
      return true;
    }
  }

  private int[] subValidPalindrome(String s, int l, int r) {
    while (l < r) {
      if (s.charAt(l) != s.charAt(r)) {
        break;
      }
      l++;
      r--;
    }
    return new int[] {l, r};
  }

  /**
   * 翻转给定语句中的每个单词（单词之间有一个空格分割）
   */
  public String reverseWords(String s) {
    if (s == null || s.length() == 1) return s;

    String[] ss = s.split(" ");
    StringBuilder sb = new StringBuilder();

    for (String t : ss) {
      for (int i = t.length() - 1; i >= 0; i--) {
        sb.append(t.charAt(i));
      }
      sb.append(" ");
    }

    sb.setLength(sb.length() - 1);
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/student-attendance-record-i/description/
   */
  public boolean checkRecord(String s) {
    int a = 0, l = 0;

    for (char c : s.toCharArray()) {
      if (c == 'A') a++;
      if (c == 'L') {
        l++;
      } else {
        l = 0;
      }

      if (a > 1) return false;
      if (l > 2) return false;
    }

    return true;
  }

  /**
   * https://leetcode.com/problems/reverse-string-ii/description/
   */
  public String reverseStr(String s, int k) {
    StringBuilder sb = new StringBuilder(s);
    for (int i = 0; i <= s.length() / k; i += 2) {
      reverseSubStr(sb, k * i, k);
    }

    return sb.toString();
  }

  private void reverseSubStr(StringBuilder sb, int start, int k) {
    k = Math.min(k, sb.length() - start);
    if (start + k <= sb.length()) {
      int r = start + k - 1;
      while (start < r) {
        char c = sb.charAt(start);
        sb.setCharAt(start, sb.charAt(r));
        sb.setCharAt(r, c);
        start++;
        r--;
      }
    }
  }

  /**
   * https://leetcode.com/problems/longest-uncommon-subsequence-i/description/
   *
   * fucking problem.
   */
  public int findLUSlength(String a, String b) {
    return a.equals(b) ? -1 : Math.max(a.length(), b.length());
  }

  /**
   * 判断给定的单词是否合法： - 全部字母为大写 - 全部字母为小写 - 只有首字母为大写
   */
  public boolean detectCapitalUse(String word) {
    char firstChar = word.charAt(0);
    boolean firstUpper = firstChar >= 'A' && firstChar <= 'Z';
    int upperCount = 0;

    for (char t : word.toCharArray()) {
      if (t >= 'A' && t <= 'Z') upperCount++;
    }

    return firstUpper ? (upperCount == 1 || upperCount == word.length()) : upperCount == 0;
  }

  /**
   * https://leetcode.com/problems/license-key-formatting/description/
   */
  public String licenseKeyFormatting(String S, int K) {
    StringBuilder sb = new StringBuilder();
    int count = 0;

    for (int i = S.length() - 1; i >= 0; i--) {
      char c = S.charAt(i);
      if (c == '-') continue;

      if (c >= 'a' && c <= 'z') c = (char) (c - 'a' + 'A');

      if (sb.length() > 0 && count % K == 0) {
        sb.append('-');
      }

      sb.append(c);
      count++;
    }

    return sb.reverse().toString();
  }

  /**
   * 判断一个字符串是否是子串的重复
   */
  public boolean repeatedSubstringPattern(String s) {
    if (s == null || s.length() < 2) return false;

    int rate = 2;

    while (!checkRate(s, rate)) {
      rate++;
      if (rate > s.length()) return false;
    }

    return true;
  }

  private boolean checkRate(String s, int rate) {
    if (s.length() % rate != 0) return false;
    int len = s.length() / rate, start = 0;
    int[] index = new int[rate];
    for (int i = 0; i < rate; i++) {
      index[i] = start;
      start += len;
    }
    for (int i = 0; i < len; i++) {
      boolean flag = true;
      char c = s.charAt(index[0] + i);
      for (int n : index) {
        if (c != s.charAt(n + i)) {
          flag = false;
          break;
        }
      }

      if (!flag) return false;
    }

    return true;
  }

  /**
   * 返回s中p的相同字母异序词索引
   */
  public List<Integer> findAnagrams(String s, String p) {
    if (s == null || s.length() < p.length()) return new ArrayList<>();
    List<Integer> result = new ArrayList<>();
    int n = s.length() - p.length();
    for (int i = 0; i <= n; i++) {
      String sub = s.substring(i, i + p.length());

      if (isAnagram(sub, p)) result.add(i);
    }

    return result;
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
