package com.droptablepwr.cemetery.model.projection;

import com.droptablepwr.cemetery.model.CemeteriesForbiddenPosition;
import com.droptablepwr.cemetery.model.Cemetery;
import com.droptablepwr.cemetery.model.CemeteryField;
import com.droptablepwr.cemetery.model.ForbiddenCemeteryField;

public class CemeteryFullRead {

    private final Integer id;


    private final String name;


    private final String description;

    private final Integer type;


    private final CemeteryField[][] area;

    private CemeteryFullRead(Integer id, String name, String description, Integer type, CemeteryField[][] area) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.area = area;
    }

    public static CemeteryFullRead generateCemeteryFullRead(Cemetery cemetery) {
        return new CemeteryFullRead(
                cemetery.getId(),
                cemetery.getName(),
                cemetery.getDescription(),
                cemetery.getType(),
                generateArea(cemetery)
        );
    }

    private static CemeteryField[][] generateArea(Cemetery cemetery) {
        CemeteryField[][] area = new CemeteryField[cemetery.getMaxGridX()][cemetery.getMaxGridY()];
        for (CemeteriesForbiddenPosition forbiddenPosition : cemetery.getForbiddenPositions()){
            Integer x1 = forbiddenPosition.getFromX1();
            Integer x2 = forbiddenPosition.getFromX2();
            Integer y1 = forbiddenPosition.getFromY1();
            Integer y2 = forbiddenPosition.getFromY2();
            Integer temp;
            if (x1 > x2){
                temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 > y2){
                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y1 <= y2; y++) {
                    area[x][y] = new ForbiddenCemeteryField();
                }
            }
        }
        return area;
    }

    private static void swap(Integer a, Integer b){
        Integer temp = a;
        a = b;
        b = temp;
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

    public CemeteryField[][] getArea() {
        return area;
    }
}
