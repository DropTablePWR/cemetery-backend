package com.droptablepwr.cemetery.db.migration;

import com.droptablepwr.cemetery.model.CemeteriesForbiddenPosition;
import com.droptablepwr.cemetery.model.Cemetery;
import org.flywaydb.core.api.migration.Context;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

@Component
public class V5__add_cemeteries extends BaseCemetery {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        Cemetery cemetery = new Cemetery("Rock & Die", "San Venganza Cemetery", 22, 22, 1);
        Set<CemeteriesForbiddenPosition> forbiddenPositions = new HashSet<>();
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 0, 21, 0));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 0, 0, 21));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 21, 21, 21));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(21, 0, 21, 21));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 5, 21, 5));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 16, 21, 16));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 10, 21, 11));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(10, 0, 11, 21));
        cemetery.setForbiddenPositions(forbiddenPositions);
        saveCemetery(cemetery, connection);

        cemetery = new Cemetery("Immortal Devotion", "Just Feel It!", 15, 15, 1);
        forbiddenPositions = new HashSet<>();
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 12, 1, 14));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 3, 8, 3));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(8, 0, 8, 14));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(3, 3, 3, 10));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(8, 7, 14, 7));
        cemetery.setForbiddenPositions(forbiddenPositions);
        saveCemetery(cemetery, connection);
    }
}
