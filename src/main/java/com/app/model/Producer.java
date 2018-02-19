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
public class Producer {
    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String name;
    @Pattern(regexp = "[A-Z][a-z]+")
    private String city;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "producer")
    private List<Product> products = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "tradeId")
    private Trade trade;
}
