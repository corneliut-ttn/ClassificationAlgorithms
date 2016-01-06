package com.classifiers.hierarchical;

import com.classifiers.kmeans.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cornelius on 07.01.2016.
 */
public class HierarchicalClustering {

    private static final int MIN = -20;
    private static final int MAX = 20;

    private int POINTS_NR = 21;

    private List<Point> points;
    private List<Cluster> clusters;

    public HierarchicalClustering(int points_nr) {

        this.POINTS_NR = points_nr;
        this.points = new ArrayList<Point>(POINTS_NR);
        this.clusters = new ArrayList<Cluster>();
    }
    public void initialize() {
        points = Point.createRandomPoints(MIN, MAX, POINTS_NR);
        for(int i=0;i<points.size();i++){
            Cluster cluster=new Cluster(Integer.toString('A'+i));
            cluster.addPoint(points.get(i));
            clusters.add(cluster);
        }
        showClusters();
    }
    private void showClusters() {
        for (int i = 0; i < clusters.size(); i++) {
            clusters.get(i).showCluster();
        }
    }
}
