import javax.swing.*;

public class TelaPrincipal extends JFrame {

	public static void main(String[] args) {

		//inicar o objeto que controla o jogo
		Jogo game = new Jogo ();
		//criar a tela
		JFrame jframe = new JFrame ("Cobrinha");
		jframe.add(game);
		jframe.setLocationRelativeTo(null);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		jframe.setVisible (true);
		//iniciar a Thread do jogo
		new Thread(game).start();

	}
}

