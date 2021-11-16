package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.Guest;
import com.droptablepwr.cemetery.model.Tombstone;
import io.micrometer.core.lang.NonNull;

import javax.validation.Valid;


public class TombstoneWriteModel {
    @NonNull
    private Integer gridX;
    @NonNull
    private Integer gridY;
    @Valid
    private GuestWriteModel guest;

    @NonNull
    public Integer getGridX() {
        return gridX;
    }

    public void setGridX(@NonNull Integer gridX) {
        this.gridX = gridX;
    }

    @NonNull
    public Integer getGridY() {
        return gridY;
    }

    public void setGridY(@NonNull Integer gridY) {
        this.gridY = gridY;
    }

    public GuestWriteModel getGuest() {
        return guest;
    }

    public void setGuest(GuestWriteModel guest) {
        this.guest = guest;
    }

    public Tombstone toTombstone(Cemetery cemetery) {
        Tombstone result = new Tombstone(cemetery, gridX, gridY, null);
        Guest guest = null;
        if (this.guest != null) {
            guest = this.guest.toGuest(result);
        }
        result.setGuest(guest);
//        cemetery.getTombstones().add(result);
        return result;
    }
}
