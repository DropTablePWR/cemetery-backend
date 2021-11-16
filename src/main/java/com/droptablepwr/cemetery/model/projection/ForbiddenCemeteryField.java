package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.field.CemeteryField;
import com.droptablepwr.cemetery.model.field.CemeteryFieldEnum;

public class ForbiddenCemeteryField extends CemeteryField {
    public ForbiddenCemeteryField() {
        super(CemeteryFieldEnum.FORBIDDEN);
    }
}
