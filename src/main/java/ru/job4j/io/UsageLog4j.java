package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        byte b = 1;
        short sh = 12;
        int age = 33;
        long l = 654654321654565L;
        float f = 0.4f;
        double d = 256.78;
        boolean b1 = true;
        char ch = 78;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.debug("Byte : {}, boolean : {}, and double : {}", b, b1, d);
        LOG.debug("Short : {}, long: {}, float : {} and char : {}", sh, l, f, ch);
    }
}
