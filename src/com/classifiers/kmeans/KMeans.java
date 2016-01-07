package com.classifiers.kmeans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cornelius on 06.01.2016.
 */
public class KMeans {

    private static final int MIN = -20;
    private static final int MAX = 20;

    private int CLUSTERS_NR = 3;
    private int POINTS_NR = 21;
    private int COORDINATES_NR =5;

    private List<Point> points;
    private List<Cluster> clusters;

    public KMeans(int clusters_nr,int points_nr,int coordinates_nr) {
        this.CLUSTERS_NR = clusters_nr;
        this.POINTS_NR = points_nr;
        this.COORDINATES_NR=coordinates_nr;
        this.points = new ArrayList<Point>(POINTS_NR);
        this.clusters = new ArrayList<Cluster>(CLUSTERS_NR);
    }

    public void initialize() {
        points = Point.createRandomPoints(MIN,MAX,POINTS_NR,COORDINATES_NR);
        for (int i = 0; i < CLUSTERS_NR; i++) {
            Cluster cluster = new Cluster(i);
            cluster.setCentroid(Point.createRandomPoint(MIN,MAX,COORDINATES_NR));
            clusters.add(cluster);
        }
        showClusters();
    }

    private void showClusters() {
        for (int i = 0; i < CLUSTERS_NR; i++) {
            clusters.get(i).showCluster();
        }
    }

    public void classify() throws Exception {

        boolean finish = false;
        int iteration = 0;

        while(!finish) {
            clearClusters();
            List<Point> lastCentroids = getCentroids();
            assignCluster();
            calculateCentroids();
            iteration++;
            List<Point> currentCentroids = getCentroids();
            double distance = 0;
            for(int i = 0; i < lastCentroids.size(); i++) {
                distance += Point.calculateDistance(lastCentroids.get(i), currentCentroids.get(i));
            }

            System.out.println("==================================================");
            System.out.println("Iteration: " + iteration);
            System.out.println("Centroid distances: " + distance);
            showClusters();
            System.out.println("==================================================");

            if(distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        clusters.forEach(com.classifiers.kmeans.Cluster::clear);
    }

    private List getCentroids() {
        List centroids = new ArrayList(CLUSTERS_NR);
        for(Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getCoordinates());
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() throws Exception {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for(Point point : points) {
            min = max;
            for(int i = 0; i < CLUSTERS_NR; i++) {
                Cluster c = clusters.get(i);
                distance = Point.calculateDistance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sum[]=new double[COORDINATES_NR];
            for (int i = 0; i <sum.length ; i++) {
                sum[i]=0.0;
            }
            List<Point> list = cluster.getPoints();
            int n_points = list.size();

            for(Point point : list) {
                for (int i = 0; i < point.getCoordinates().length ; i++) {
                    sum[i]+=point.getCoordinates()[i];
                }
            }
            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
                double coordCentr[]=new double[COORDINATES_NR];
                for (int i = 0; i <coordCentr.length ; i++) {
                    coordCentr[i]=sum[i]/n_points;
                }
               centroid.setCoordinates(coordCentr);
            }
        }
    }

    public void showPoints(){
        for(Point point:points){
            System.out.println(point.toString());
        }
    }

}
