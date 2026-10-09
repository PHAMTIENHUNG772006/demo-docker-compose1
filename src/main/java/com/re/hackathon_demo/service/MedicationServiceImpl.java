package com.re.hackathon_demo.service;

import com.re.hackathon_demo.model.dto.request.MedicationDTO;
import com.re.hackathon_demo.model.entity.Medication;
import com.re.hackathon_demo.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService{

    private final MedicationRepository medicationRepository;

    @Override
    public List<Medication> getAll() {
        return medicationRepository.findAll();
    }

    @Override
    public Page<Medication> getAll(Pageable pageable) {
        return medicationRepository.findAll(pageable);
    }

    @Override
    public Medication getById(Long id) {
        return medicationRepository.findById(id).orElse(null);
    }

    @Override
    public Medication save(Medication medication) {
        medication.setIs_deleted(false);
        return medicationRepository.save(medication);
    }

    @Override
    public boolean delete(Long id) {

        Medication medication = getById(id);

        if (medication == null) {
            return false;
        }

        medication.setIs_deleted(true);
        return true;
    }

    @Override
    public Medication updateAll(Long id,MedicationDTO medicationDTO) {
        Medication medicationEntity = medicationRepository.findById(id).orElse(null);

        if(medicationEntity != null) {
            medicationEntity.setId(id);
            medicationEntity.setName(medicationDTO.getName());
            medicationEntity.setManufacturer(medicationDTO.getManufacturer());
            medicationEntity.setPrice(medicationDTO.getPrice());
            return medicationRepository.save(medicationEntity);
        }

        return null;
    }

    @Override
    public Medication updatePrice(Long id, Double price) {
        Medication medicationEntity = medicationRepository.findById(id).orElse(null);

        if(medicationEntity != null) {
            medicationEntity.setPrice(price);
            return medicationRepository.save(medicationEntity);
        }

        return null;
    }
}
