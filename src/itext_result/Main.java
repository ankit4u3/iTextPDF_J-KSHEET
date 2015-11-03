/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itext_result;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.SwingWorker;

/**
 *
 * @author emapps
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
    }

    private int developScreeningSheet() {
        new SwingWorker<Object, Object>() {
            String filename;

            @Override
            protected void done() {

                // ConsoleMsg("Printing PROFILE SHEET IN PROGRESS.. ");
            }

            public PdfPCell createBarcode(PdfWriter writer, String code) throws DocumentException, IOException {
                BarcodeEAN barcode = new BarcodeEAN();
                barcode.setCodeType(Barcode.EAN8);
                barcode.setCode(code);
                PdfPCell cell = new PdfPCell(barcode.createImageWithBarcode(writer.getDirectContent(), BaseColor.BLACK, BaseColor.GRAY), true);
                cell.setPadding(10);
                return cell;
            }

            class ImageContent implements PdfPTableEvent {

                protected com.itextpdf.text.Image content;

                public ImageContent(com.itextpdf.text.Image content) {
                    this.content = content;
                }

                public void tableLayout(PdfPTable table, float[][] widths,
                        float[] heights, int headerRows, int rowStart,
                        PdfContentByte[] canvases) {
                    try {
                        PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
                        float x = widths[3][1] + 10;
                        float y = heights[3] - 10 - content.getScaledHeight();
                        content.setAbsolutePosition(x, y);
                        canvas.addImage(content);
                    } catch (DocumentException e) {
                        throw new ExceptionConverter(e);
                    }
                }
            }

            @Override
            protected Object doInBackground() throws Exception {
                //   System.err.println(" Roll No Received ....." + rollno);
                Document doc = new Document();
                try {
                    PdfWriter writer = PdfWriter.getInstance(doc,
                            new FileOutputStream("12345" + ".pdf"));
                    doc.open();
                    doc.addTitle("Recruitment PET Sheett - "
                            + "Created By SOS : ");
                    doc.addSubject("Confidential Report Eyes Only");
                    doc.addKeywords("");
                    doc.addAuthor("SOS");
                    doc.addCreator("SOS");

                    // A4 = 210mm x 297mm ~ 605points x 855points
                    doc.setPageSize(PageSize.A5);
                    doc.setMargins(15f, 15f, 15f, 15f);

                    /////////////////////////////////////////////////////////////
                    int pageno = 1;

                    for (int i = 0; i == pageno; i++) {

                        //    doc.add(imageRight);
                    }

                    PdfContentByte cb = writer.getDirectContent();
                    //DONE
                    BarcodeEAN codeEAN = new BarcodeEAN();
                    codeEAN.setCodeType(Barcode.EAN8);
                    Barcode128 code128 = new Barcode128();
                    code128.setCode(String.valueOf("123456"));

                    Barcode128 code128_jacket = new Barcode128();
                    code128_jacket.setCode(String.valueOf("10345"));

                    codeEAN.setCode(String.valueOf("123456"));
                    com.itextpdf.text.Image imageEAN = code128.createImageWithBarcode(cb, null, null);
                    // imageEAN.scalePercent(10f);
                    //464f, 725f
                    //  imageEAN.setAbsolutePosition(474f, 662f);

                    int i = 1;
                    while (i <= pageno) {
                        doc.newPage();
                        //   cb.addImage(imageRight);
                        //   cb.addImage(imageEAN);
                        i++;
                    }

                    com.itextpdf.text.Image carcode = code128_jacket.createImageWithBarcode(cb, null, null);
                    carcode.scaleAbsolute(100f, 35f);
                    carcode.setAbsolutePosition(500f, 600f);
                    writer.addDirectImageSimple(carcode);
                    //     cb.addImage(carcode);

                    com.itextpdf.text.Image carcode2 = code128.createImageWithBarcode(cb, null, null);
                    carcode2.scaleAbsolute(100f, 35f);
                    carcode2.setAbsolutePosition(450f, 760f);
                    writer.addDirectImageSimple(carcode2);
                    //    cb.addImage(carcode2);

                    Image image1 = Image.getInstance("jklogo.gif");
                    
                    PdfPTable table = new PdfPTable(1);
                    table.setWidthPercentage(99);
                    table.addCell(image1);
                    doc.add(table);
                    table = new PdfPTable(4);
                    table.setWidthPercentage(99);

                    float[] columnWidths = {6, 2};
                    PdfPTable CandidateTable = new PdfPTable(columnWidths);
                    Font f = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, GrayColor.BLACK);
                    Font fsmall = new Font(Font.FontFamily.HELVETICA, 7, Font.NORMAL, GrayColor.BLACK);
                    PdfPCell rollno = new PdfPCell(new Phrase("Roll No# " + String.valueOf("123456"), fsmall));
                    PdfPCell cname = new PdfPCell(new Phrase("Name #" + String.valueOf("John Doe"), fsmall));
//                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                    String today = formatter.format(cInfo.getDob());
                    PdfPCell dob = new PdfPCell(new Phrase("DOB#" + String.valueOf("12-03-1989"), fsmall));
                    PdfPCell fname = new PdfPCell(new Phrase("F/H Name #" + String.valueOf("Big John"), fsmall));
                    PdfPCell type = new PdfPCell(new Phrase("Total Time  #" + String.valueOf("350.00"), fsmall));

                    //  String scrtoday = formatter.format(cInfo.getScreeningdate());
                    //  System.out.println("Today : " + today);
                    PdfPCell scrdate = new PdfPCell(new Phrase("Laps  #" + String.valueOf("4"), fsmall));
                    PdfPCell cell = new PdfPCell(new Phrase(" ", fsmall));

                    rollno.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    cname.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    rollno.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    dob.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    fname.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    type.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    scrdate.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    cell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);

                    CandidateTable.addCell(rollno);
                    CandidateTable.addCell(cell);
                    CandidateTable.addCell(cname);
                    CandidateTable.addCell(cell);
                    CandidateTable.addCell(dob);
                    CandidateTable.addCell(cell);

                    PdfPTable CandidateOtherTable = new PdfPTable(columnWidths);
                    CandidateOtherTable.addCell(fname);
                    CandidateOtherTable.addCell(cell);
                    CandidateOtherTable.addCell(scrdate);
                    CandidateOtherTable.addCell(cell);
                    CandidateOtherTable.addCell(type);
                    CandidateOtherTable.addCell(cell);

                    PdfPCell race_start_time = new PdfPCell(new Phrase("Start Time :XX-XX-XX ", fsmall));
                    PdfPCell race_end_time = new PdfPCell(new Phrase("End Time :XX-XX-XX", fsmall));
                    PdfPCell race_total_time = new PdfPCell(new Phrase("Total Time : 350.00   ", fsmall));
                    race_start_time.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    race_end_time.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    race_total_time.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);

                    PdfPTable CandidateRaceDetails = new PdfPTable(columnWidths);
                    CandidateRaceDetails.addCell(race_start_time);
                    CandidateRaceDetails.addCell(cell);
                    CandidateRaceDetails.addCell(race_end_time);
                    CandidateRaceDetails.addCell(cell);
                    CandidateRaceDetails.addCell(race_total_time);
                    CandidateRaceDetails.addCell(cell);

                    PdfPTable tablewith3cells = new PdfPTable(3);
                    //1 St Col for Roll No Name and DOB
                    tablewith3cells.addCell(CandidateTable);
                    //2 nd Col for Father Name sCREEning Date Gurkha 
                    tablewith3cells.addCell(CandidateOtherTable);
                    //3rd Col for Barcode to be Printed
                    tablewith3cells.addCell(CandidateRaceDetails);
                    // Setting the Width here to 101
                    tablewith3cells.setWidthPercentage(99);
                    doc.add(tablewith3cells);

                    PdfPTable userArea = new PdfPTable(1);
                    userArea.setWidthPercentage(99);
                    userArea.addCell(" \n \n Congratulations \n \n ");

                    doc.add(userArea);

                    PdfPTable footerCSBC = new PdfPTable(2);
                    footerCSBC.setWidthPercentage(99);
   
                    PdfPCell height_box = new PdfPCell(new Phrase("Height  \n\n\n", f));
                    height_box.setBorder(com.itextpdf.text.Rectangle.BOX);
                    PdfPCell chest_box = new PdfPCell(new Phrase("Chest  \n\n\n", f));
                    chest_box.setBorder(com.itextpdf.text.Rectangle.BOX);
                    PdfPCell chest_exp_box = new PdfPCell(new Phrase("Chest Exp  \n\n\n", f));
                    chest_exp_box.setBorder(com.itextpdf.text.Rectangle.BOX);
                    PdfPCell pushup_box = new PdfPCell(new Phrase("Pushup  \n\n\n", f));
                    pushup_box.setBorder(com.itextpdf.text.Rectangle.BOX);

                    //CSignatureBox.setBorder(com.itextpdf.text.Rectangle.BOX);
                    // ASignatureBox.setBorder(com.itextpdf.text.Rectangle.BOX);
                    footerCSBC.addCell(height_box);
                    footerCSBC.addCell(chest_box);
                    footerCSBC.addCell(chest_exp_box);
                    footerCSBC.addCell(pushup_box);

                    doc.add(footerCSBC);

                    float[] columnWidths_ForBarcode = {6, 3};
                    PdfPTable terminalinfo = new PdfPTable(columnWidths_ForBarcode);
                    // terminalinfo.setWidthPercentage(99);
                    String computername = InetAddress.getLocalHost().getHostName();
                    System.out.println(computername);
                    PdfPCell pcname = new PdfPCell(new Phrase("\t Jacket \n\n ", f));

                    String UserMatchScore = "\t Barcode\n\n";

                    PdfPCell score = new PdfPCell(new Phrase(UserMatchScore, f));
                    PdfPCell barcode = new PdfPCell(carcode2);
                    PdfPCell jacketnumber = new PdfPCell(carcode);
                    pcname.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    score.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    barcode.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
                    jacketnumber.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
     
                    terminalinfo.addCell(score);
                    terminalinfo.addCell(pcname);
                    terminalinfo.addCell(barcode);
                    terminalinfo.addCell(jacketnumber);

                    doc.add(terminalinfo);

                    PdfPCell eula_notice = new PdfPCell(new Phrase("  ", f));
                    eula_notice.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                    PdfPTable eula_notice_table = new PdfPTable(1);
                    eula_notice_table.setWidthPercentage(25);
                    eula_notice_table.addCell(eula_notice);

                    doc.add(eula_notice_table);

                } catch (Exception e) {
                    System.err.println(e.getMessage());

                    // ConsoleMsg(e.getMessage());
                } finally {
                    doc.close();
                    doc.close();
                    doc.close();
                }

                //ConsoleMsg("PDF... GENERATED");
                return null;
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
                .execute();

        return 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jButton1)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        developScreeningSheet();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
