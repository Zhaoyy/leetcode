package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
  /**
   * 根据给定的行数， 返回杨辉三角
   */
  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 1; i <= numRows; i++) {
      result.add(getPascalRow(i - 1, result));
    }

    return result;
  }

  public List<Integer> getRow(int rowIndex) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i <= rowIndex; i++) {
      if (i == rowIndex) {
        return getPascalRow(rowIndex, result);
      } else {
        result.add(getPascalRow(i, result));
      }
    }

    return null;
  }

  private List<Integer> getPascalRow(int rowIndex, List<List<Integer>> pascal) {
    List<Integer> temp = new ArrayList<>();
    temp.add(1);
    if (rowIndex > 0) {
      List<Integer> pre = pascal.get(rowIndex - 1);
      for (int i = 1; i <= rowIndex; i++) {
        if (pre.size() > i) {
          temp.add(pre.get(i - 1) + pre.get(i));
        } else {
          temp.add(1);
        }
      }
    }
    return temp;
  }

  private List<Integer> getPascalRowBetter(int rowIndex) {
    ArrayList<Integer> row = new ArrayList<>();
    long last = 1;
    row.add((int) last);
    for (int i = 1; i <= rowIndex; i++) {
      last = last * (long) (rowIndex + 1 - i) / i;
      row.add((int) last);
    }

    return row;
  }
}
