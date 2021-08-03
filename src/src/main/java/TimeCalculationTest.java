import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class TimeCalculationTest {
    private static final DecimalFormat format = new DecimalFormat("#.####");

    public static void main(String[] args) {
        System.out.println("===Time calculation,====");

        String fileName = "TimeCalculation";
        String description = "Fished lunch at 2.15pm, it took 30 minutes and 20 minutes watch TV, when started";
        Map<String, String> mapQuestions = buildQuestions(10);
        PDFCreator pdfCreator = new PDFCreator(fileName, description);
        List<String> listQuestions = mapQuestions.keySet().stream().collect(Collectors.toList());
        Collections.shuffle(listQuestions);
        List<String> listAnswers = getAnswers(mapQuestions, listQuestions);
        String filePath = pdfCreator.outputPdf(listQuestions, listAnswers, 1);

        System.out.println("====output file:" + filePath);

    }

    private static List<String> getAnswers(Map<String, String> mapQuestions, List<String> listQuestions) {
        return listQuestions.stream().map(it -> mapQuestions.get(it)).collect(Collectors.toList());
    }


    private static Map<String, String> buildQuestions(int questionsNumber) {
        Map<String, String> mapQuestionsAnswers = new HashMap<>();


        for (int i = 0; i < questionsNumber; i++) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime today = LocalDateTime.of(2000, 1, 1, getRandom(23), getRandom(59));
//            LocalDateTime today = now.withHour(getRandom(23));
//            today = today.withMinute(getRandom(59));
            System.out.println("now date is :" + today);
            int h1 = getRandom(4);
            int m1 = getRandom(60);
            int m2 = getRandom(60);

            getTimeQuestion(mapQuestionsAnswers, today, h1, m1, m2, new Random().nextBoolean());


        }
        return mapQuestionsAnswers;

    }

    private static void getTimeQuestion(Map<String, String> mapQuestionsAnswers, LocalDateTime today, int h1, int m1, int m2, boolean forwardCalculation) {
        String name = getRandomLabel(new String[]{"Tom", "Jerry", "Frank", "Ben", "Charlie"});
        Object time1 = today.format(DateTimeFormatter.ofPattern("hh:mm"));

        String questionForward = String.format("%s arrive at home at %s, " +
                        "he then played with friends for %d hour and %d minuets, " +
                        "then took %d minutes for lunch. What's the time now?",
                name, time1, h1, m1, m2);

        String questionBackward = String.format("After %s arrived home, he done several activities, while current time is %s,  " +
                        "he've played with friends for %d hour and %d minuets, " +
                        "then took %d minutes for lunch. When he arrived at home?",
                name, time1, h1, m1, m2);

        LocalDateTime answerForward = today.plusHours(h1).plusMinutes(m1).plusMinutes(m2);
        LocalDateTime answerBackward = today.minusHours(h1).minusMinutes(m1).minusMinutes(m2);

        if (forwardCalculation) {
            mapQuestionsAnswers.put(questionForward, answerForward.format(DateTimeFormatter.ofPattern("hh:mm")));
        } else {
            mapQuestionsAnswers.put(questionBackward, answerBackward.format(DateTimeFormatter.ofPattern("hh:mm")));
        }

    }

    private static String getRandomLabel(String[] strings) {
        return strings[new Random().nextInt(strings.length - 1)];
    }

    private static int getRandom(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }
}
