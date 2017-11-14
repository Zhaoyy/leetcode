package leetcode;

public class RomanNumber {
  public static void main(String[] args) {
    RomanNumber romanNumber = new RomanNumber();
    System.out.println(romanNumber.roman2Number("MCMLXXX"));
    System.out.println(romanNumber.number2RomanBetter(1980));
  }

  /**
   * 思路：根据罗马数字规则1-3999中最多只有三个相同数字一起，而且都是三个（I,X,C,M)才会出现这种情况。 从1-9中我们可以发现相减的情况只有IX-9,IV-4两种，其他情况都是相加，
   * 所以我们可以简化转化过程为每位数字都可以通过9,5,4,1之间相加来表示。
   */
  private String number2RomanBetter(int num) {
    String[] symbol = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    StringBuilder str = new StringBuilder();
    for (int i = 0; num != 0; i++) {
      while (num >= value[i]) {
        num -= value[i];
        str.append(symbol[i]);
      }
    }
    return str.toString();
  }

  /**
   * @param num = 1-3999
   */
  private String number2Roman(int num) {
    String[][] temp = new String[][] {
        {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 0 - 9
        {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // (0-9) * 10
        {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // (0-9) * 100
        {"", "M", "MM", "MMM", "MMMM", "MMMMM", "MMMMMM", "MMMMMMM", "MMMMMMMM", "MMMMMMMMM"}
    };
    StringBuilder sb = new StringBuilder();
    if (num >= 1000) {
      sb.append(temp[3][num / 1000 % 10]);
    }

    if (num >= 100) {
      sb.append(temp[2][num / 100 % 10]);
    }

    if (num >= 10) {
      sb.append(temp[1][num / 10 % 10]);
    }

    sb.append(temp[0][num % 10]);

    return sb.toString();
  }

  /**
   * @return 1-3999
   */
  private int roman2Number(String s) {
    int[] num = new int[s.length()];

    for (int i = 0; i < num.length; i++) {
      switch (s.charAt(i)) {
        case 'I':
          num[i] = 1;
          break;
        case 'X':
          num[i] = 10;
          break;
        case 'V':
          num[i] = 5;
          break;
        case 'L':
          num[i] = 50;
          break;
        case 'C':
          num[i] = 100;
          break;
        case 'D':
          num[i] = 500;
          break;
        case 'M':
          num[i] = 1000;
          break;
        default:
          break;
      }
    }

    int result = 0;

    for (int i = 0; i < num.length - 1; i++) {
      if (num[i] < num[i + 1]) {
        result -= num[i];
      } else {
        result += num[i];
      }
    }

    result += num[num.length - 1];

    return result;
  }
}
