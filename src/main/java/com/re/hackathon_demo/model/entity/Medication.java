package com.re.hackathon_demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medications")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String manufacturer;
    private Double price;
    private MedicationStatus status;
    private Boolean is_deleted;
}
