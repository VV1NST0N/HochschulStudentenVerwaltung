package helper;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class IdCardGen {

    /*
       TODO change whole IText Implementation to add THM logo and image -> https://itextpdf.com/en/resources/examples/itext-7/adding-images-table
    */
    String BASE_PATH = System.getProperty("user.home") + "\\Documents\\ID_Cards\\";
    public static final String DEST =  "/myfiles/test.pdf";

    public void convertToFile(String[] args) throws IOException, DocumentException {

        PdfDocument pdf = new PdfDocument();

        Rectangle rectangle = new Rectangle(100, 144,123,123);

        Document document = new Document(rectangle);

        OutputStream outputStream = new FileOutputStream(BASE_PATH + DEST);
        for (String line: args) {
            document.add(new Paragraph(line));
        }
        pdf.addWriter(PdfWriter.getInstance(document, outputStream));
        document.close();

    }


}
