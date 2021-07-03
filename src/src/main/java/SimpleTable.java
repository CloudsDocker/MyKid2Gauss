//import com.itextpdf.text.Document;
//import com.itextpdf.text.pdf.PdfDocument;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.sun.tools.javac.util.Name;
//import com.itextpdf.layout.property.UnitValue;
//
//import java.io.File;
//
//public class SimpleTable {
//    public static final String DEST = "./target/sandbox/tables/simple_table.pdf";
//
//    public static void main(String[] args) throws Exception {
//        File file = new File(DEST);
//        file.getParentFile().mkdirs();
//
//        new SimpleTable().manipulatePdf(DEST);
//    }
//
//    protected void manipulatePdf(String dest) throws Exception {
//        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
//        Document doc = new Document(pdfDoc);
//
//        Name.Table table = new Name.Table(UnitValue.createPercentArray(8)).useAllAvailableWidth();
//
//        for (int i = 0; i < 16; i++) {
//            table.addCell("hi");
//        }
//
//        doc.add(table);
//
//        doc.close();
//    }
//}