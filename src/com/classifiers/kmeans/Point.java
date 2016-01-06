package com.classifiers.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Cornelius on 06.01.2016.
 */
public class Point {

    private double x = 0;
    private double y = 0;
    private int cluster = 0;

    public Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX()  {
        return this.x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getCluster() {
        return this.cluster;
    }

    public static double calculateDistance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
    }

    public static Point createRandomPoint(int min, int max) {
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }

    public static List createRandomPoints(int min, int max, int nr) {
        List points = new ArrayList(nr);
        for(int i = 0; i < nr; i++) {
            points.add(createRandomPoint(min,max));
        }
        return points;
    }

    @Override
    public String toString() {
        return "Point [" +
                "x =" + x +
                ", y =" + y +
               // ", cluster =" + cluster +
                ']';
    }
}
