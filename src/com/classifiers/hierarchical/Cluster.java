package com.classifiers.hierarchical;

import com.classifiers.kmeans.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cornelius on 06.01.2016.
 */
public class Cluster {

    public List<Point> points;
    public String id;

    public Cluster(String id) {
        this.id = id;
        this.points = new ArrayList<Point>();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setPoints(List points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    @Override
    public String toString() {
        return "Cluster:" + id +"\n"+
                "[Points: " + points +"\n";
    }

    public static double completeLinkageDistance(Cluster a,Cluster b){
        double max=Double.NEGATIVE_INFINITY;
        for (Point pointA : a.getPoints()) {
            for (Point pointB:b.getPoints()) {
                double dist=Point.calculateDistance(pointA,pointB);
                if(max<dist)
                    max=dist;
            }
        }
        return max;
    }

    public static double singleLinkageDistance(Cluster a,Cluster b){
        double min=Double.POSITIVE_INFINITY;
        for (Point pointA : a.getPoints()) {
            for (Point pointB:b.getPoints()) {
                double dist=Point.calculateDistance(pointA,pointB);
                if(min>dist)
                    min=dist;
            }
        }
        return min;
    }

    public static Cluster merge(Cluster a,Cluster b){
        String id=a.getId()+b.getId();
        Arrays.sort(id.toCharArray());
        Cluster cluster=new Cluster(id);

        for(Point point:a.getPoints()){
            cluster.addPoint(point);
        }
        for(Point point:b.getPoints()){
            cluster.addPoint(point);
        }
        return cluster;
    }

    public void showCluster() {
        System.out.println(this.toString());
    }
}
