package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.TombstonesFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Set;


@JsonSerialize(as = TombstoneInfo.class)
public interface TombstoneInfo {
    Integer getId();

    Integer getGridX();

    Integer getGridY();

    GuestInfo getGuest();

    Set<TombstonesFeature> getFeatures();
}
