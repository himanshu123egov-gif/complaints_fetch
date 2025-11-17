package com.example.demo.controllers;

import com.example.demo.entities.Complaint;
import com.example.demo.services.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // Showing search page (form)
    @GetMapping("/")
    public String home() {
        return "report-home";
    }

    // Search by Complaint Number
    @GetMapping("/complaint")
    public String getByComplaintNumber(@RequestParam String complaintNumber, Model model) {

        Optional<Complaint> c = reportService.findByComplaintNumber(complaintNumber);

        if (c.isEmpty()) {
            model.addAttribute("error", "Complaint not found!");
            return "report-result";
        }

        model.addAttribute("complaints", java.util.List.of(c.get()));
        return "report-result";
    }

    // Search by Table Number
    @GetMapping("/table/{tableNo}")
    public String getByTableNo(
            @PathVariable Integer tableNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            Model model) {

        Page<Complaint> result = reportService.findByTableNo(tableNo, PageRequest.of(page, size));

        model.addAttribute("complaints", result.getContent());
        model.addAttribute("page", result);

        return "report-result";
    }
}