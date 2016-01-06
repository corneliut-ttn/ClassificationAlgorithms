package com.classifiers.bayesnaive;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

/**
 * Bayes Clasifier using Gaussian Distribution
 * Created by Cornelius on 05.01.2016.
 */


public class BayesNaive {

    private ArrayList<TrainingData> trainingData;
    private ArrayList<TestData> testData;

    private int attributes_nr;
    private int presumption_nr;
    private String attributes_names[];
    private String presumptions[];
    private String presumtion_name;

    private double results[][];

    public BayesNaive(int attributes_nr,String presumptions[]){
        this.trainingData=new ArrayList<TrainingData>();
        this.testData=new ArrayList<TestData>();

        this.attributes_nr=attributes_nr;
        this.attributes_names=new String[attributes_nr];
        this.presumption_nr=presumptions.length;
        this.presumptions=new String[presumption_nr];
        this.presumptions=presumptions;
    }

    public void setAttributes_names(String attributes_names[]){
        for (int i = 0; i <  this.attributes_nr; i++) {
            this.attributes_names[i]=attributes_names[i];
        }
    }

    public void setPresumption_name(String presumption_name){
        this.presumtion_name=presumption_name;
    }

    public void addTrainData(TrainingData trainData){
        this.trainingData.add(trainData);
    }

    public  void addTestData(TestData testData){
        this.testData.add(testData);
    }

    public void trainClassifier(){

        results=new double[presumption_nr][attributes_nr*2];

        for (int i = 0; i < presumption_nr; i++) {
            for (int j = 0; j < attributes_nr; j++) {
                double mean=0;
                int count=0;
                double variance=0;

                /**mean calculation*/
                for( TrainingData trData : trainingData ){
                    if(trData.getAssumption().compareTo(presumptions[i])==0){
                        mean+=trData.getAtributes()[j];
                        count++;
                    }
                }
                mean=mean/count;

                /**variance calculation*/
                for( TrainingData trData : trainingData ){
                    if(trData.getAssumption().compareTo(presumptions[i])==0){
                        variance+=Math.pow(trData.getAtributes()[j] - mean, 2);
                    }
                }
                variance=variance/count;

                results[i][2*j]=mean;
                results[i][2*j+1]=variance;
            }
        }
    }

    public void testClassifier(TestData testData){

        HashMap<String,Double> score = new HashMap<String,Double>(presumption_nr);

        for (int i = 0; i < presumption_nr ; i++) {
            List<Double> subScoreList = new ArrayList<Double>();
            int indx=0;
            for (int j = 0; j < results[0].length ; j+=2) {
                double res=0;
                double mean=results[i][j];
                double variance = results[i][j+1];
                res = 1.0 / (Math.sqrt(2*Math.PI*variance)) * (Math.pow(Math.E,-(Math.pow((testData.getAtributes()[j/2] - mean),2))/(2*variance)))  ;

                subScoreList.add(res);
            }
            double finalScore = 0;
            for (int z = 0; z < subScoreList.size(); z++)
            {
                if (finalScore == 0)
                {
                    finalScore = subScoreList.get(z);
                    continue;
                }

                finalScore = finalScore * subScoreList.get(z);
            }

            score.put(presumptions[i], finalScore * 0.5);
        }
        double max=-1000;
        String answer=null;
        for (int i = 0; i < presumption_nr; i++) {
            if(max<score.get(presumptions[i])){
                max=score.get(presumptions[i]);
                answer=presumptions[i];
            }
        }
        System.out.println("Classification for data"+testData.toString()+" is :");
        System.out.println("=>>"+answer);
    }
}
