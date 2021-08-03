package todzhang.util;

import org.bouncycastle.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.IntFunction;

public class RandomNumberUtils {

    public static int getRandomUpTo(int max, int[] excludes) {
        Random random = new Random();
        int number = random.nextInt(max);

        while (Arrays.contains(excludes, number)) {
            number = random.nextInt(max);
        }
        return number;
    }

    public static int getRandomUpTo(int max) {
        return getRandomUpTo(max, new int[]{0, 1});
    }

    public static int getRandomUpTo(int max, int exclude) {
        return getRandomUpTo(max, new int[]{0, 1, exclude});
    }

    public static int getRandomUpTo(int max, IntFunction<Boolean> fun) {
        Random random = new Random();
        int number = random.nextInt(max);
        while (!fun.apply(number)) {
            number = random.nextInt(max);
        }
        return number;
    }

    public static int getRandomDividableNumber(int max) {
        Random random = new Random();
        int number = random.nextInt(max);
        while (getAllFactors(number).size() < 2) {
            number = random.nextInt(max);
        }
        return number;
    }

    public static int findRandomFactor(int dividend) {
        List<Integer> allFactors = new ArrayList<>();
        // 32: 2,4,8,16 (symmetric in square of 32, roughly 5.x)
        for (int i = 2; i * i <= dividend; i++) {
            if (dividend % i == 0) {
                allFactors.add(i);
            }
        }
        System.out.println("size:" + allFactors.size());
        return allFactors.get(new Random().nextInt(allFactors.size()));
    }

    public static List<Integer> getAllFactors(int dividend) {
        List<Integer> allFactors = new ArrayList<>();
        // 32: 2,4,8,16 (symmetric in square of 32, roughly 5.x)
        for (int i = 2; i * i <= dividend; i++) {
            if (dividend % i == 0) {
                allFactors.add(i);
            }
        }
        System.out.println("size:" + allFactors.size());
        return allFactors;
    }


}
