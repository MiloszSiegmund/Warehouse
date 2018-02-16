package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductForm {
    private Long id;
    private String name;
    private BigDecimal price;
    private String colour;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfProduction;
    private Long categoryId;
    private Long producerId;
}
