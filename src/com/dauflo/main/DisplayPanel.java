package com.dauflo.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import com.dauflo.perceptron.Perceptron;

public class DisplayPanel extends Component implements Runnable{

	private double[][] datas;
	private double[] curveInfo;
	private Perceptron p;
	
	private double[] prediction;
	private boolean trained = false;

	public DisplayPanel(double[][] datas) {
		this.datas = datas;
		this.curveInfo = new double[] {0, 0};
		this.p = new Perceptron(2);
		
		this.prediction = new double[datas.length];
		
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, AI.width, AI.height);

		for (double[] data : datas) {
			if (data[2] == 1) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.GREEN);
			}
			g.fillOval((int) (data[1] * AI.width), (int) (map((float) (data[0] * AI.height), 0, 1000, 1000, 0)), 3, 3);
		}
		
		double coef = curveInfo[0];
		double offset = curveInfo[1];
		
		g.setColor(Color.WHITE);
		g.drawLine(0, (int) (1000 - offset*1000) , (int) (1000 / coef), (int) (map(1000, 0, 1000, 1000, 0) - offset*1000));
		
		repaint();
	}

	private final float map(float value, float istart, float istop, float ostart, float ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

	@Override
	public void run() {
		while (!trained) {
			trainAgain(datas, prediction);
			trained = true;
			for (int i = 0; i < datas.length; i++) {
				prediction[i] = p.prediction(datas[i]);
			}
			for (int i = 0; i < datas.length; i++) {
				if (datas[i][2] != prediction[i]) {
					trained = false;
					break;
				}
			}
			
			this.curveInfo = this.p.getCurveInfo();
			repaint();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		new Thread(this).interrupt();
	}
	
	private void trainAgain(double[][] datas, double[] prediction) {
		for (int i = 0; i < datas.length; i++) {
			p.learn(datas[i], prediction[i]);
		}
	}
}
