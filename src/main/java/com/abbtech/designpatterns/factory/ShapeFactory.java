package com.abbtech.designpatterns.factory;

public class ShapeFactory {

    public static Shape createCircles() {

        return new Circle();
    }

    public static Shape createShape(String shapeType) {
        return switch (shapeType) {
            case "circle" -> new Circle();
            case "rectangle" -> new Rectangle();
            case "triangle" -> new Triangle();
            default -> throw new IllegalArgumentException("Invalid shapeType");
        };
    }
}
