package com.spring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class News implements Serializable {
    @Id
    @Column(name = "NEWS_ID", length = 36)
    private String newsId;

    @Column(name = "CONTENT", columnDefinition = "nvarchar (4000)")

    private String content;

    @Column(name = "PREVIEW", columnDefinition = "nvarchar (1000)")
    private String preview;

    @Column(name = "TITLE", columnDefinition = "nvarchar (300)")
    private String title;

    @Temporal(TemporalType.DATE)
    private Date postDate;

    @ManyToOne
    @JoinColumn(name = "NEWS_TYPE_ID")
    private NewsType newsType;

    @PrePersist
    protected void createDate() {
        this.postDate = new Date();
    }



}
