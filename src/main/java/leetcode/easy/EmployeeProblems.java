package leetcode.easy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeProblems {

  private Map<Integer, Employee> record = new HashMap<>();
  private int index = 0;

  public int getImportance(List<Employee> employees, int id) {
    Employee employee = record.get(id);
    if (employee == null) {
      for (; index < employees.size(); index++) {
        Employee e = employees.get(index);
        record.put(e.id, e);
        if (e.id == id) {
          employee = e;
          break;
        }
      }
    }

    assert employee != null;
    int importance = employee.importance;

    if (employee.subordinates.size() > 0) {
      for (int subId : employee.subordinates) {
        importance += getImportance(employees, subId);
      }
    }
    return importance;
  }

  public static class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
  }
}
