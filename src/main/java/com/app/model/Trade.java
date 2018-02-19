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
public class Trade {
    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[a-z]+")
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "trade")
    private List<Producer> producers = new ArrayList<>();
}