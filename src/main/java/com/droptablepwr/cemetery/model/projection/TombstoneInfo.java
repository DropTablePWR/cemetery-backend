package com.droptablepwr.cemetery.model.projection;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(as = TombstoneInfo.class)
public interface TombstoneInfo {
    Integer getId();

    Integer getGridX();

    Integer getGridY();

    GuestInfo getGuest();
}
