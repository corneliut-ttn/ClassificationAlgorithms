package com.classifiers.bayesnaive;

import java.util.Arrays;

/**
 * Created by Cornelius on 05.01.2016.
 */
public class TestData {
    private String assumption;
    private double atributes[];

    public TestData(double attributes[]){
        this.atributes=new double[attributes.length];
        this.atributes=attributes;
    }

    public String getAssumption() {
        return assumption;
    }

    public void setAssumption(String assumption) {
        this.assumption = assumption;
    }

    public double[] getAtributes() {
        return atributes;
    }

    public void setAtributes(double[] atributes) {
        this.atributes = atributes;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "assumption='" + assumption + '\'' +
                ", atributes=" + Arrays.toString(atributes) +
                '}';
    }
}
