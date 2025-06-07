/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts;

/**
 *
 * @author empichi
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.myapp.struts.dao.FighterDAO;
import com.myapp.struts.model.Fighter;
import com.myapp.struts.model.Script;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ExportarScriptsPDFAction extends ActionSupport {

    private InputStream inputStream;

    @Override
    public String execute() {
        try {
            System.out.println("> Entrando a ExportarScriptsPDFAction");

            Map<String, Object> session = ActionContext.getContext().getSession();
            Fighter user = (Fighter) session.get("usuario");

            System.out.println("> Usuario en sesión: " + user);

            if (user == null) {
                System.out.println("> Usuario no logueado, abortando PDF.");
                return ERROR;
            }

            List<Script> scripts = FighterDAO.getScriptsByFighterId(user.getFighter_id());
            System.out.println("> Scripts encontrados: " + scripts.size());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            document.add(new Paragraph("> Lista de Scripts", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            document.add(new Paragraph("Usuario: " + user.getUsername()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.addCell("ID");
            table.addCell("Título");
            table.addCell("Fecha de Creación");

            for (Script s : scripts) {
                table.addCell(String.valueOf(s.getScript_id()));
                table.addCell(s.getTitle());
                table.addCell(s.getCreated_at().toString());
            }

            document.add(table);
            document.close();

            inputStream = new ByteArrayInputStream(baos.toByteArray());
            System.out.println("✅ PDF generado correctamente");
            return SUCCESS;

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
