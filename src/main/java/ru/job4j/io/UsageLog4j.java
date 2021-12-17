package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int x = 10;
        long lx = 2147483649L;
        float fx = 3.5F;
        double dx = 5.0;
        byte bx = 127;
        short sx = 2;
        char ch = 'x';
        boolean flag = true;

        LOG.trace("trace message");
        LOG.debug("int : {}, long : {}, byte : {}, short : {}", x, lx, bx, sx);
        LOG.info("float : {}, double : {}, char : {}, boolean : {}", fx, dx, ch, flag);
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
