package com.droptablepwr.cemetery.db.migration;

import com.droptablepwr.cemetery.model.CemeteriesForbiddenPosition;
import com.droptablepwr.cemetery.model.Cemetery;
import org.flywaydb.core.api.migration.Context;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;


@Component
public class V2__add_first_cemetery extends BaseCemetery {

    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        Cemetery cemetery = new Cemetery("Ultra cemetery", "Place to die for", 20, 20, 1);
        Set<CemeteriesForbiddenPosition> forbiddenPositions = new HashSet<>();
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 9, 19, 10));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(4, 0, 4, 19));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 15, 3, 15));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(5, 4, 8, 4));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(9, 0, 10, 19));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(15, 0, 15, 19));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(11, 15, 14, 15));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(16, 4, 19, 4));
        cemetery.setForbiddenPositions(forbiddenPositions);
        saveCemetery(cemetery, connection);
    }
}
