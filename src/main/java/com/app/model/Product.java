package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @Min(value = 0)
    private BigDecimal price;
    @Pattern(regexp = "[a-z]+")
    private String colour;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfProduction;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "categoryId")
    private Category category;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producerId")
    private Producer producer;

}
