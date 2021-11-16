package com.droptablepwr.cemetery.db.migration;

import com.droptablepwr.cemetery.model.CemeteriesForbiddenPosition;
import com.droptablepwr.cemetery.model.Cemetery;
import org.flywaydb.core.api.migration.BaseJavaMigration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public abstract class BaseCemetery extends BaseJavaMigration {
    public void saveCemetery(Cemetery cemetery, Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        Statement insert = connection.createStatement();
        insert.execute("INSERT INTO cemeteries (name, description, max_grid_x, max_grid_y, type)" +
                " VALUES ('" + cemetery.getName() + "', '" + cemetery.getDescription() + "', " + cemetery.getMaxGridX() + ", " + cemetery.getMaxGridY() + ", DEFAULT);");
        Statement selectId = connection.createStatement();
        ResultSet rows = selectId.executeQuery("select id from cemeteries where name='" + cemetery.getName() + "'");
        while (rows.next()) {
            int id = rows.getInt(1);
            saveForbidden(id, connection, cemetery.getForbiddenPositions());
        }
        connection.commit();
    }

    private void saveForbidden(int id, Connection connection, Set<CemeteriesForbiddenPosition> positions) throws SQLException {
        for (CemeteriesForbiddenPosition position : positions) {
            Statement insert = connection.createStatement();
            insert.execute("INSERT INTO cemeteries_forbidden_positions (cemetery_id, from_x1, from_y1, from_x2, from_y2)" +
                    "VALUES (" + id + "," + position.getFromX1() + "," + position.getFromY1() + "," + position.getFromX2() + "," + position.getFromY2() + ")");
        }
    }
}
