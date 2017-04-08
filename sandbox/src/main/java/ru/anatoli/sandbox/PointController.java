package ru.anatoli.sandbox;

/**
 * Created by anatoli.anukevich on 4/8/2017.
 */
public class PointController {
    public static void main(String[] args) {
        //Creating objects for 2 points
        Point p1 = new Point(5, 5);
        Point p2 = new Point(10 ,10);

        //Creating object for method, but it is optional one
        Point dist = new Point();
        dist.distance(p1, p2);
    }
}
