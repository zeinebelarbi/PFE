package com.example.managingfoodreservation.services.impl;

import com.example.managingfoodreservation.JWT.JwtFilter;
import com.example.managingfoodreservation.Repository.BillRepository;
import com.example.managingfoodreservation.constants.MenuConstants;
import com.example.managingfoodreservation.model.Bill;
import com.example.managingfoodreservation.services.BillService;
import com.example.managingfoodreservation.utils.MenuUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class BillServiceImpl implements BillService {

    @Autowired
    JwtFilter jwtFilter;
    @Autowired
    BillRepository billRepository;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generate report");
        try {
            String fileName;
            if (validateRequestMap(requestMap)) {
                if (requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate")) {
                    fileName = (String) requestMap.get("uuid");

                } else {
                    fileName = MenuUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);
                }
                String data = "Name: " + requestMap.get("name") + "\n" + "Contact Number:" + requestMap.get("contactNumber") + "\n" + "Email: " + requestMap.get("email") + "\n" + "Payement Method: " + requestMap.get("payement Method ");

                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(MenuConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                document.open();
                setRectangleInPdf(document);
                Paragraph chunk = new Paragraph("Food management System ", getFont("Header"));

                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);
                Paragraph paragraph = new Paragraph(data + "\n\n", getFont("Data"));
                document.add(paragraph);
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);
                JSONArray jsonArray = MenuUtils.getJsonAArrayFromString((String) requestMap.get("dishDetails"));
                for (int i = 0; i < jsonArray.length(); i++) {
                    addRows(table, MenuUtils.getMapFromJson(jsonArray.getString(i)));
                }
                document.add(table);
                Paragraph footer = new Paragraph("Total :" + requestMap.get("totalAmount") + "\n" + "Thank you for visiting, Please visit again !", getFont("Data"));
                document.add(footer);
                document.close();
                return new ResponseEntity<>("{\"uuid\":\"" + fileName + "\"}", HttpStatus.OK);
            }
            return MenuUtils.getResponseEntity("Required data not found ", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {


            ex.printStackTrace();

        }
        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private void addRows(PdfPTable table, Map<String, Object> data) {
        log.info("Inside addRows");
        table.addCell((String) data.get("name"));
        table.addCell((String) data.get("MenuCategory"));
        table.addCell((String) data.get("Quantity"));
        table.addCell((String) data.get("Price"));
        table.addCell((String) data.get("Sub Total"));
        table.addCell(Double.toString((Double) data.get("Price")));
        table.addCell(Double.toString((Double) data.get("Total")));
    }

    private void addTableHeader(PdfPTable table) {
        log.info("Inside addTableHeader");
        Stream.of("Name", "MenuCategory", "Quantity", "Price", "Sub Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private void setRectangleInPdf(Document document) throws DocumentException {
        log.info("Inside setRectangleInPdf");
        Rectangle rect = new Rectangle(577, 825, 18, 15);
        rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBackgroundColor(BaseColor.BLACK);
        document.add(rect);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setBillname((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPayementmethod((String) requestMap.get("payment Method"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            bill.setDishDetail((String) requestMap.get("dishDetails"));
            bill.setCreatedby(jwtFilter.getCurrentUser());
            billRepository.save(bill);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email") && requestMap.containsKey("paymentMethod") && requestMap.containsKey("dishDetails") && requestMap.containsKey("totalAmount");

    }

    private Font getFont(String type) {
        log.info("Inside getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font();
        }
    }

    @Override
    public ResponseEntity<List<Bill>> getBills() {
        List<Bill> list = new ArrayList<>();
        if (jwtFilter.isAdmin()) {
            list = billRepository.getAllBills();
        } else {
            list = billRepository.getAllBillByUserName(jwtFilter.getCurrentUser());

        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        log.info("Inside getpdf : requestMap{}", requestMap);

        try {
            byte[] byteArray = new byte[0];
            if (!requestMap.containsKey("uuid") && validateRequestMap(requestMap))
                return new ResponseEntity<>(byteArray, HttpStatus.BAD_REQUEST);
                String filePath = MenuConstants.STORE_LOCATION + "\\" + (String) requestMap.get("uuid") + ".pdf";
if (MenuUtils.isFileExist(filePath)){
    byteArray =getByteArray(filePath);
    return new ResponseEntity<>(byteArray,HttpStatus.OK);
}else{
    requestMap.put("isGenerate",false);
    generateReport(requestMap);
    byteArray = getByteArray(filePath);
    return new ResponseEntity<>(byteArray,HttpStatus.OK);
}
        }
            catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }



    private byte[] getByteArray(String filePath) throws Exception{
    File initialFile = new File(filePath);
    InputStream targetStream = new FileInputStream(initialFile);
    byte[] byteArray =  IOUtils.toByteArray(targetStream);
        targetStream.close();
return byteArray;
    }
    @Override
    public ResponseEntity<String> deleteBill(Integer idbill) {
        try{
    Optional optional = billRepository.findById(idbill);
       if(!optional.isEmpty()){
billRepository.deleteById(idbill);
return MenuUtils.getResponseEntity("Bill deleted successfully",HttpStatus.OK);
       }
return MenuUtils.getResponseEntity("Bill id does not exist",HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }


        return MenuUtils.getResponseEntity(MenuConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR) ;
    }
}

