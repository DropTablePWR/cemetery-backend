package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.Feature;
import com.droptablepwr.cemetery.model.Tombstone;
import com.droptablepwr.cemetery.model.TombstonesFeature;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TombstonesFeatureWriteModel {
    @NotNull
    private Integer featureId;
    @NotBlank
    private String place;

    public TombstonesFeatureWriteModel() {
    }

    public TombstonesFeatureWriteModel(Integer featureId, String place) {
        this.featureId = featureId;
        this.place = place;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public String getPlace() {
        return place;
    }

    public TombstonesFeature toFeature(Tombstone tombstone, Feature feature) {

        return new TombstonesFeature(tombstone, feature, place);
    }
}
