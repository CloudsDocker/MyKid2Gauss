import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class NegativeNumberTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("===negative number test====");

        Map<String, String> mapQuestions = buildQuestions(42, 100);
        PDFCreator pdfCreator = new PDFCreator("tttt", "./pdfDoc/negative_" + LocalDateTime.now().toString() + ".pdf");
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
        Random random = new Random();

        for (int i = 0; i < questionsNumber; i++) {
            int op1 = getOperator(maxNumber);
            int op2 = getOperator(maxNumber);
            boolean randomBool = random.nextBoolean();

            String question = String.format("%d %s %d =", op1, randomBool ? "+" : "-", op2);
            int sign = 1;
            if (!randomBool) {
                sign = -1;
            }
            mapQuestionsAnswers.put(question, String.valueOf(op1 + sign * op2));
        }
        return mapQuestionsAnswers;

    }

    private static int getOperator(int maxNumber) {

        Random random = new Random();
        int sign = getRandomSign();
        int number = random.nextInt(maxNumber);
        return sign * number;
    }

    private static int getRandomSign() {
        if (new Random().nextBoolean())
            return 1;
        else
            return -1;
    }


}
