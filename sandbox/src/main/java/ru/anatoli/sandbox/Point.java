package ru.anatoli.sandbox;

/**
 * Created by anatoli.anukevich on 4/8/2017.
 */
public class Point {
    private double x;
    private  double y;

    //Constructor with arguments for Points
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Empty constructor for calculation method
    public Point() {
    }

    //Getters + Setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //Method for distance calculation between 2 points
    public double distance(Point p1, Point p2) {
        double result;
        double ExpForX = Math.pow(p2.getX() - p1.getX(), 2);
        double ExpForY = Math.pow(p2.getY() - p1.getY(), 2);

        result = Math.sqrt(ExpForX + ExpForY);
        System.out.println("Distance between two points is " + result);
        return result;
    }
}
