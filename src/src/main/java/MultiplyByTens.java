import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiplyByTens {
    public static void main(String[] args) {
        System.out.println("==start==");
        Random random = new Random();
        int dividend = random.nextInt(1000);

        // find all factors
        List<Integer> factors = FindAllFactors(dividend);
        int divisor = factors.get(random.nextInt(factors.size()));

        int zero1 = (int) Math.pow(10, random.nextInt(6));
        int zero2 = (int) Math.pow(10, random.nextInt(6));
        System.out.println(zero1);
        System.out.println(zero2);
        int zeroS = Math.min(zero1, zero2);
        int zeroL = Math.max(zero1, zero2);

        dividend *= zeroL;
        divisor *= zeroS;

        System.out.println(String.format("%d / %d = %d", dividend, divisor, dividend / divisor));


        // to output report in pdf format
    }

    private static List<Integer> FindAllFactors(int dividend) {
        List<Integer> allFactors = new ArrayList<>();
        // 32: 2,4,8,16 (symmetric in square of 32, roughly 5.x)
        for (int i = 2; i * i <= dividend; i++) {
            if (dividend % i == 0) {
                allFactors.add(i);
            }
        }
        return allFactors;
    }
}