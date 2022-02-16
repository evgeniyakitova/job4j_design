package ru.job4j.srp;

import org.junit.Test;

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
}