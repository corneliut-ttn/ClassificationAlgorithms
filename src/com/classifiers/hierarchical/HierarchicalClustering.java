package com.classifiers.hierarchical;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cornelius on 07.01.2016.
 */
public class HierarchicalClustering {

    private static final int MIN_DIST=-1;
    private static final int AVG_DIST=0;
    private static final int MAX_DIST=1;

    private static final int MIN = -20;
    private static final int MAX = 20;

    private int POINTS_NR = 21;
    private int COORDINATES_NR =5;

    private List<Point> points;
    private List<Cluster> clusters;

    public HierarchicalClustering(int points_nr,int coordinates_nr) {
        this.POINTS_NR = points_nr;
        this.COORDINATES_NR=coordinates_nr;
        this.points = new ArrayList<Point>(POINTS_NR);
        this.clusters = new ArrayList<Cluster>();
    }

    public HierarchicalClustering() {
        this.points = new ArrayList<Point>(POINTS_NR);
        this.clusters = new ArrayList<Cluster>();
    }

    public void classify(int type) throws Exception {
        boolean finish = false;
        int iteration = 0;
        List<Cluster> remainingClusters = new ArrayList<>();
        while (!finish) {

            for (Cluster c : clusters)
                remainingClusters.add(c);

            for (int clus_i = 0; clus_i < clusters.size(); clus_i++) {
                if (finish == true) break;
                if (!remainingClusters.contains(clusters.get(clus_i))) {
                    continue;
                }

                double best_dist = Double.POSITIVE_INFINITY;
                if (type == MAX_DIST) best_dist = Double.NEGATIVE_INFINITY;
                int best_idx = -1;
                for (int clus_j = 0; clus_j < clusters.size(); clus_j++) {
                    if (clus_i == clus_j) continue;
                    if (!remainingClusters.contains(clusters.get(clus_j))) {
                        continue;
                    }

                    double clus_dist = 0.0;
                    if (type == MIN_DIST) {
                        clus_dist = Cluster.singleLinkageDistance(clusters.get(clus_i), clusters.get(clus_j));
                        if (best_dist > clus_dist) {
                            best_dist = clus_dist;
                            best_idx = clus_j;
                        }
                    } else if (type == AVG_DIST) {
                        clus_dist = Cluster.meanLinkageDistance(clusters.get(clus_i), clusters.get(clus_j));
                        if (best_dist > clus_dist) {
                            best_dist = clus_dist;
                            best_idx = clus_j;
                        }
                    } else if (type == MAX_DIST) {
                        clus_dist = Cluster.completeLinkageDistance(clusters.get(clus_i), clusters.get(clus_j));
                        if (best_dist < clus_dist) {
                            best_dist = clus_dist;
                            best_idx = clus_j;
                        }
                    } else System.out.println("Invalid type");
                }
                if(best_idx!=-1){
                Cluster aux = Cluster.merge(clusters.get(clus_i), clusters.get(best_idx));
                remainingClusters.remove(clusters.get(clus_i));
                remainingClusters.remove(clusters.get(best_idx));
                remainingClusters.add(aux);
                iteration++;
                if (remainingClusters.size() == 1)
                    finish = true;
                System.out.println("==================================================");
                System.out.println("Iteration: " + iteration);
                for (Cluster c : remainingClusters) {
                    c.showCluster();
                }
                System.out.println("==================================================");
                }
            }
            clusters.clear();
            for (Cluster c : remainingClusters)
                clusters.add(c);
            remainingClusters.clear();
        }
    }

    private void clearClusters() {
        clusters.forEach(Cluster::clear);
    }

    public void initialize() {
        points = Point.createRandomPoints(MIN, MAX, POINTS_NR,COORDINATES_NR);
        for(int i=0;i<points.size();i++){
            char char_id=(char)('A'+i);
            Cluster cluster=new Cluster(Character.toString(char_id));
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

    public void setPoints(List<Point> points) {
        this.points = points;
        for(int i=0;i<points.size();i++){
            char char_id=(char)('A'+i);
            Cluster cluster=new Cluster(Character.toString(char_id));
            cluster.addPoint(points.get(i));
            clusters.add(cluster);
        }
        showClusters();
    }
}
