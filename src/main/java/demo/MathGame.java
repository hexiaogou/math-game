package demo;

import demo.dto.Type;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * MathGame
 *
 * @author hexiaogou
 * @desc Math Game
 * @date 2022-10-04 09:58
 */
public class MathGame {
    private static Random random = new Random();

    private int illegalArgumentCount = 0;

    private static final int MIN_PRIME = 2;

    private static final char SYMBOL_MULTI = '*';

    private static final Map m = new HashMap();

    private static final Map n = new HashMap<>();

    static {
        m.put("a", "aaa");
        m.put("b", "bbb");

        n.put(Type.RUN, "aaa");
        n.put(Type.STOP, "bbb");
    }

    public static void main(String[] args) throws InterruptedException {
        MathGame game = new MathGame();
        while (true) {
            game.run();
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void run() {
        try {
            int number = random.nextInt() / 10000;
            List<Integer> primeFactors = primeFactors(number);
            print(number, primeFactors);
        } catch (Exception e) {
            System.out.println(String.format("illegalArgumentCount:%3d, ", illegalArgumentCount) + e.getMessage());
        }
    }

    public static void print(int number, List<Integer> primeFactors) {
        StringBuilder sb = new StringBuilder(number + "=");
        for (int factor : primeFactors) {
            sb.append(factor).append("*");
        }
        if (sb.charAt(sb.length() - 1) == SYMBOL_MULTI) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

    public List<Integer> primeFactors(int number) {
        if (number < MIN_PRIME) {
            illegalArgumentCount++;
            throw new IllegalArgumentException("number is: " + number + ", need >= 2");
        }

        List<Integer> result = new ArrayList<Integer>();
        //region getting prime factors by niu bi way
        int i = 2;
        while (i <= number) {
            if (number % i == 0) {
                result.add(i);
                number = number / i;
                i = 2;
            } else {
                i++;
            }
        }
        //endregion

        return result;
    }
}
