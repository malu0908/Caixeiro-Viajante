package tb17;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class DesenhaGrafo extends JPanel {
	public int[] rota; // rota completa para imprimir
	public double[][] matriz; // matriz das posicoes, aquele arquivo de entrada

	public DesenhaGrafo(int[] rota, double[][] matrizPontos) {
		this.rota = rota; // inicializacao recebendo a rota
		this.matriz = matrizPontos; // inicializacao rebendo o matrizEntrada
	}

	@Override
	public void paintComponent(Graphics grafo) {
		super.paintComponent(grafo);
		this.setBackground(Color.black);

		int j = 1, i = 0;
		grafo.setColor(Color.cyan);
		grafo.fillOval((int) (this.matriz[this.rota[i]][0] * 5), (int) (this.matriz[this.rota[i]][1] * 5), 20, 20);
		for (i = 1; i < rota.length; i++) { //desenha as cidades
			if(i % 2 == 0)
				grafo.setColor(Color.red); // senao colore de vermelho
			else
				grafo.setColor(Color.blue); // senao colore de vermelho	
			grafo.fillOval((int) (this.matriz[this.rota[i]][0] * 5), (int) (this.matriz[this.rota[i]][1] * 5), 20, 20);
		}
		for (i = 0; i < rota.length && j < rota.length; i++) {
			grafo.setColor(Color.white); // colore as arestas de preto
			grafo.drawLine((int) (this.matriz[this.rota[i]][0] * 5), (int) (this.matriz[this.rota[i]][1] * 5),
					(int) (this.matriz[rota[j]][0] * 5), (int) (this.matriz[this.rota[j]][1] * 5));
			j++;
		}
		
		//colore o ultimo vertice de magenta
		grafo.setColor(Color.MAGENTA);
		grafo.fillOval((int) (this.matriz[this.rota[j - 1]][0] * 5), (int) (this.matriz[this.rota[j - 1]][1] * 5), 20, 20);
		
		//colore o vertice que interliga o primeiro vertice com o ultimo de amarelo
		grafo.setColor(Color.yellow);
		grafo.drawLine((int) (this.matriz[this.rota[j - 1]][0] * 5), (int) (this.matriz[this.rota[j - 1]][1] * 5),
				(int) (this.matriz[this.rota[0]][0] * 5), (int) (this.matriz[this.rota[0]][1] * 5));
	}
}
