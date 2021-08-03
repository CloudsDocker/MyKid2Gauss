import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static todzhang.util.RandomNumberUtils.*;

public class HundredByHundredTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("=== 67x52 or 345÷15 ====");

        Map<String, String> mapQuestions = buildQuestions(48);
        PDFCreator pdfCreator = new PDFCreator("HundredsByHundresMultipicationOrDivide", "67x52 or 345÷15");
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

            int op1 = getRandomUpTo(99);


            boolean isMul = new Random().nextBoolean();
            String question;
            int answer;
            if (isMul) {
                int finalOp = op1;
                int op2 = getRandomUpTo(99, (int num) -> finalOp > 100 ? num < 100 : num > 0);
                question = String.format("%d X %d =", op1, op2);
                answer = op1 * op2;
            } else {
//                IntFunction<Boolean> fun = (int num) -> num!=0 && num>1 && op1>num && op1 % num == 0;
                op1 = getRandomDividableNumber(999);
                int op2 = findRandomFactor(op1);
                question = String.format("%d ÷ %d =", op1, op2);
                answer = op1 / op2;
            }

            mapQuestionsAnswers.put(question, String.valueOf(answer));


            counter--;
        }

        return mapQuestionsAnswers.entrySet().stream().limit(questionsNumber)
                .collect(HashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);

    }


}
