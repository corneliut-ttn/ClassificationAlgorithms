package com.classifiers;

import com.classifiers.bayesnaive.BayesNaive;
import com.classifiers.bayesnaive.TestData;
import com.classifiers.bayesnaive.TrainingData;
import com.classifiers.hierarchical.HierarchicalClustering;
import com.classifiers.hierarchical.Point;
import com.classifiers.kmeans.KMeans;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //testNaiveBayes();
        testKMeans();
        //testHierarchial();
    }

    public static void testHierarchial(){
        HierarchicalClustering hc=new HierarchicalClustering(10,2);
        hc.initialize();
        List<Point> listp=new ArrayList<Point>();
        listp.add(new Point(new double[]{1, 1, 9}));
        listp.add(new Point(new double[]{1, 2, 8}));
        listp.add(new Point(new double[]{1, 3, 7}));
        listp.add(new Point(new double[]{1, 4, 6}));
        listp.add(new Point(new double[]{1, 5, 5}));
        listp.add(new Point(new double[]{1, 6, 4}));
        listp.add(new Point(new double[]{1, 7, 3}));
        listp.add(new Point(new double[]{1, 8, 2}));
        listp.add(new Point(new double[]{1, 9, 1}));
        //hc.setPoints(listp);
        try {
            hc.classify(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testKMeans(){
        KMeans kMeans=new KMeans(3,21,4);
        kMeans.initialize();
        kMeans.showPoints();
        try {
            kMeans.classify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testNaiveBayes(){
        int n=3,m=3;
        String presumptions[]=new String[]{"male","female","transgender"};
        BayesNaive bayesNaive=new BayesNaive(n,presumptions);

        bayesNaive.setPresumption_name("Sex");

        String attr_names[]= new String[]{"Height", "Weight","FootSize"};

        bayesNaive.setAttributes_names(attr_names);

        bayesNaive.addTrainData(new TrainingData("male",new double[]{6, 180, 12}));
        bayesNaive.addTrainData(new TrainingData("male",new double[]{5.92, 190, 11}));
        bayesNaive.addTrainData(new TrainingData("male",new double[]{5.58, 170, 12}));
        bayesNaive.addTrainData(new TrainingData("male",new double[]{5.92, 165, 10}));
        bayesNaive.addTrainData(new TrainingData("female",new double[]{5, 100, 6}));
        bayesNaive.addTrainData(new TrainingData("female",new double[]{5.5, 150, 8}));
        bayesNaive.addTrainData(new TrainingData("female",new double[]{5.42, 130, 7}));
        bayesNaive.addTrainData(new TrainingData("female",new double[]{5.75, 150, 9}));
        bayesNaive.addTrainData(new TrainingData("transgender",new double[]{4, 200, 5}));
        bayesNaive.addTrainData(new TrainingData("transgender",new double[]{4.10, 150, 8}));
        bayesNaive.addTrainData(new TrainingData("transgender",new double[]{5.42, 190, 7}));
        bayesNaive.addTrainData(new TrainingData("transgender",new double[]{5.50, 150, 9}));

        bayesNaive.trainClassifier();
        bayesNaive.testClassifier(new TestData(new double[]{4, 150, 12}));
        bayesNaive.testClassifier(new TestData(new double[]{3.4, 110, 5}));
        bayesNaive.testClassifier(new TestData(new double[]{5, 100, 10}));
        bayesNaive.testClassifier(new TestData(new double[]{6, 80, 11}));
        bayesNaive.testClassifier(new TestData(new double[]{7, 111, 8}));
    }
}
