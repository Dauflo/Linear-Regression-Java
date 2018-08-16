package com.dauflo.perceptron;

import java.util.Arrays;

public class Perceptron {
	
	private double[] weight;
	private double bias;
	
	private double learningRate = 0.1;
	
	public Perceptron(int nbWeight) {
		weight = new double[nbWeight];
		for (int i = 0; i < nbWeight; i++) {
			weight[i] = 0;
		}
		bias = 0;
	}
	
	public void learn(double[] data, double predicted) {
		for (int i = 0; i < weight.length; i++) {
			weight[i] += learningRate * (data[2] - predicted) * data[i];
		}
		bias += learningRate * (data[2] - predicted);
	}
	
	public int prediction(double[] data) {
		double activation = 0;
		for (int i = 0; i < weight.length; i++) {
			activation += weight[i] * data[i];
		}
		activation += bias;
		return ((activation >= 0) ? 1 : 0);
	}
	
	public double[] displayWeight() {
//		System.out.println(Arrays.toString(weight) + " " + bias);
		return weight;
	}
	
	public double[] getCurveInfo() {
//		System.out.println("Intercept " + -bias/weight[0]);
//		System.out.println("Slope " + weight[1] / -weight[0]);
		
		double[] data = new double[2];
		data[0] = weight[1] / -weight[0];
		data[1] = -bias/weight[0];
		
		return data;
	}
	
	
}
