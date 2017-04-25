package ru.anatoli.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by anatoli.anukevich on 4/13/2017.
 */
public class PointTest {
    //Checking correct distance calculation 1
    @Test
    public void distanceTest() {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(10, 10);

        Assert.assertEquals(p1.distance(p2), 7.0710678118654755);
    }

    //Checking correct distance calculation 2
    @Test
    public void distanceTest2() {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(20, 20);

        Assert.assertEquals(p1.distance(p2), 14.142135623730951);
    }

    //Checking correct distance calculation 3
    @Test
    public void distanceTest3() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(50, 50);

        Assert.assertEquals(p1.distance(p2), 69.29646455628166);
    }
}
