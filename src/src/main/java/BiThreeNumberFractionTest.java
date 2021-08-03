import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class BiThreeNumberFractionTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("===negative number test====");

        String fileName = "TwoNumbersDivideOneFactor";
        String description = "8*5/4 should transform to (8/4) * 5";
        Map<String, String> mapQuestions = buildQuestions(42, 100);
        PDFCreator pdfCreator = new PDFCreator(fileName, description);
        List<String> listQuestions = mapQuestions.keySet().stream().collect(Collectors.toList());
        Collections.shuffle(listQuestions);
        List<String> listAnswers = getAnswers(mapQuestions, listQuestions);
        pdfCreator.outputPdf(listQuestions, listAnswers, 3);

        System.out.println("====done====");

    }

    private static List<String> getAnswers(Map<String, String> mapQuestions, List<String> listQuestions) {
        return listQuestions.stream().map(it -> mapQuestions.get(it)).collect(Collectors.toList());
    }


    private static Map<String, String> buildQuestions(int questionsNumber, int maxNumber) {
        Map<String, String> mapQuestionsAnswers = new HashMap<>();

        for (int i = 0; i < questionsNumber; i++) {
            int op1 = getNonZeroRandomNumber(maxNumber);
            int op2 = getNumerator(maxNumber, op1, true);
            int op3 = getNumerator(maxNumber, op1, false);

            String question = String.format("%d*%d/%d =", op2, op3, op1);
            int answer = op2 * op3 / op1;
            mapQuestionsAnswers.put(question, String.valueOf(answer));
        }
        return mapQuestionsAnswers;

    }

    private static int getNumerator(int maxNumber, int num1, boolean beFactor) {
        Random random = new Random();
        int number = random.nextInt(maxNumber);
        if (beFactor) {
            int factor = getRandomFactor(number);
            while (number == 0 || num1 % number != 0 || number % num1 != 0) {
                System.out.println("Still trying next number:" + number + ", denominator is:" + num1 + " with beFactor:" + beFactor);

                number = random.nextInt(maxNumber);
            }
        } else {
            while (number == 0 || num1 % number == 0 || number % num1 == 0) {
                System.out.println("Still trying next number:" + number + ", denominator is:" + num1 + " with beFactor:" + beFactor);

                number = random.nextInt(maxNumber);
            }
        }
        System.out.println("get number:" + number + " with beFactor:" + beFactor);

        return number;
    }

    private static int getRandomFactor(int number) {
        // get it's factors
        ArrayList<Integer> listFactors = new ArrayList<Integer>();
        for (int i = 0; i * i < number; i++) {
            if (number % i == 0) {
                listFactors.add(number);
            }
        }
        Collections.shuffle(listFactors);
        return listFactors.get(0);
    }

    private static int getNonZeroRandomNumber(int maxNumber) {

        Random random = new Random();
        int number = random.nextInt(maxNumber);
        while (number == 0) {
            number = random.nextInt(maxNumber);
        }
        System.out.println("get random number:" + number);
        return number;
    }


}
