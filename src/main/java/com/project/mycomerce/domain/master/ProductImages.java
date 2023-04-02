package com.project.mycomerce.domain.master;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

}

