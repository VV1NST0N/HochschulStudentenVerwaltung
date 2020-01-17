package unitTests;

import com.itextpdf.text.DocumentException;
import helper.IdCardGen;

import java.io.IOException;

public class TestPdf {

    public static void main(String[] args) throws IOException, DocumentException {

        IdCardGen idCardGen = new IdCardGen();
        String[] text = {"Vorname: aasd", "Nachname: blabla"};

        idCardGen.convertToFile(text);

    }
}
