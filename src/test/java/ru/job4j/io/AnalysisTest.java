package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalysisTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenWasUnavailableForTwoPeriods() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }
        String expected = "10:57:01;10:59:0111:01:02;11:02:02";
        assertThat(result.toString(), is(expected));
    }

    @Test
    public void whenWasUnavailableForOnePeriod() throws IOException {
        File source = folder.newFile("server.log");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        Analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(result::append);
        }
        String expected = "10:57:01;11:02:02";
        assertThat(result.toString(), is(expected));
    }
}