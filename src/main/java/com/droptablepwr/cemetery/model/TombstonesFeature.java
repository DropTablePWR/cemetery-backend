package com.droptablepwr.cemetery.model;

import javax.persistence.*;

@Table(name = "tombstones_features", indexes = {
        @Index(name = "tombstones_features_tombstone_id_place_uindex", columnList = "tombstone_id, place", unique = true)
})
@Entity
public class TombstonesFeature {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tombstone_id", nullable = false)
    private Tombstone tombstone;

    @ManyToOne(optional = false)
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;

    @Column(name = "place", nullable = false, length = 64)
    private String place;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Tombstone getTombstone() {
        return tombstone;
    }

    public void setTombstone(Tombstone tombstone) {
        this.tombstone = tombstone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
