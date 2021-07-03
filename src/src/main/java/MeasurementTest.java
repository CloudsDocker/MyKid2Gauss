import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.DoubleFunction;
import java.util.stream.Collectors;

public class MeasurementTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("===measurement test====");

        Map<String, String> mapQuestions = createQuestionList();
        PDFCreator pdfCreator = new PDFCreator("./pdfDoc/measurement_" + LocalDateTime.now().toString() + ".pdf");
        List<String> listQuestions = mapQuestions.keySet().stream().collect(Collectors.toList());
        Collections.shuffle(listQuestions);
        List<String> listAnswers = getAnswers(mapQuestions, listQuestions);
        pdfCreator.outputPdf(listQuestions, listAnswers);

        System.out.println("====done====");

    }

    private static List<String> getAnswers(Map<String, String> mapQuestions, List<String> listQuestions) {
        return listQuestions.stream().map(it -> mapQuestions.get(it)).collect(Collectors.toList());
    }

    private static Map<String, String> createQuestionList() {

        String[] q1000Up = new String[]{"%s ml = ? L", "%s mm = ? m", "%s g = ? kg"};
        String[] q1000Down = new String[]{"%s L = ? ml", "%s m = ? mm", "%s kg = ? g"};
        String[] q100Down = new String[]{"%s cm = ? mm"};

        // 200ml = ? L
//        Map<String, String> mapQuestionsAnswers = new HashMap<>();
//        DoubleFunction<Double> func = a->a/1000;
        int questions = 16;
        Map<String, String> mapQuestionsAnswersUp = buildQuestions(questions, q1000Up, true, a -> a / 1000);
        Map<String, String> mapQuestionsAnswersDown = buildQuestions(questions, q1000Down, false, a -> a * 1000);
        Map<String, String> mapQuestionsAnswers100Down = buildQuestions(questions, q100Down, false, a -> a * 100);

        System.out.println(mapQuestionsAnswersUp);
        System.out.println(mapQuestionsAnswersDown);
        System.out.println(mapQuestionsAnswers100Down);
        // 2500ml = L

//        List<String> listQuestions = new ArrayList<>();
//        listQuestions.addAll(mapQuestionsAnswersUp.keySet());
//        listQuestions.addAll(mapQuestionsAnswersDown.keySet());
//        listQuestions.addAll(mapQuestionsAnswers100Down.keySet());

        Map<String, String> allQuestions = new HashMap<>();
        allQuestions.putAll(mapQuestionsAnswersUp);
        allQuestions.putAll(mapQuestionsAnswersDown);
        allQuestions.putAll(mapQuestionsAnswers100Down);
        return allQuestions;

        // 3L = ml
        // 5.7L = ml
        // 0.5L = ml
    }

    private static Map<String, String> buildQuestions(int numQuestions, String[] questions, boolean powUpFlag, DoubleFunction<Double> func) {
        Map<String, String> mapQuestionsAnswers = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < numQuestions; i++) {
            int base = random.nextInt(9) + 1;
            int power = random.nextInt(questions.length) + 1;
            boolean randomPowerUp = random.nextBoolean();
            double number1;
            if (powUpFlag || randomPowerUp) {
                number1 = base * Math.pow(10, power);
            } else {
                number1 = base / Math.pow(10, power);
            }
            String question = questions[random.nextInt(questions.length)];
//        System.out.println(String.format(question, number1)+" = "+format.format(number2));
            mapQuestionsAnswers.put(String.format(question, format.format(number1)), format.format(func.apply(number1)));
        }
        return mapQuestionsAnswers;

    }
}
