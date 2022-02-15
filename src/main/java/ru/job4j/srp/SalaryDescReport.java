package ru.job4j.srp;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class SalaryDescReport implements Report {
    private Store store;

    public SalaryDescReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        Collections.sort(employees, (em1, em2) -> Double.compare(em2.getSalary(), em1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary");
        text.append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
