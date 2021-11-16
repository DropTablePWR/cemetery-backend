package com.droptablepwr.cemetery.model.projection;

import java.util.Set;

public interface CemeteryInfoAdvanced {
    Integer getId();

    String getDescription();

    Integer getMaxGridX();

    Integer getMaxGridY();

    String getName();

    Integer getType();

    Set<CemeteriesForbiddenPositionInfo> getForbiddenPositions();

    Set<TombstoneInfo> getTombstones();

    interface CemeteriesForbiddenPositionInfo {
        Integer getId();

        Integer getFromX1();

        Integer getFromX2();

        Integer getFromY1();

        Integer getFromY2();
    }

    interface TombstoneInfo {
        Integer getId();

        Integer getGridX();

        Integer getGridY();
    }
}
