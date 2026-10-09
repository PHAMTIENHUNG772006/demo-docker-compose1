package com.re.hackathon_demo.controller;

import com.re.hackathon_demo.model.dto.request.MedicationDTO;
import com.re.hackathon_demo.model.dto.response.ApiDataResponse;
import com.re.hackathon_demo.model.entity.Medication;
import com.re.hackathon_demo.model.entity.MedicationStatus;
import com.re.hackathon_demo.service.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medications")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;


    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Medication>>> getAllMedication(){

        List<Medication> medications = medicationService.getAll();

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách thuốc",
                medications,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<List<Medication>>> deleteMedication(@PathVariable Long id){

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách thuốc",
                null,
                null,
                HttpStatus.NO_CONTENT
        ), HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Medication>> saveMedication(@Valid @RequestBody MedicationDTO medicationDTO, BindingResult result){


        if (medicationDTO == null){

        }

        Medication medication = new Medication();

        medication.setName(medicationDTO.getName());
        medication.setManufacturer(medicationDTO.getManufacturer());
        medication.setPrice(medicationDTO.getPrice());
        medication.setStatus(MedicationStatus.AVAILABLE);

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm mới thuốc",
                medicationService.save(medication),
                null,
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Medication>> updateAllMedication(@RequestParam Long id ,@Valid @RequestBody MedicationDTO medicationDTO, BindingResult result){
        Medication medication = medicationService.updateAll(id,medicationDTO);

            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Cập nhật thuốc thành công",
                    medication,
                    null,
                    HttpStatus.OK
            ), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Medication>> updatePriceMedication(@RequestParam Long id ,@RequestBody Double price){


        Medication medication = medicationService.updatePrice(id,price);

            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Cập nhật thuốc thành công",
                    medication,
                    null,
                    HttpStatus.OK
            ), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Medication>> updatePriceMedication(@RequestParam Long id){

        Medication medication = medicationService.getById(id);

            return new ResponseEntity<>(new ApiDataResponse<>(
                    true,
                    "Tìm kiếm thuốc",
                    medication,
                    null,
                    HttpStatus.OK
            ), HttpStatus.OK);

    }


    @GetMapping("/page")
    public ResponseEntity<ApiDataResponse<Page<Medication>>> getALLMedication(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                              @RequestParam(value = "totalItem", defaultValue = "5") Integer totalItem
    ){

        Pageable pageable = Pageable.ofSize(totalItem).withPage(page);

        Page<Medication> medications = medicationService.getAll(pageable);

        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách thuốc",
                medications,
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
