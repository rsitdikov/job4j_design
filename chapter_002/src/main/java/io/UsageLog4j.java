package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = true;
        byte bt = 127;
        short shrt = 32767;
        int integer = 2147483647;
        long lng = 9223372036854775807L;
        char chr = 'A';
        float fl = 3.4e38F;
        double dbl = 1.8e307;
        String format = String.join(System.lineSeparator(), "", "boolean : {} ", "byte : {} ", "short : {} ",
                "int : {} ", "long : {} ", "char : {} ", "float : {} ", "double : {}");
        LOG.debug(format, bool, bt, shrt, integer, lng, chr, fl, dbl);
    }
}