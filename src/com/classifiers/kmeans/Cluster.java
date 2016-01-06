package com.classifiers.kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cornelius on 06.01.2016.
 */
public class Cluster {

    public List<Point> points;
    public Point centroid;
    public int id;

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList<Point>();
        this.centroid = null;
    }

    public List getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setPoints(List points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    @Override
    public String toString() {
        return "Cluster:" + id +"\n"+
                "[Centroid: "+ centroid +"\n"+
                "[Points: " + points +"\n";
    }

    public void showCluster() {
        System.out.println(this.toString());
    }
}
