import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class HundredDivideTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("=== 100÷20 or 200÷25====");

        Map<String, String> mapQuestions = buildQuestions(60);
        PDFCreator pdfCreator = new PDFCreator("HundredsDivide", "100÷20 or 100÷25");
        List<String> listQuestions = mapQuestions.keySet().stream().collect(Collectors.toList());
        Collections.shuffle(listQuestions);
        List<String> listAnswers = getAnswers(mapQuestions, listQuestions);
        String filePath = pdfCreator.outputPdf(listQuestions, listAnswers, 3);

        System.out.println("====done==== File is:" + filePath);

    }

    private static List<String> getAnswers(Map<String, String> mapQuestions, List<String> listQuestions) {
        return listQuestions.stream().map(it -> mapQuestions.get(it)).collect(Collectors.toList());
    }


    private static Map<String, String> buildQuestions(int questionsNumber) {
        Map<String, String> mapQuestionsAnswers = new HashMap<>();

        int counter = questionsNumber;
        while (counter > 0) {
            int op1 = getRandomNumber(new int[]{100, 200, 300, 400, 500, 600, 700, 800, 900});
            int op2 = getRandomNumber(new int[]{20, 25, 10, 4, 2, 5, 50});

            String question = String.format("%d ÷ %d =", op1, op2);

            mapQuestionsAnswers.put(question, String.valueOf(op1 / op2));
            counter--;
        }

        return mapQuestionsAnswers.entrySet().stream().limit(questionsNumber)
                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

    }

    private static int getRandomNumber(int[] ints) {
        return ints[new Random().nextInt(ints.length)];
    }


}
