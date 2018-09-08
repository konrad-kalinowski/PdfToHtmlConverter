package com.github.html2pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.assertj.core.api.AbstractByteArrayAssert;
import org.assertj.core.api.Assertions;

import java.io.IOException;

public class PdfBodyAssert extends AbstractByteArrayAssert {

    public PdfBodyAssert(byte[] actual) {
        super(actual, PdfBodyAssert.class);
    }

    public PdfBodyAssert containsText(String expected) throws IOException {
        PDDocument doc = PDDocument.load((byte[]) actual);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(doc);
        doc.close();
        Assertions.assertThat(text).contains(expected);
        return this;
    }
}
