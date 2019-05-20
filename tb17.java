package tb17;
import java.io.*;
import Lista.*;
import java.util.*;
import javax.swing.JFrame;

public class tb17 {
	public static int tamanho = 58;
	public static double matriz[][] = new double[tamanho][2];
	public static double matrizAdj[][] = new double[tamanho][tamanho];
	public static boolean visitados[] = new boolean[tamanho];
	public static int seqCidade[] = new int[tamanho];
	public static JFrame janela = new JFrame();
	public static double melhorScore;

	public static void PlotaGrafo() {
		janela.setSize(750, 750);
		janela.setTitle("Caixeiro Viajante - Rota");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setVisible(true);
		DesenhaGrafo grafo = new DesenhaGrafo(seqCidade, matriz);
		janela.add(grafo);
	}

	public static Lista procuraMelhorPosicao(double m[][], Lista l, double SomaAnt) {
		int vezes = 0;
		double Soma = 0.0;

		while (vezes < 1000) {
			Random random = new Random();
			int j = random.nextInt(tamanho);

			int valor = l.buscaValor(j).value;
			// troca o conteúdo dos índices i e j do vetor
			int melhorPosicao = mudaPosicao(j, m, l, valor, SomaAnt);
			l.insereIndice(valor, melhorPosicao);
			Soma = calculaDistancia(m, l);
			if (Soma > SomaAnt) {
				l.removePorIndice(melhorPosicao);
				l.insereIndice(valor, j);
			}
			vezes++;
		}
		return l;
	}

	public static int mudaPosicao(int i, double m[][], Lista l, int valor, double SomaAnt) {
		double Soma = 0.0;

		int j = 0;
		int melhorIndice = j;

		Node removido = l.removeList(valor);
		while (l.buscaValor(j) != null) {
			l.insereIndice(removido.value, j);

			Soma = calculaDistancia(m, l);
			if (Soma < SomaAnt) {
				SomaAnt = Soma;
				melhorIndice = j;
				melhorScore = Soma;
				Node atual = l.top;
				int w = 0;
				while (atual != null) {
					seqCidade[w] = atual.value;
					w++;
					atual = atual.next;
				}
				janela.repaint();
				try {Thread.sleep(10);} catch (InterruptedException ex) {}
			}
			removido = l.removeList(valor);
			j++;
		}
		return melhorIndice;
	}

	public static Lista procuraMaiorOrigem(double m[][], Lista l, boolean visitados[]) {
		double SomaAnt = 500000.0;
		double Soma = 0.0;
		Lista melhor = new Lista();
		for (int i = 0; i < m.length; i++) {
			l.insereIndice(i, 0);
			visitados[i] = true;
			vizinhoMaisProx(m, l, 1, i, visitados);

			// inicializando com false novamente
			for (int j = 0; j < visitados.length; j++) {
				visitados[j] = false;
			}

			Soma = calculaDistancia(m, l);
			melhorScore = Soma;

			if (Soma < SomaAnt) {
				SomaAnt = Soma;
				melhor.clean();
				melhor.clonar(l);
				janela.repaint();
				try { Thread.sleep(200);} catch (InterruptedException ex) {}
			}
			l.clean();
		}
		return melhor;
	}

	public static int buscaMelhor(double m[][], int i, boolean visitados[]) {
		double menor = 100000.0;
		int j;
		int indice = 0;
		for (j = 0; j < m.length; j++) {
			// se o vertice nao tiver sido visitado
			if (visitados[j] == false)
				// se a distancia do vertice atual for menor que o menor anterior o menor recebe
				// ele e o indice recebe o vertice
				if (m[i][j] < menor && i != j) {
					menor = m[i][j];
					indice = j;
				}
		}
		return indice;
	}

	public static Lista vizinhoMaisProx(double m[][], Lista l, int j, int w, boolean visitados[]) {
		for (int i = 1; i < m.length; i++) {
			int melhor = buscaMelhor(m, w, visitados);
			visitados[melhor] = true;
			l.insereIndice(melhor, j);
			j++;
			w = melhor;
		}
		Node atual = l.top;
		int i = 0;
		while (atual != null) {
			seqCidade[i] = atual.value;
			i++;
			atual = atual.next;
		}
		return l;
	}

	public static double calculaDistancia(double m[][], Lista l) {
		double soma = 0.0;
		// soma as distancias das cidades consecutivas do vetor v
		for (int i = 0; i < m.length - 1; i++) {
			Node valor1 = l.buscaValor(i);
			Node valor2 = l.buscaValor(i + 1);
			soma += m[valor1.value][valor2.value];
		}
		// soma as distancias da primeira cidade com a ultima
		soma += m[l.top.value][l.down.value];
		return soma;
	}

	public static void main(String[] args) throws IOException {
		FileReader arq = new FileReader("/home/2018.1.08.015/Desktop/tb17/src/new.txt");

		BufferedReader lerArq = new BufferedReader(arq);

		// lendo as coordenadas
		String linha = lerArq.readLine();
		String[] x = null;
		int i = 0;
		while (linha != null) {
			x = linha.split(",");
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = Double.parseDouble(x[j]);
			}
			linha = lerArq.readLine();
			i++;
		}

		// calcula as distancias para montar a matriz de adjcencia
		for (i = 0; i < matrizAdj.length; i++) {
			for (int j = 0; j < matrizAdj[0].length; j++) {
				double distanciaX = matriz[j][0] - matriz[i][0];
				double distanciaY = matriz[j][1] - matriz[i][1];

				double distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));
				matrizAdj[i][j] = distancia;
				matrizAdj[j][i] = distancia;
			}
		}

		Lista cidades = new Lista();
		for (i = 0; i < tamanho; i++) {
			cidades.toList(i);
		}

		Node atual = cidades.top;
		i = 0;
		while (atual != null) {
			seqCidade[i] = atual.value;
			i++;
			atual = atual.next;
		}
		PlotaGrafo();
		try { Thread.sleep(400);} catch (InterruptedException ex) {}
		cidades.clean();

		// aplica o metodo do vizinhoMaisProximo procurando a melhor origem
		cidades = procuraMaiorOrigem(matrizAdj, cidades, visitados);
		double SomaAnt = calculaDistancia(matrizAdj, cidades);

		atual = cidades.top;
		i = 0;
		while (atual != null) {
			seqCidade[i] = atual.value;
			i++;
			atual = atual.next;
		}

		// aplica o metodo que procura a melhor posicao para determinadas cidades
		cidades = procuraMelhorPosicao(matrizAdj, cidades, SomaAnt);
		double Soma = calculaDistancia(matrizAdj, cidades);
		System.out.println("Distância Final: " + Soma);

	}
}
