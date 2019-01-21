package by.spurky;

import java.util.concurrent.ThreadLocalRandom;

// for LightShot
public class LinkGenerator {
    private static final Long MAX_VALUE = 2176782335L; //Long.toString(2176782335, 36), Long.valueOf("zzzzzz", 36);
    private static final Long MIN_VALUE = 60466176L;
    private static final String before = "https://prnt.sc/";

    private LinkGenerator (){
    }

    public static String randomLink() {
        return before + Long.toString(randomLong(MIN_VALUE, MAX_VALUE), 36);
    }

    private static long randomLong(long minLong, long maxLong){
        return ThreadLocalRandom.current().nextLong(minLong, maxLong);
    }
}
