package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.CemeteriesForbiddenPosition;
import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.Tombstone;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CemeteryFullListReadModel {
    private final Integer id;

    private final String name;

    private final String description;

    private final Integer type;

    private final List<GridPosition> forbiddenPositions;

    private final Set<Tombstone> tombstones;

    private CemeteryFullListReadModel(Integer id, String name, String description, Integer type, List<GridPosition> forbiddenPositions, Set<Tombstone> tombstones) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.forbiddenPositions = forbiddenPositions;
        this.tombstones = tombstones;
    }

    public static CemeteryFullListReadModel generateCemeteryFullListRead(Cemetery cemetery) {
        return new CemeteryFullListReadModel(
                cemetery.getId(),
                cemetery.getName(),
                cemetery.getDescription(),
                cemetery.getType(),
                generateForbiddenPositions(cemetery),
                cemetery.getTombstones()
        );
    }

    private static List<GridPosition> generateForbiddenPositions(Cemetery cemetery) {
        List<GridPosition> result = new LinkedList<>();
        for (CemeteriesForbiddenPosition forbiddenPosition : cemetery.getForbiddenPositions()) {
            Integer x1 = forbiddenPosition.getFromX1();
            Integer x2 = forbiddenPosition.getFromX2();
            Integer y1 = forbiddenPosition.getFromY1();
            Integer y2 = forbiddenPosition.getFromY2();
            Integer temp;
            if (x1 > x2) {
                temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 > y2) {
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    result.add(new GridPosition(x, y));
                }
            }
        }
        return result;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getType() {
        return type;
    }

    public List<GridPosition> getForbiddenPositions() {
        return forbiddenPositions;
    }

    public Set<Tombstone> getTombstones() {
        return tombstones;
    }

    private static class GridPosition {
        private final Integer gridX;
        private final Integer gridY;

        private GridPosition(Integer gridX, Integer gridY) {
            this.gridX = gridX;
            this.gridY = gridY;
        }

        public Integer getGridX() {
            return gridX;
        }

        public Integer getGridY() {
            return gridY;
        }
    }
}