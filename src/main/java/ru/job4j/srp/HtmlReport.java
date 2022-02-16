package ru.job4j.srp;

import java.util.Calendar;
import java.util.function.Predicate;

public class HtmlReport implements Report {
    public static final String START = """
                <html>
                 <head>
                  <meta content="text/html; charset=utf-8">
                  <title>Report</title>
                 </head>
                 <body>
                  <table>
                   <tr>
                    <th>Name</th>
                    <th>Hired</th>
                    <th>Fired</th>
                    <th>Salary</th>
                   </tr>
                """;
    public static final String END = """
                 </table>
                 </body>
                </html>
                """;

    private Store store;

    public HtmlReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder(START);
        for (Employee employee : store.findBy(filter)) {
            report.append("<tr><td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td></tr>");

        }
        report.append(END);
        return report.toString();
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("Mike", now, now, 100);
        Employee employee2 = new Employee("Anna", now, now, 1000);
        MemStore store = new MemStore();
        store.add(employee);
        store.add(employee2);
        String report = new HtmlReport(store).generate(em -> true);
        System.out.println(report);
    }
}
