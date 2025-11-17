package com.example.demo.services;

import com.example.demo.entities.Complaint;
import com.example.demo.repositories.ComplaintRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService {

    private final ComplaintRepository complaintRepo;

    public ReportService(ComplaintRepository complaintRepo) {
        this.complaintRepo = complaintRepo;
    }

    public Optional<Complaint> findByComplaintNumber(String complaintNumber) {
        return complaintRepo.findByComplaintNumber(complaintNumber);
    }

    public Page<Complaint> findByTableNo(Integer tableNo, Pageable pageable) {
        return complaintRepo.findByTableNo(tableNo, pageable);
    }
}
