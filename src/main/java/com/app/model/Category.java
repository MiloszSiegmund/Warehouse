package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[a-z]+")
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
