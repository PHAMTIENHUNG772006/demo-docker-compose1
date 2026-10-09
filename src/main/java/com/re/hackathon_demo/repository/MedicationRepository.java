package com.re.hackathon_demo.repository;

import com.re.hackathon_demo.model.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {


    @Query("SELECT m FROM Medication m WHERE m.is_deleted = false AND " +
            "(:keyword IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.manufacturer) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Medication> getReferenceById(@Param("keyword") String keyword, Pageable pageable);
}
