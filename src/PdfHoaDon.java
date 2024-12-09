/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import Model.ChiTietHoaDon;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

/**
 *
 * @author Manh
 */
public class PdfHoaDon {

    public void xuatHoaDonPDF(String maHoaDon, String tenKhachHang, List<ChiTietHoaDon> dsChiTietHoaDon, BigDecimal tongTien) {
        String fileName = "HoaDon_" + maHoaDon + ".pdf";
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(fileName));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Tiêu đề hóa đơn
            Paragraph title = new Paragraph("HÓA ĐƠN MUA HÀNG")
                    .setBold()
                    .setFontSize(18)
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.CENTER);
            document.add(title);

            // Thông tin khách hàng
            Paragraph info = new Paragraph("Mã Hóa Đơn: " + maHoaDon + "\n"
                    + "Tên Khách Hàng: " + tenKhachHang + "\n"
                    + "------------------------------------------");
            document.add(info);

            // Tạo bảng chi tiết hóa đơn
            float[] columnWidths = {100f, 200f, 100f, 100f};
            Table table = new Table(columnWidths);

            // Tiêu đề cột
            table.addCell(new Cell().add(new Paragraph("Mã SP")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Tên Sản Phẩm")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Số Lượng")).setTextAlignment(TextAlignment.CENTER));
            table.addCell(new Cell().add(new Paragraph("Thành Tiền")).setTextAlignment(TextAlignment.CENTER));

// Thêm các dòng chi tiết hóa đơn
            for (ChiTietHoaDon chiTiet : dsChiTietHoaDon) {
                String sanPhamId = chiTiet.getSanPham() != null ? String.valueOf(chiTiet.getSanPham().getSanPhamId()) : "N/A";
table.addCell(new Cell().add(new Paragraph(sanPhamId)));
                table.addCell(new Cell().add(new Paragraph(chiTiet.getSanPham().getTenSanPham())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(chiTiet.getSoLuong()))));
                table.addCell(new Cell().add(new Paragraph(chiTiet.getThanhTien().toString())));
            }

            // Tổng tiền
            document.add(table);
            Paragraph total = new Paragraph("Tổng Tiền: " + tongTien + " VND")
                    .setBold()
                    .setTextAlignment(com.itextpdf.layout.property.TextAlignment.RIGHT);
            document.add(total);

            document.close();
            System.out.println("Hóa đơn đã được xuất ra file PDF: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//try {
//            SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
//            Calendar cal = Calendar.getInstance();
//
//        } catch (Exception e) {
//        }
//        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
//        try {
//            PdfWriter.getInstance(doc, new FileOutputStream(InventoryUtils.billPath + "" + "hoadon" + ".pdf"));
//            doc.open();
//            Paragraph projectName = new Paragraph("Hoa don dien tu");
//            doc.add((Element) projectName);
//            Paragraph starline = new Paragraph("*******************************");
//            doc.add((Element) starline);
//            Paragraph details = new Paragraph("\tOrder ID:" + "1" + "\nDate:" + "11/27/2024" + "\nToltal Paid:" + "10000");
//            doc.add((Element) details);
//            doc.add((Element) starline);
//            PdfPTable tbl = new PdfPTable(5);
//            PdfPCell nameCell = new PdfPCell(new Phrase("Name"));
//            PdfPCell mota = new PdfPCell(new Phrase("mo ta"));
//            PdfPCell giaban = new PdfPCell(new Phrase("Gia ban"));
//            PdfPCell soluong = new PdfPCell(new Phrase("So luong"));
//            PdfPCell thanhtien = new PdfPCell(new Phrase("Thanh tien"));
//
//            BaseColor backgroundColor = new BaseColor(255, 204, 51);
//            nameCell.setBackgroundColor(backgroundColor);
//            mota.setBackgroundColor(backgroundColor);
//            giaban.setBackgroundColor(backgroundColor);
//            soluong.setBackgroundColor(backgroundColor);
//            thanhtien.setBackgroundColor(backgroundColor);
//
//            tbl.addCell(nameCell);
//            tbl.addCell(mota);
//            tbl.addCell(giaban);
//            tbl.addCell(soluong);
//            tbl.addCell(thanhtien);
//
//            for (int i = 0; i < tblChiTietHoaDon.getColumnCount(); i++) {
//                tbl.addCell(tblChiTietHoaDon.getValueAt(i, 1).toString());
//                tbl.addCell(tblChiTietHoaDon.getValueAt(i, 2).toString());
//                tbl.addCell(tblChiTietHoaDon.getValueAt(i, 3).toString());
//                tbl.addCell(tblChiTietHoaDon.getValueAt(i, 4).toString());
//                tbl.addCell(tblChiTietHoaDon.getValueAt(i, 5).toString());
//            }
//            doc.add(tbl);
//            doc.add((Element) starline);
//            OpenPdf.OpenById(orderId);
//            doc.close();
//            setVisible(false);
//            try {
//                new TaoHoaDon1().setVisible(true);
//            } catch (SQLException ex) {
//                Logger.getLogger(TaoHoaDon1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//        }
