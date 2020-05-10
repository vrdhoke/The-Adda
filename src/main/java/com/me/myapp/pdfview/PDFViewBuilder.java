package com.me.myapp.pdfview;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.me.myapp.pojo.Event;


public class PDFViewBuilder extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        List<Event> listEvent = (List<Event>) model.get("registeredevent");
         
        doc.add(new Paragraph("Boston Health Care System"));
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        // write table header
        cell.setPhrase(new Phrase("Event's Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Event Details", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Event Date", font));
        table.addCell(cell);
         
//        cell.setPhrase(new Phrase("Published Date", font));
//        table.addCell(cell);
//         
//        cell.setPhrase(new Phrase("Price", font));
//        table.addCell(cell);
         
        // write table row data
        Chunk ch2 = new Chunk();
        for (Event event : listEvent) {
//            table.addCell(aBook.getDosage());
//            table.addCell(aBook.getPrescription());
//            table.addCell(aBook.getMedHistory());
//            table.addCell(aBook.getOtherMedicine());
                      
    		ch2.append("\n Event's Name:	" + event.getEventname());
    		ch2.append("\n Event Details:		" + event.getDetails());
    		ch2.append("\n Event Date:		" + event.getEvent_date());            
        }
        doc.add(ch2);
        
        
        String dest = "C:/itextExamples/addingImage.pdf";       
//         writer = new PdfWriter(dest);        
//        
//        // Creating a PdfDocument       
//        PdfDocument pdf = new PdfDocument(writer);              
//        
//        // Creating a Document        
//        Document document = new Document(pdf);              
//        
//        // Creating an ImageData object       
//        String imFile = "C:/itextExamples/logo.jpg";       
//        ImageData data = ImageDataFactory.create(imFile);              
//        
//        // Creating an Image object        
//        Image image = new Image(data);                        
//        
        // Adding image to the document       
//        document.add(image);            
         
    }
 
}