package com.example.demo.controllers;

import com.example.demo.entities.Complaint;
import com.example.demo.services.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController   // <-- IMPORTANT: You forgot this annotation earlier
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/complaint")
    public ResponseEntity<Object> getByComplaintNumber(@RequestParam String complaintNumber) {
        Optional<Complaint> c = reportService.findByComplaintNumber(complaintNumber);

        if (c.isPresent()) return ResponseEntity.ok(c.get());
        return ResponseEntity.status(404).body("Complaint not found");
    }


    @GetMapping("/table/{tableNo}")
    public ResponseEntity<Page<Complaint>> getByTableNo(
            @PathVariable Integer tableNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<Complaint> result = reportService.findByTableNo(tableNo, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }
}
