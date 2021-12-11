package com.droptablepwr.cemetery.model;

import com.droptablepwr.cemetery.model.projection.TombstoneInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tombstones")
@Entity
@JsonSerialize(as = TombstoneInfo.class)
public class Tombstone implements TombstoneInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cemetery_id", nullable = false)
    private Cemetery cemetery;

    @Column(name = "grid_x", nullable = false)
    private Integer gridX;

    @Column(name = "grid_y", nullable = false)
    private Integer gridY;

    @OneToOne(mappedBy = "tombstone", cascade = CascadeType.ALL)
    private Guest guest;

    @OneToMany(mappedBy = "tombstone", fetch = FetchType.EAGER)
    private Set<TombstonesFeature> features = new HashSet<>();

    public Tombstone() {
    }

    public Tombstone(Cemetery cemetery, Integer gridX, Integer gridY, Guest guest) {
        this.cemetery = cemetery;
        this.gridX = gridX;
        this.gridY = gridY;
        this.guest = guest;
    }

    public Set<TombstonesFeature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<TombstonesFeature> features) {
        this.features = features;
    }

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

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
