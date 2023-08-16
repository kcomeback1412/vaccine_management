package com.spring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class News implements Serializable {
    @Id
    @Column(name = "NEWS_ID", length = 36)
    private String newsId;

    @Column(name = "CONTENT", length = 4000)
    private String content;

    @Column(name = "PREVIEW", length = 1000)
    private String preview;

    @Column(name = "TITLE", length = 300)
    private String title;

    @ManyToOne
    @JoinColumn(name = "NEWS_TYPE_ID")
    private NewsType newsType;


}
