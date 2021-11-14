package com.droptablepwr.cemetery.model;

import javax.persistence.*;

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

    @Column(name = "description", nullable = false)
    private Integer description;

    @Column(name = "max_grid_x", nullable = false)
    private Integer maxGridX;

    @Column(name = "max_grid_y", nullable = false)
    private Integer maxGridY;

    @Column(name = "type", nullable = false)
    private Integer type;

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

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
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
}
