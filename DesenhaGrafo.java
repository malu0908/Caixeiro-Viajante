package tb17;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class DesenhaGrafo extends JPanel {
	public int[] seqCidade;
	public double[][] matriz;
	public tb17 score;

	public DesenhaGrafo(int[] seqCidade, double[][] matrizPontos) {
		this.seqCidade = seqCidade;
		this.matriz = matrizPontos;
	}

	public void paintComponent(Graphics grafo) {
		super.paintComponent(grafo);
		this.setBackground(Color.black);

		int escala = 6;
		int j = 1, i = 0;
		grafo.setColor(Color.cyan);
		grafo.fillOval((int) (this.matriz[this.seqCidade[i]][0] * escala - 7),
				(int) (this.matriz[this.seqCidade[i]][1] * escala - 7), 15, 15);
		grafo.setColor(Color.black);
		String x = Integer.toString(this.seqCidade[0]);
		grafo.drawString(x, (int) (this.matriz[this.seqCidade[i]][0] * escala),
				(int) (this.matriz[this.seqCidade[i]][1] * escala));
		for (i = 1; i < seqCidade.length; i++) {
			grafo.setColor(Color.red);
			grafo.fillOval((int) (this.matriz[this.seqCidade[i]][0] * escala - 7),
					(int) (this.matriz[this.seqCidade[i]][1] * escala - 7), 15, 15);
			grafo.setColor(Color.white);
			x = Integer.toString(this.seqCidade[i]);
			grafo.drawString(x, (int) (this.matriz[this.seqCidade[i]][0] * escala),
					(int) (this.matriz[this.seqCidade[i]][1] * escala));
		}
		for (i = 0; i < seqCidade.length && j < seqCidade.length; i++) {
			grafo.setColor(Color.white);
			grafo.drawLine((int) (this.matriz[this.seqCidade[i]][0] * escala),
					(int) (this.matriz[this.seqCidade[i]][1] * escala), (int) (this.matriz[seqCidade[j]][0] * escala),
					(int) (this.matriz[this.seqCidade[j]][1] * escala));
			grafo.setColor(Color.white);
			x = Integer.toString(this.seqCidade[i]);
			j++;
		}

		grafo.setColor(Color.MAGENTA);
		grafo.fillOval((int) (this.matriz[this.seqCidade[j - 1]][0] * escala - 7),
				(int) (this.matriz[this.seqCidade[j - 1]][1] * escala - 7), 15, 15);
		grafo.setColor(Color.white);
		x = Integer.toString(this.seqCidade[i]);
		grafo.drawString(x, (int) (this.matriz[this.seqCidade[j - 1]][0] * escala),
				(int) (this.matriz[this.seqCidade[j - 1]][1] * escala));

		grafo.setColor(Color.pink);
		grafo.drawLine((int) (this.matriz[this.seqCidade[j - 1]][0] * escala),
				(int) (this.matriz[this.seqCidade[j - 1]][1] * escala),
				(int) (this.matriz[this.seqCidade[0]][0] * escala), (int) (this.matriz[this.seqCidade[0]][1] * escala));

		grafo.setColor(Color.white);
		x = Double.toString(this.score.melhorScore);
		grafo.drawString(x, 600, 100);
	}
}
