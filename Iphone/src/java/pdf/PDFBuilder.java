package pdf;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controllers.ControllerIphone;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phone.iphone.Souvenir;

public class PDFBuilder {

    public void generatePDF(List<Souvenir> listSouvenir, HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document();
        // step 2
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
            PdfWriter.getInstance(document, baos);
      
        document.open();
         BaseFont times =BaseFont.createFont("c:/windows/fonts/times.ttf","cp1251",BaseFont.EMBEDDED);
            Font font = new Font(times,14);
            
       
         
            
            document.add(new Paragraph("Предложение от компании Eftech dla pre "+ request.getParameter("factory"),font));
        
        for (int i = 0; i < listSouvenir.size(); i++) {
            
            PdfPTable table = new PdfPTable(2);
            
            table.addCell("Title");
            table.addCell(listSouvenir.get(i).getTitle());
            table.addCell("Price");
            table.addCell(Integer.toString(listSouvenir.get(i).getPrice()));
            table.addCell("Description");
            table.addCell(listSouvenir.get(i).getDescription());
           
                document.add(new Paragraph("Товар " + i,font));
                document.add(table);
         
        }
        document.close();
        response.addHeader("Content-Disposition", "attachment; filename=offer.pdf");//Disposition -расположение
        response.setContentType("application/forse-download");
        response.setContentLength(baos.size());
        OutputStream os;
       
            os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
       
        document.close();
    }

}
