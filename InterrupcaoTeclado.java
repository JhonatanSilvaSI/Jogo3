import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterrupcaoTeclado extends KeyAdapter {

	@Override
		public void keyPressed (KeyEvent e) {

		switch (e.getKeyCode ()) {
			case KeyEvent.VK_LEFT: // "<- esquerda"
				if (Cobrinha.direcao != 'D') {
					Cobrinha. direcao = 'E';
				}
				break;
			case KeyEvent. VK_RIGHT: //"-> direita"
				if (Cobrinha.direcao != 'E') {
					Cobrinha.direcao = 'D';
				}
				break;
			case KeyEvent. VK_UP://para cima
				if (Cobrinha.direcao != 'B') {
					Cobrinha.direcao = 'C';
				}
				break;
			case KeyEvent. VK_DOWN: //para baixo
				if (Cobrinha.direcao != 'C') {
					Cobrinha.direcao = 'B';
				}
				break;
			default:
				break;
		}
	}
}