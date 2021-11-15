package com.droptablepwr.cemetery.model;

public class TombstoneCemeteryField extends CemeteryField{
    private final Tombstone tombstone;

    public TombstoneCemeteryField(Tombstone tombstone) {
        super(CemeteryFieldEnum.TOMBSTONE);
        this.tombstone = tombstone;
    }

    public Tombstone getTombstone() {
        return tombstone;
    }
}
