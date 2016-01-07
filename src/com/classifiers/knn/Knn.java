package com.classifiers.knn;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * Created by Cornelius on 07.01.2016.
 */
public class Knn {
    private static final int MIN = -20;
    private static final int MAX = 20;

    private static int POINTS_NR=20;
    private static int COORDINATES_NR =5;
    private static int NEIGHBOURS=3;

    private LinkedHashMap<Point,String> points=new LinkedHashMap<Point, String>();
    private Multimap<Double,String> distances= ArrayListMultimap.create();
    private String pressumptions[];

    public Knn(int points_nr,int coordinates_nr,String pressumptions[],int neighbours) {
        this.pressumptions=new String[pressumptions.length];
        this.pressumptions=pressumptions;
        this.POINTS_NR=points_nr;
        this.COORDINATES_NR=coordinates_nr;
        this.NEIGHBOURS=neighbours;
    }

    public void initialize(){
        Random r = new Random();
        List<Point> pointList=new ArrayList<Point>(POINTS_NR);
        pointList=Point.createRandomPoints(MIN,MAX,POINTS_NR,COORDINATES_NR);
        this.points=new LinkedHashMap<Point,String>(POINTS_NR);
        for (Point p:pointList) {
            int test= r.nextInt(pressumptions.length);
            points.put(p,pressumptions[test]);
        }
    }

    public Point generatePoint(){
        return Point.createRandomPoint(MIN,MAX,COORDINATES_NR);
    }

    public void addPoint(Point p, String classification){
        points.put(p,classification);
    }

    public void classify(Point p) throws Exception {
        calculateDistances(p);

        System.out.println("==========================================");
        System.out.println("The neighbours are:");
        ArrayList<String> values=new ArrayList<String>();
        Double d;
        int k=0;
        while(k<NEIGHBOURS){
            d=getMinimum();
            for (String s:distances.get(d)) {
                values.add(s);
                System.out.println(s);
                k++;
                if(k>=NEIGHBOURS)
                    break;
            }
            distances.removeAll(d);
        }
        System.out.println("Point classification: "+ majorityElement(values));
    }

    public String majorityElement(ArrayList<String> pressumptions) {
        HashMap<String,Integer> counts=new HashMap<>();
        String result="";
        int max=-1;
        for (int i = 0; i <pressumptions.size() ; i++) {
            if(!counts.containsKey(pressumptions.get(i))){
                counts.put(pressumptions.get(i),1);
                if(max<1){
                    max=1;
                    result=pressumptions.get(i);
                }
            }
            else {
                int count=counts.get(pressumptions.get(i));
                count++;
                counts.replace(pressumptions.get(i),count);
                if(max<count){
                    max=count;
                    result=pressumptions.get(i);
                }
            }
        }

        return result;
    }

    public Double getMinimum(){
        Double min=distances.keySet().iterator().next();
        for (Double d:distances.keySet()) {
            if (min>d)
                min=d;
        }
        return min;
    }

    public void calculateDistances(Point p) throws Exception {
        Double distance;
        for (Point hashmapPoint: points.keySet()) {
            distance=Point.calculateDistance(p, hashmapPoint);
            distances.put(distance,points.get(hashmapPoint));
        }
        System.out.println("Distances from point "+p+" to each other point:");
        for (Double d: distances.keySet()) {
            System.out.println(d+" "+distances.get(d));
        }
    }
}
