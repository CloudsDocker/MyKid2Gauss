import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PDFCreator {
    private static Font fontTitle = new Font(Font.FontFamily.TIMES_ROMAN, 28,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Font contentText = new Font(Font.FontFamily.COURIER, 25);
    private String filePath;

    public PDFCreator(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {
        System.out.println("==output pdf file===");
        PDFCreator pdfCreator = new PDFCreator("./pdfDoc/enya.pdf");
        List<String> listQuestions = createDummyQuestions();
        pdfCreator.outputPdf(listQuestions, null);

    }

    private static List<String> createDummyQuestions() {
        List list = new ArrayList<String>();
        for (int i = 0; i < 51; i++) {
            list.add(String.format("%d x %d = ", i, i));
        }
        return list;
    }

    public void outputPdf(List<String> listQuestions, List<String> listAnswers) {
        prepareFilePath(filePath);
        Document document = new Document();
        final Paragraph NEW_LINE = new Paragraph();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            Paragraph preface = createTitle();
            Paragraph paraTable = createQuestionsTable(listQuestions, listAnswers);

            document.add(preface);
            document.add(paraTable);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Paragraph createTitle() {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph("Math sheet for Enya: Quick calculation", fontTitle));
        preface.add(new Paragraph("Created at " + LocalDateTime.now().toString(), smallBold));
        preface.add(new Paragraph("Timing: start time:_____________  finish time: _____________", smallBold));
        preface.add(newLine());
        return preface;
    }

    private Paragraph createQuestionsTable(List<String> listQuestions, List<String> listAnswers) {
//        Chapter chapter = new Chapter(new Paragraph(), 1);
        Paragraph paraTable = new Paragraph("All questions ", contentText);
//        Section section = chapter.addSection(paraTable);

        int column = 3;
        PdfPTable table = new PdfPTable(column);
//        IntStream.range(0,12).forEach();
//        table.addCell("");
        for (int i = 0; i < listQuestions.size(); i++) {
            table.addCell(listQuestions.get(i));
        }
        paraTable.add(table);

//        chapter.addSection(new Paragraph("===answers===="));
        Paragraph paraTable2 = new Paragraph("Answers ", contentText);

//        Section section2 = chapter.addSection(paraTable2);

        PdfPTable table2 = new PdfPTable(column);
//        IntStream.range(0,12).forEach();
//        table.addCell("");
        for (int i = 0; i < listAnswers.size(); i++) {
            table2.addCell(listAnswers.get(i));
        }
//        section2.add(table2);
        paraTable2.add(table2);
        paraTable.add(paraTable2);

        return paraTable;
//        document.add(paraTable);
    }

    private Paragraph newLine() {
        Paragraph preface = new Paragraph();
        preface.add(new Paragraph(" "));
        preface.add(new Paragraph(" "));
        preface.add(new Paragraph(" "));
        return preface;
    }

    private void prepareFilePath(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

    }
}
