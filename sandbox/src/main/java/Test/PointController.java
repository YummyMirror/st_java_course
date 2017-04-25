package Test;

/**
 * Created by anatoli.anukevich on 4/25/2017.
 */
public class PointController {
    public static void main(String[] args) {
        //Creating objects for 2 points
        Point p1 = new Point(5, 5);
        Point p2 = new Point(10 ,10);

        System.out.println("Distance between two points is " + p1.distance(p2));
    }
}
