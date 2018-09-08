package com.github.html2pdf;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class HtmlToPdf {

    public String createHtml(Map<String, Object> attributes, String template) {
        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode("XHTML");
        final TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        final Context context = new Context();
        attributes.entrySet().forEach(entry -> context.setVariable(entry.getKey(), entry.getValue()));
        return engine.process(template, context);

    }

    public byte[] createPdf(String processedHtml) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os);
            return os.toByteArray();
        } catch (IOException | DocumentException e) {
            throw new IllegalStateException("Failed to create pdf ", e);

        }

    }

    public byte[] createPdf(Map<String, Object> attributes, String template) {
        String html = createHtml(attributes, template);
        return createPdf(html);
    }
}