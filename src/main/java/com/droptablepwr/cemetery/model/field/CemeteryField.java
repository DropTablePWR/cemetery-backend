package com.droptablepwr.cemetery.model.field;

public class CemeteryField {
    private final CemeteryFieldEnum fieldEnum;

    public CemeteryField(CemeteryFieldEnum fieldEnum) {
        this.fieldEnum = fieldEnum;
    }

    public CemeteryFieldEnum getFieldEnum() {
        return fieldEnum;
    }
}
