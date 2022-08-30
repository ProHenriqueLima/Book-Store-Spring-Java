package com.api.bookstore.models.pdf;

import com.api.bookstore.models.BookModel;
import com.api.bookstore.models.RentModel;
import com.api.bookstore.models.UserModel;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RentPdf {
    public RentPdf(UserModel userModel, BookModel bookModel, RentModel rentModel, HttpServletResponse response) throws IOException {
        Document documentPdf = new Document();
        try {
            PdfWriter.getInstance(documentPdf, response.getOutputStream());
            documentPdf.open();
            Paragraph title = new Paragraph();
            title.setAlignment(Element.ALIGN_CENTER);
            title.add(
                    new Chunk(
                            "Relatório do Aluguel",
                            new Font(Font.HELVETICA, 24)
                    )
            );

            title.setSpacingAfter(20);

            documentPdf.add(title);

            //Nome do Aluguel
            Paragraph dataRent = new Paragraph();
            dataRent.add(
                    new Chunk(
                            "Dados do Aluguel",
                            new Font(Font.TIMES_ROMAN,16)
                    )
            );
            dataRent.setAlignment(Element.ALIGN_CENTER);
            dataRent.setSpacingAfter(20);
            documentPdf.add(dataRent);

            //Dados do Aluguel

            Paragraph rentDate = new Paragraph();
            rentDate.add(
                    new Chunk(
                            "Data do Aluguel : "+rentModel.getDateRent(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            rentDate.setSpacingAfter(5);
            documentPdf.add(rentDate);

            Paragraph predictionDate = new Paragraph();
            predictionDate.add(
                    new Chunk(
                            "Previsão de Entrega : "+rentModel.getPrediction(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            predictionDate.setSpacingAfter(5);
            documentPdf.add(predictionDate);

            //Nome do Cliente
            Paragraph dataUser = new Paragraph();
            dataUser.add(
                    new Chunk(
                            "Dados do Cliente",
                            new Font(Font.TIMES_ROMAN,16)
                    )
            );
            dataUser.setAlignment(Element.ALIGN_CENTER);
            dataUser.setSpacingAfter(20);
            documentPdf.add(dataUser);


            //Dados do cliente

            Paragraph nameUser = new Paragraph();
            nameUser.add(
                    new Chunk(
                            "Nome do Cliente : "+userModel.getName(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            nameUser.setSpacingAfter(5);
            documentPdf.add(nameUser);

            Paragraph adressUser = new Paragraph();
            adressUser.add(
                    new Chunk(
                            "Endereço : "+userModel.getAndressUser()+", "+userModel.getCityUser(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            adressUser.setSpacingAfter(5);
            documentPdf.add(adressUser);

            Paragraph emailUser = new Paragraph();
            emailUser.add(
                    new Chunk(
                            "Email : "+userModel.getEmail(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            emailUser.setSpacingAfter(20);
            documentPdf.add(emailUser);

            //Nome do Livro
            Paragraph dataBook = new Paragraph();
            dataBook.add(
                    new Chunk(
                            "Dados do Livro",
                            new Font(Font.TIMES_ROMAN,16)
                    )
            );
            dataBook.setAlignment(Element.ALIGN_CENTER);
            dataBook.setSpacingAfter(20);
            documentPdf.add(dataBook);


            //Dados do cliente

            Paragraph nameBook = new Paragraph();
            nameBook.add(
                    new Chunk(
                            "Nome do Livro : "+bookModel.getName(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            nameBook.setSpacingAfter(5);
            documentPdf.add(nameBook);

            Paragraph authorBook = new Paragraph();
            authorBook.add(
                    new Chunk(
                            "Autor : "+bookModel.getAuthor(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            authorBook.setSpacingAfter(5);
            documentPdf.add(authorBook);

            Paragraph bookLaunch = new Paragraph();
            bookLaunch.add(
                    new Chunk(
                            "Data de Lançamento : "+bookModel.getLaunch(),
                            new Font(Font.TIMES_ROMAN,14)
                    )
            );
            bookLaunch.setSpacingAfter(5);
            documentPdf.add(bookLaunch);

            Paragraph row = new Paragraph("==========================================================================");
            row.setAlignment(Element.ALIGN_CENTER);
            documentPdf.add(row);
            documentPdf.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
