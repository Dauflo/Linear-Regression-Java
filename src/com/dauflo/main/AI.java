package com.dauflo.main;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.dauflo.perceptron.Perceptron;

public class AI extends JFrame {
	
	public static final int width = 1000, height = 1000;

	public static void main(String[] args) {

		double[][] datas = generationDataSet();
		
		JFrame myFrame = new JFrame("Linear Regression");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(width, height);
		myFrame.setResizable(false);

		Container myPanel = myFrame.getContentPane();
		myPanel.setPreferredSize(new Dimension(width, height));

		DisplayPanel dp = new DisplayPanel(datas);
		myPanel.add(dp);

		myFrame.setContentPane(myPanel);

		myFrame.pack();
		myFrame.setVisible(true);
	}

	public static double[][] generationDataSet() {
		double[][] dataSet = new double[1000][3];

		for (int i = 0; i < dataSet.length; i++) {
			for (int j = 0; j < dataSet[1].length; j++) {
				if (j != 2) {
					dataSet[i][j] = Math.random();
				} else {
					dataSet[i][j] = ((dataSet[i][0] >= dataSet[i][1] * 0.6 + 0.2) ? 1 : 0);
				}
			}
		}
		return dataSet;
	}
}
