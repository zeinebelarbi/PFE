package com.example.managingfoodreservation.controller;

import com.example.managingfoodreservation.model.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
@RestController
public interface BillController {
    @PostMapping(path="/generatedReport")
    ResponseEntity<String>generateReport(@RequestBody Map<String,Object> requestMap);
    @GetMapping(path ="/getBills")
    ResponseEntity<List<Bill>>getBills();
    @PostMapping(path="/getPdf")
    ResponseEntity<byte[]>getPdf(@RequestBody Map<String,Object> requestMap);
    @PostMapping(path="/delete/{idbill}")
    ResponseEntity<String>deleteBill(@PathVariable Integer idbill);
}
