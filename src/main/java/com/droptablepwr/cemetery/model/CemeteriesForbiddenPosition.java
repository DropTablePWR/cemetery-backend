package com.droptablepwr.cemetery.model;

import javax.persistence.*;

@Table(name = "cemeteries_forbidden_positions")
@Entity
public class CemeteriesForbiddenPosition {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cemetery_id", nullable = false)
    private Cemetery cemetery;

    @Column(name = "from_x1", nullable = false)
    private Integer fromX1;

    @Column(name = "from_y1", nullable = false)
    private Integer fromY1;

    @Column(name = "from_x2", nullable = false)
    private Integer fromX2;

    @Column(name = "from_y2", nullable = false)
    private Integer fromY2;

    public Integer getFromY2() {
        return fromY2;
    }

    public void setFromY2(Integer fromY2) {
        this.fromY2 = fromY2;
    }

    public Integer getFromX2() {
        return fromX2;
    }

    public void setFromX2(Integer fromX2) {
        this.fromX2 = fromX2;
    }

    public Integer getFromY1() {
        return fromY1;
    }

    public void setFromY1(Integer fromY1) {
        this.fromY1 = fromY1;
    }

    public Integer getFromX1() {
        return fromX1;
    }

    public void setFromX1(Integer fromX1) {
        this.fromX1 = fromX1;
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
