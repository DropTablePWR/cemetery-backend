package com.droptablepwr.cemetery.model.field;

import com.droptablepwr.cemetery.model.Tombstone;

public class TombstoneCemeteryField extends CemeteryField {
    private final Tombstone tombstone;

    public TombstoneCemeteryField(Tombstone tombstone) {
        super(CemeteryFieldEnum.TOMBSTONE);
        this.tombstone = tombstone;
    }

    public Tombstone getTombstone() {
        return tombstone;
    }
}
