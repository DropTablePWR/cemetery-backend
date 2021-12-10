package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.CemeteriesForbiddenPosition;
import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.Tombstone;
import com.droptablepwr.cemetery.model.field.CemeteryField;
import com.droptablepwr.cemetery.model.field.EmptyCemeteryField;
import com.droptablepwr.cemetery.model.field.ForbiddenCemeteryField;
import com.droptablepwr.cemetery.model.field.TombstoneCemeteryField;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CemeteryAreaTest {

    @Test
    void checkCemeteryFullReadArea() {
        Cemetery testCemetery = getCemetery1();
        CemeteryFullReadModel testModel = CemeteryFullReadModel.generateCemeteryFullRead(testCemetery);
        CemeteryField[][] valid = getCemetery1Area();
        CemeteryField[][] toCheck = testModel.getArea();
        assertEquals(valid.length, toCheck.length);
        for (int i = 0; i < valid.length; i++) {
            assertEquals(valid[i].length, toCheck[i].length);
            for (int j = 0; j < valid[i].length; j++) {
                assertEquals(valid[i][j].getFieldEnum(), toCheck[i][j].getFieldEnum());
            }
        }
    }

    @Test
    void checkCemeteryFullReadListForbiddenPositions() {
        Cemetery testCemetery = getCemetery1();
        CemeteryFullListReadModel testModel = CemeteryFullListReadModel.generateCemeteryFullListRead(testCemetery);
        List<CemeteryFullListReadModel.GridPosition> valid = getCemetery1Positions();
        List<CemeteryFullListReadModel.GridPosition> toCheck = testModel.getForbiddenPositions();
        assertEquals(valid.size(), toCheck.size());
        for (CemeteryFullListReadModel.GridPosition position : valid) {
            if (toCheck.stream().noneMatch(gridPosition -> Objects.equals(gridPosition.getGridX(), position.getGridX()) && Objects.equals(gridPosition.getGridY(), position.getGridY())))
                throw new AssertionError();
        }
    }

    Cemetery getCemetery1() {
        Cemetery cemetery = new Cemetery("name", "desc", 4, 4, 0);
        Set<CemeteriesForbiddenPosition> forbiddenPositions = new HashSet<>();
        forbiddenPositions.add(new CemeteriesForbiddenPosition(0, 1, 3, 2));
        forbiddenPositions.add(new CemeteriesForbiddenPosition(3, 3, 3, 3));
        cemetery.setForbiddenPositions(forbiddenPositions);
        Tombstone tombstone = new Tombstone();
        tombstone.setGridX(1);
        tombstone.setGridY(0);
        cemetery.getTombstones().add(tombstone);
        return cemetery;
    }

    CemeteryField[][] getCemetery1Area() {
        CemeteryField[][] area = new CemeteryField[4][4];
        area[0][0] = new EmptyCemeteryField();
        area[1][0] = new TombstoneCemeteryField(null);
        area[2][0] = new EmptyCemeteryField();
        area[3][0] = new EmptyCemeteryField();

        area[0][1] = new ForbiddenCemeteryField();
        area[1][1] = new ForbiddenCemeteryField();
        area[2][1] = new ForbiddenCemeteryField();
        area[3][1] = new ForbiddenCemeteryField();

        area[0][2] = new ForbiddenCemeteryField();
        area[1][2] = new ForbiddenCemeteryField();
        area[2][2] = new ForbiddenCemeteryField();
        area[3][2] = new ForbiddenCemeteryField();

        area[0][3] = new EmptyCemeteryField();
        area[1][3] = new EmptyCemeteryField();
        area[2][3] = new EmptyCemeteryField();
        area[3][3] = new ForbiddenCemeteryField();

        return area;
    }

    List<CemeteryFullListReadModel.GridPosition> getCemetery1Positions() {
        List<CemeteryFullListReadModel.GridPosition> positions = new LinkedList<>();
        positions.add(new CemeteryFullListReadModel.GridPosition(0, 1));
        positions.add(new CemeteryFullListReadModel.GridPosition(1, 1));
        positions.add(new CemeteryFullListReadModel.GridPosition(2, 1));
        positions.add(new CemeteryFullListReadModel.GridPosition(3, 1));
        positions.add(new CemeteryFullListReadModel.GridPosition(0, 2));
        positions.add(new CemeteryFullListReadModel.GridPosition(1, 2));
        positions.add(new CemeteryFullListReadModel.GridPosition(2, 2));
        positions.add(new CemeteryFullListReadModel.GridPosition(3, 2));
        positions.add(new CemeteryFullListReadModel.GridPosition(3, 3));

        return positions;
    }


}
