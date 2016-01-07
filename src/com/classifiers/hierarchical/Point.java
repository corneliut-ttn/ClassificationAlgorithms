package com.classifiers.hierarchical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Cornelius on 06.01.2016.
 */
public class Point {

    private double coordinates[];

    public Point(double coordinates[])
    {
        this.coordinates=new double[coordinates.length];
        this.coordinates=coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public static double calculateDistance(Point p, Point q) throws Exception {
        if (testCoordinatesNr(p,q)){
            double dist=0.0;
            for (int i = 0; i < p.getCoordinates().length ; i++) {
                dist+=Math.pow(q.getCoordinates()[i]-p.getCoordinates()[i],2);
            }
        return Math.sqrt(dist);
        }
        else throw new Exception("Invalid number of coordinates. \n"+p+"\n"+q);
    }

    public static boolean testCoordinatesNr(Point p, Point q){
        return (p.getCoordinates().length==q.getCoordinates().length);
    }

    public static Point createRandomPoint(int min, int max, int coordinates_nr) {
        Random r = new Random();
        double coordinates[]=new double[coordinates_nr];
        for (int i = 0; i < coordinates_nr; i++) {
            coordinates[i]=min + (max - min) * r.nextDouble();
        }
        return new Point(coordinates);
    }

    public static List createRandomPoints(int min, int max, int nr,int coordinates_nr) {
        List points = new ArrayList(nr);
        for(int i = 0; i < nr; i++) {
            points.add(createRandomPoint(min,max,coordinates_nr));
        }
        return points;
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
