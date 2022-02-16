package ru.job4j.srp;

import java.util.function.Predicate;

public class DollarSalaryReport implements Report {
    public static final double DOLLAR_RATE = 75.5;

    private Store store;

    public DollarSalaryReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            double salary = employee.getSalary() / DOLLAR_RATE;
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(salary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
