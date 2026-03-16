package com.abbtech.designpatterns.factory;

public class Main {
    static void main() {
        Shape circle = ShapeFactory.createShape("circle");
        circle.draw();
        Shape rectangle = ShapeFactory.createShape("rectangle");
        rectangle.draw();
        Shape square = ShapeFactory.createShape("triangle");
        square.draw();
    }
}
