package com.droptablepwr.cemetery.model;

import javax.persistence.*;

@Table(name = "tombstones")
@Entity
public class Tombstone {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cemetery_id", nullable = false)
    private Cemetery cemetery;

    @Column(name = "grid_x", nullable = false)
    private Integer gridX;

    @Column(name = "grid_y", nullable = false)
    private Integer gridY;

    public Integer getGridY() {
        return gridY;
    }

    public void setGridY(Integer gridY) {
        this.gridY = gridY;
    }

    public Integer getGridX() {
        return gridX;
    }

    public void setGridX(Integer gridX) {
        this.gridX = gridX;
    }

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
