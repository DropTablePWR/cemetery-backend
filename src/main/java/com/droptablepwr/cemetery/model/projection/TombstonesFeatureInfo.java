package com.droptablepwr.cemetery.model.projection;


import com.droptablepwr.cemetery.model.Feature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = TombstonesFeatureInfo.class)
public interface TombstonesFeatureInfo {
    Integer getId();

    String getPlace();

    Feature getFeature();
}
