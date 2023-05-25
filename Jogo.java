import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class Jogo extends JPanel implements Runnable {

	public static final int LARGURA_TELA = 1300;
	public static final int ALTURA_TELA = 750;
	public static final int TAMANHO_BLOCO = 50;
	public static final int UNIDADES = LARGURA_TELA * ALTURA_TELA / (TAMANHO_BLOCO * TAMANHO_BLOCO);
	public static final int INTERVALO = 200;
	public static final String NOME_FONTE = "Ink Free";
	public boolean GameOver = false;
	Cobrinha objetoCobra;
	Comida objetoComida;
	Random random;

	public Jogo () {

		setPreferredSize (new Dimension(LARGURA_TELA, ALTURA_TELA) );
		setBackground(Color.WHITE);
		setFocusable (true);
		//adicionar a Interrupção das teclas para detectar a tecla que o usuário pressionou
		addKeyListener(new InterrupcaoTeclado () );
		objetoCobra = new Cobrinha ();
		objetoComida = new Comida ();
		objetoComida.CriarNovaPosicao ();
		GameOver = false;
	}

	@Override

	public void paintComponent(Graphics g) {
		super.paintComponent (g);
		desenharTela (g);
	}

	public void desenharTela (Graphics g) {

		if (GameOver == false) {

			objetoComida. Desenhar (g);
			objetoCobra. Desenhar (g);
			//mostrar a pontuação na tela
			g.setColor(Color.red);
			g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			String texto = "Pontos: " + objetoCobra.QuantidadeComida;
			g.drawString (texto, (LARGURA_TELA - metrics.stringWidth(texto)) / 2, g.getFont().getSize());
		}else {
			fimDeJogo (g);
		}
	}	

	public void fimDeJogo (Graphics g) {
		//mostrar mensagem "Fim de jogo"
		g.setColor (Color.red);
		g. setFont (new Font (NOME_FONTE, Font. BOLD, 75));
		FontMetrics fonteFinal = getFontMetrics (g.getFont ());
		g.drawString("\uD83D\uDE1D Fim do Jogo.", (LARGURA_TELA - fonteFinal.stringWidth ("Fim do Jogo")) / 2, ALTURA_TELA / 2);
	}

	@Override
	public void run() {

		while (GameOver == false)
		{
			objetoCobra.andar();
			try {
				if (objetoCobra.alcancouComida () == true)
					objetoComida. CriarNovaPosicao ();
			} catch (LineUnavailableException | IOException el) {
				// TODO Auto-generated catch block
				el.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			GameOver= objetoCobra.VerificarGameOver();

				repaint(); //chama, internamente, o método "paint Component"
			
			try {
				Thread.sleep (100); //esperar 16 ms
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}

