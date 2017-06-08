package ru.anatoli.sandbox;

/**
 * Created by anatoli.anukevich on 6/8/2017.
 */
public class Point {
    private double x;
    private double y;

    //Constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //Distance method
    public double distance(Point p2) {
        double result;
        double ExpForX = Math.pow((x - p2.getX()), 2);
        double ExpForY = Math.pow((y - p2.getY()), 2);

        result = Math.sqrt(ExpForX + ExpForY);
        return result;
    }
}
