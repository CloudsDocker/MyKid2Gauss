import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TwoDigitsDivTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("===60 / 5====");

        Map<String, String> mapQuestions = buildQuestions(42, 100);
        PDFCreator pdfCreator = new PDFCreator("./pdfDoc/2DigitsDiv_" + LocalDateTime.now().toString() + ".pdf");
        List<String> listQuestions = mapQuestions.keySet().stream().collect(Collectors.toList());
        Collections.shuffle(listQuestions);
        List<String> listAnswers = getAnswers(mapQuestions, listQuestions);
        pdfCreator.outputPdf(listQuestions, listAnswers);

        System.out.println("====done====");

    }

    private static List<String> getAnswers(Map<String, String> mapQuestions, List<String> listQuestions) {
        return listQuestions.stream().map(it -> mapQuestions.get(it)).collect(Collectors.toList());
    }


    private static Map<String, String> buildQuestions(int questionsNumber, int maxNumber) {
        Map<String, String> mapQuestionsAnswers = new HashMap<>();

        int counter = questionsNumber;
        while (counter > 0) {
            int op1 = new Random().nextInt(maxNumber);
            int op2 = new Random().nextInt(maxNumber);

            if (op1 * op2 == 0 || op1 % op2 != 0 || op1 / op2 == 1 || op1 == 1 || op2 == 1) {
                // avoid ArithmeticException / by zero
                // avoid simplest case =1
                continue;
            }

            String question = String.format("%d ÷ %d =", op1, op2);

            mapQuestionsAnswers.put(question, String.valueOf(op1 / op2));
            counter--;

        }

        return mapQuestionsAnswers.entrySet().stream().limit(questionsNumber)
                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

    }


}
