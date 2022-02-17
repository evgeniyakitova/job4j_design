package ru.job4j.srp;

import org.junit.Test;

import java.time.OffsetDateTime;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHtmlReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new HtmlReport(store);
        StringBuilder expect = new StringBuilder(HtmlReport.START);
        expect.append("<tr><td>").append(worker.getName()).append("</td>")
                .append("<td>").append(worker.getHired()).append("</td>")
                .append("<td>").append(worker.getFired()).append("</td>")
                .append("<td>").append(worker.getSalary()).append("</td></tr>")
                .append(HtmlReport.END);
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenDollarSalaryReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 6400);
        store.add(worker);
        Report report = new DollarSalaryReport(store);
        double salary = worker.getSalary() / DollarSalaryReport.DOLLAR_RATE;
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(salary).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenSalaryDescReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee firstWorker = new Employee("Anna", now, now, 7000);
        Employee secondWorker = new Employee("Ivan", now, now, 5000);
        store.add(firstWorker);
        store.add(secondWorker);
        Report report = new SalaryDescReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary")
                .append(System.lineSeparator())
                .append(firstWorker.getName()).append(";")
                .append(firstWorker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(secondWorker.getName()).append(";")
                .append(secondWorker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(report.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenXMLReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("Anna", now, now, 7000);
        store.add(employee);
        OffsetDateTime dateTime = OffsetDateTime.ofInstant(now.toInstant(), now.getTimeZone().toZoneId());
        String template = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """.formatted(employee.getName(),
                dateTime,
                dateTime,
                employee.getSalary());
        Report report = new XMLReport(store);
        assertThat(report.generate(em -> true), is(template));
    }

    @Test
    public void whenJsonReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee employee = new Employee("Anna", now, now, 7000);
        store.add(employee);
        String template = """
                [
                  {
                    "name": "%1$s",
                    "hired": {
                      "year": %2$s,
                      "month": %3$s,
                      "dayOfMonth": %4$s,
                      "hourOfDay": %5$s,
                      "minute": %6$s,
                      "second": %7$s
                    },
                    "fired": {
                      "year": %2$s,
                      "month": %3$s,
                      "dayOfMonth": %4$s,
                      "hourOfDay": %5$s,
                      "minute": %6$s,
                      "second": %7$s
                    },
                    "salary": %8$s
                  }
                ]""".formatted(employee.getName(),
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                employee.getSalary());
        Report report = new JsonReport(store);
        assertThat(report.generate(em -> true), is(template));
    }
}