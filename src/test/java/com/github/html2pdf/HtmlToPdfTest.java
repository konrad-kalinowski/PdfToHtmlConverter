package com.github.html2pdf;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


class HtmlToPdfTest {

    @Test
    void createHtml() throws IOException {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("key1", "var1");
        attributes.put("key2", "var2");
        attributes.put("key3", "var3");
        String template = IOUtils.toString(HtmlToPdf.class.getResourceAsStream("/htmlTemplate.html"));
        HtmlToPdf htmlToPdf = new HtmlToPdf();

        Assertions.assertThat(htmlToPdf.createHtml(attributes,template)).contains("var1", "var2", "var3");
    }

    @Test
    void createPdf() throws IOException {
        HtmlToPdf htmlToPdf = new HtmlToPdf();

        byte[] pdf = htmlToPdf.createPdf("<p>test</p>");
        String s = new String(pdf);
        System.out.println(s);

        new PdfBodyAssert(pdf).containsText("test");
    }
}