package com.edium.service.data.model;

import com.edium.library.model.base.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Course extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String shortDescription;

    public Course(@NotBlank String name, @NotBlank String shortDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
