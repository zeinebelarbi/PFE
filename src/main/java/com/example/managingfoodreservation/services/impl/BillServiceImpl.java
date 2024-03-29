package com.example.managingfoodreservation.services.impl;

import com.example.managingfoodreservation.JWT.JwtFilter;
import com.example.managingfoodreservation.Repository.BillRepository;
import com.example.managingfoodreservation.constants.MenuConstants;
import com.example.managingfoodreservation.model.Bill;
import com.example.managingfoodreservation.services.BillService;
import com.example.managingfoodreservation.utils.MenuUtils;
import com.google.gson.internal.LinkedTreeMap;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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
                String data = "Name: " + requestMap.get("name") +  "\n"  + "Payement Method: " + requestMap.get("paymentMethod")+"\n";
                System.out.println(data);
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(MenuConstants.STORE_LOCATION + "\\" + fileName + ".pdf"));

                document.open();
                setRectangleInPdf(document);
                Paragraph chunk = new Paragraph("WEVIOO CANTEEN ", getFont("Header"));

                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);
                Paragraph paragraph = new Paragraph(data + "\n\n", getFont("Data"));
                document.add(paragraph);
                PdfPTable table = new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);
                JSONArray jsonArray = null;
                if (requestMap.get("dishDetails") instanceof JSONArray) {
                    jsonArray = (JSONArray) requestMap.get("dishDetails");
                } else if (requestMap.get("dishDetails") instanceof String) {
                    jsonArray = MenuUtils.getJsonAArrayFromString((String) requestMap.get("dishDetails"));
                } else if (requestMap.get("dishDetails") instanceof List) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonString = objectMapper.writeValueAsString(requestMap.get("dishDetails"));
                    jsonArray = MenuUtils.getJsonAArrayFromString(jsonString);
                }

                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        addRows(table, MenuUtils.getMapFromJson(jsonArray.getString(i)));
                    }
                }
                document.add(table);
                Paragraph footer = new Paragraph("Total :" + requestMap.get("totalAmount") + "\n" + "Thank you for visiting, Please visit again !", getFont("Data"));
//                Paragraph footer = new Paragraph("Total :" + requestMap.get("quantity") + "\n" + "Thank you for visiting, Please visit again !", getFont("Data"));
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
    private void addRows(PdfPTable table, Map<String, Object> dish) {

        LinkedTreeMap<String,Object> dishDetails= (LinkedTreeMap<String, Object>) dish.get("dishname");
        Double subTotal= (double) 0;
       // System.out.println(((Dish) dish.get("dishname")).getDishname());
        if (dishDetails.containsKey("name") && dishDetails.get("name") != null) {
            table.addCell(dishDetails.get("name").toString());
        } else {
            table.addCell("");
        }

        if (dishDetails.containsKey("menucategoryname") && dishDetails.get("menucategoryname") != null) {
            table.addCell(dishDetails.get("menucategoryname").toString());
        } else {
            table.addCell("");
        }

        if (dish.containsKey("quantity") && dish.get("quantity") != null) {
            table.addCell(dish.get("quantity").toString());
            if (dishDetails.containsKey("price") && dishDetails.get("price") != null) {
                table.addCell(dishDetails.get("price").toString());
                subTotal=Double.parseDouble(dish.get("quantity").toString()) * Float.valueOf(dishDetails.get("price").toString());
                table.addCell(subTotal.toString());

            }
            else {
                table.addCell("");
            }
        } else {
            table.addCell("");
        }


//        if (dishDetails.containsKey("total") && dishDetails.get("total") != null) {
//            table.addCell(dishDetails.get("total").toString());
//        } else {
//            table.addCell("");
//        }
//        table.addCell(((Dish) dish.get("dishname")).getDishname());
//        table.addCell("55");
//        table.addCell("55");
//        table.addCell("55");
//        table.addCell("55");
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
        rect.setBorderColor(BaseColor.BLACK);
        document.add(rect);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try {
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setBillname((String) requestMap.get("billname"));
            bill.setPayementmethod((String) requestMap.get("payment Method"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
           // bill.setQuantity("3");
//            bill.setDishDetail((String) requestMap.get("dishDetails"));
            bill.setCreatedby(jwtFilter.getCurrentUser());
            billRepository.save(bill);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return requestMap.containsKey("name")  && requestMap.containsKey("paymentMethod") && requestMap.containsKey("dishDetails") && requestMap.containsKey("totalAmount");
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
            list = billRepository.getAllBillByUsername(jwtFilter.getCurrentUser());

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