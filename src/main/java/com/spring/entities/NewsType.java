package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class NewsType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NEWS_TYPE_ID", length = 36)
    private String newsTypeId;

    @Column(name = "DESCRIPTION", length = 10)
    private String description;

    @Column(name = "NEWS_TYPE_NAME", length = 50)
    private String newsTypeName;
}
