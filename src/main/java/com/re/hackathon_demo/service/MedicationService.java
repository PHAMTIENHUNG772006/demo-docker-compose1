package com.re.hackathon_demo.service;

import com.re.hackathon_demo.model.dto.request.MedicationDTO;
import com.re.hackathon_demo.model.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicationService {
    List<Medication> getAll();
    Page<Medication> getAll(Pageable pageable);
    Medication getById(Long id);
    Medication save(Medication medication);
    boolean delete(Long id);
    Medication updateAll(Long id ,MedicationDTO medicationDTO);
    Medication updatePrice(Long id, Double price);
}
