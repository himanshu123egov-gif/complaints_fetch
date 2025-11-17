package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data                      // <-- Lombok: generates getters, setters, toString, equals, hashCode
@NoArgsConstructor         // <-- Lombok: no-arg constructor (required by JPA)
@AllArgsConstructor
@Entity
@Table(name = "tapal_entries")
public class TapalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="complaint_id")
    @JsonIgnore
    private Complaint complaint;

    private LocalDate letterReceivedDate;
    private String subjectLine;
    private String letterFrom;
    @Column(columnDefinition="TEXT")
    private String remarks;
    private LocalDateTime createdAt;
    // getters / setters / constructors
}
