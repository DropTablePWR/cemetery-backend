package com.droptablepwr.cemetery.model.field;

import com.droptablepwr.cemetery.model.Tombstone;
import com.droptablepwr.cemetery.model.projection.TombstoneInfo;

public class TombstoneCemeteryField extends CemeteryField {
    private final TombstoneInfo tombstone;

    public TombstoneCemeteryField(Tombstone tombstone) {
        super(CemeteryFieldEnum.TOMBSTONE);
        this.tombstone = tombstone;
    }

    public TombstoneInfo getTombstone() {
        return tombstone;
    }
}
