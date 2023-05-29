import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Cobrinha {
	public static char direcao = 'D'; // C - cima, B - Baixo, E - Esquerda, D - Direita
	private int TamanhoDaCobra;
	public int QuantidadeComida;

	// vetores que ir�o armazenar as posi��es (x,y) do corpo da cobra
	private final int[] eixoX = new int[Jogo.UNIDADES];
	private final int[] eixoY = new int[Jogo.UNIDADES];

	public Cobrinha() {
		TamanhoDaCobra = 6;
		QuantidadeComida = 0;
	}

	public void Desenhar(Graphics g) {

		// desenhar o corpo da cobra
		for (int i = 0; i < TamanhoDaCobra; i++) {
			if (i == 0) { // 0 � a posi��o da cabe�a: pintar de verde
				g.setColor(Color.green);
				g.fillRect(eixoX[0], eixoY[0], Jogo.TAMANHO_BLOCO, Jogo.TAMANHO_BLOCO);
			} else {
				g.setColor(new Color(45, 180, 0));
				g.fillRect(eixoX[i], eixoY[i], Jogo.TAMANHO_BLOCO, Jogo.TAMANHO_BLOCO);
			}
		}
	}

	public void andar() {
		// deslocar a cobra
		for (int i = TamanhoDaCobra; i > 0; i--) {
			eixoX[i] = eixoX[i - 1];
			eixoY[i] = eixoY[i - 1];
		}

		// atualizar a posi��o de acordo com as teclas que o usu�rio pressionou
		switch (direcao) {
			case 'C':
				eixoY[0] = eixoY[0] - Jogo.TAMANHO_BLOCO;
				break;
			case 'B':
				eixoY[0] = eixoY[0] + Jogo.TAMANHO_BLOCO;
				break;
			case 'E':
				eixoX[0] = eixoX[0] - Jogo.TAMANHO_BLOCO;
				break;
			case 'D':
				eixoX[0] = eixoX[0] + Jogo.TAMANHO_BLOCO;
				break;
			default:
				break;

		}
	}

	public void TocarEfeito() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		File arquivo = new File("C:/Users/angel/OneDrive/Desktop/Cobrinha/Cobrinha/src/Somcomida.wav");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivo);

		// Cria um clip para reproduzir o arquivo
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		// Reproduz o �udio
		clip.start();
	}

	public boolean alcancouComida() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		// verificar se atingiu a comida
		if (eixoX[0] == Comida.posicao_x && eixoY[0] == Comida.posicao_y) {
			TamanhoDaCobra++; // aumtar o tamanho da cobra
			QuantidadeComida++; // atualizar a quantidade de comidas que a cobra j� comeu

			// som
			TocarEfeito();
			return true;
		} else
			return false;
	}

	public boolean VerificarGameOver() {

		boolean perdeu = false;
		// A cabe�a bateu no corpo?
		for (int i = TamanhoDaCobra; i > 0; i--) {
			if (eixoX[0] == eixoX[i] && eixoY[0] == eixoY[i]) {
				perdeu = true;
				break;
			}
		}

		// A cabe�a tocou uma das bordas Direita ou esquerda?
		if (eixoX[0] < 0 || eixoX[0] > Jogo.LARGURA_TELA) {
			perdeu = true;
		}
		// A cabe�a tocou o piso ou o teto?
		if (eixoY[0] < 0 || eixoY[0] > Jogo.ALTURA_TELA) {
			perdeu = true;
		}
		return perdeu;
	}
}