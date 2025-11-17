package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data                      // <-- Lombok: generates getters, setters, toString, equals, hashCode
@NoArgsConstructor         // <-- Lombok: no-arg constructor (required by JPA)
@AllArgsConstructor
@Entity
@Table(name = "complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complaint_number", nullable=false, unique=true)
    private String complaintNumber;

    @Column(name = "complaint_year")
    private Integer complaintYear;

    private LocalDate receivedDate;
    private LocalDate complaintDate;
    private String section;
    private Integer tableNo;
    private String complainantName;
    private String againstPartyName;
    private String subject;
    @Column(columnDefinition="TEXT")
    private String remarks;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TapalEntry> tapalEntries;

    // getters / setters / constructors
}