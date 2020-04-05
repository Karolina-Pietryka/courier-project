package com.example.courierService.service;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import com.example.courierService.model.CouriersDeliveryResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PdfService {

    private static final Path PATH_TO_DESKTOP = Paths.get(System.getProperty("user.home") + "/Desktop");
    private static final String FILE_NAME = "/Report";
    private static final String FORMAT = ".pdf";

    public void generatePdf(List<CouriersDeliveryResult> couriersDeliveryResultList) {

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);

        drawContent(doc, page, couriersDeliveryResultList);

        File file = new File(generatePathToSave().toString());

        try {
            doc.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                doc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawContent(PDDocument doc, PDPage page, List<CouriersDeliveryResult> couriersDeliveryResultList) {

        PDPageContentStream content = null;
        try {
            PDType1Font font = PDType1Font.TIMES_ROMAN;
            content = new PDPageContentStream(doc, page);

            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(250, 750);
            content.drawString("Courier delivery result report");
            content.endText();

            if (!couriersDeliveryResultList.isEmpty())
                drawTable(page, doc, couriersDeliveryResultList, font);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (content != null) {
                    content.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawTable(PDPage myPage, PDDocument mainDocument, List<CouriersDeliveryResult> couriersDeliveryResultList, PDType1Font font) throws IOException {
        float margin = 20;
        float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
        float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);

        float bottomMargin = 40;
        float yPosition = 700;

        BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage, true, true);

        Row<PDPage> headerRow = table.createRow(15);
        Cell<PDPage> cell = headerRow.createCell(10, "Number");
        cell.setFont(font);
        cell.setFillColor(Color.lightGray);

        cell = headerRow.createCell(30, "First name");
        cell.setFont(font);
        cell.setFillColor(Color.lightGray);

        cell = headerRow.createCell(30, "Last name");
        cell.setFont(font);
        cell.setFillColor(Color.lightGray);

        cell = headerRow.createCell(30, "Packages delivered");
        cell.setFont(font);
        cell.setFillColor(Color.lightGray);

        Row<PDPage> row = null;
        int count = 0;
        for (CouriersDeliveryResult element : couriersDeliveryResultList) {
            count++;
            row = table.createRow(15);
            cell = row.createCell(10, "" + count);
            cell.setFont(font);

            cell = row.createCell(30, element.getFirstName());
            cell.setFont(font);

            cell = row.createCell(30, element.getLastName());
            cell.setFont(font);

            cell = row.createCell(30, "" + element.getPackagesDelivered());
            cell.setFont(font);
        }

        row = table.createRow(15);
        cell = row.createCell(10, "");
        cell.setFont(font);

        cell = row.createCell(30, "");
        cell.setFont(font);

        cell = row.createCell(30, "");
        cell.setFont(font);

        cell = row.createCell(30, "" + sum(couriersDeliveryResultList));
        cell.setFont(font);
        cell.setFillColor(Color.lightGray);

        table.draw();
    }

    private Path generatePathToSave() {
        Path path = Paths.get(PATH_TO_DESKTOP + FILE_NAME + FORMAT);

        int count = 1;
        do {
            if (!Files.exists(path))
                return path;

            String temp = "(" + count + ")";
            path = Paths.get(PATH_TO_DESKTOP + FILE_NAME + temp + FORMAT);

            count++;
        } while (true);
    }

    private int sum(List<CouriersDeliveryResult> couriersDeliveryResultList) {
        int count = 0;
        for (CouriersDeliveryResult element : couriersDeliveryResultList) {
            count += element.getPackagesDelivered();
        }
        return count;
    }
}
