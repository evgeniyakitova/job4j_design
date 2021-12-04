package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.username"), is("postgres"));
        assertThat(config.value("hibernate.connection"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyStrings() {
        String path = "./data/with_comment_and_empty_strings.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password"), is("password"));
        assertThat(config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithViolation() {
        String path = "./data/pair_with_violation.properties";
        Config config = new Config(path);
        config.load();
    }
}