package com.re.hackathon_demo.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicationDTO {
    @NotBlank(message = "Tên thuốc không được để trống")
    private String name;
    @NotBlank(message = "Tên nhà sản xuất không được để trống")
    private String manufacturer;
    @NotNull(message = "Giá không được để trống")
    @Min(value = 1, message = "Giá không được nhỏ hơn không")
    private Double price;
}
