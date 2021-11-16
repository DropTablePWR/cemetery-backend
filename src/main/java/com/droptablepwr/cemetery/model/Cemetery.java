package com.droptablepwr.cemetery.model;

import com.droptablepwr.cemetery.model.projection.CemeteryWriteModel;
import com.droptablepwr.cemetery.repository.CemeteryRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Table(name = "cemeteries", indexes = {
        @Index(name = "Cemeteries_name_uindex", columnList = "name", unique = true)
})
@Entity
public class Cemetery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "name", nullable = false, length = 64)
    private String name;


    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "max_grid_x", nullable = false)
    private Integer maxGridX;

    @Column(name = "max_grid_y", nullable = false)
    private Integer maxGridY;

    @Column(name = "type", nullable = false)
    private Integer type;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cemetery_id")
    private Set<CemeteriesForbiddenPosition> forbiddenPositions = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cemetery_id")
    private Set<Tombstone> tombstones = new HashSet<>();


    public Cemetery() {
    }

    public Cemetery(String name, String description, Integer maxGridX, Integer maxGridY, Integer type) {
        this.name = name;
        this.description = description;
        this.maxGridX = maxGridX;
        this.maxGridY = maxGridY;
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMaxGridY() {
        return maxGridY;
    }

    public void setMaxGridY(Integer maxGridY) {
        this.maxGridY = maxGridY;
    }

    public Integer getMaxGridX() {
        return maxGridX;
    }

    public void setMaxGridX(Integer maxGridX) {
        this.maxGridX = maxGridX;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<CemeteriesForbiddenPosition> getForbiddenPositions() {
        return forbiddenPositions;
    }

    public void setForbiddenPositions(Set<CemeteriesForbiddenPosition> forbiddenPositions) {
        this.forbiddenPositions = forbiddenPositions;
    }

    public Set<Tombstone> getTombstones() {
        return tombstones;
    }

    public void setTombstones(Set<Tombstone> tombstones) {
        this.tombstones = tombstones;
    }

    public void change(CemeteryWriteModel newVal) {
        this.setDescription(newVal.getDescription());
        this.setName(newVal.getName());
        this.setType(newVal.getType());
        this.setMaxGridX(newVal.getMaxGridX());
        this.setMaxGridY(newVal.getMaxGridY());
    }
}
