import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Game frame which have to set game panel.
 */
public class GameFrame extends JFrame {
	private GamePanel board;
	/**
	 * Constructor of GameFrame.
	 */
	public GameFrame() {
		super("0     :     0");
		this.setPreferredSize(new Dimension(600,550));
		init();
	}
	
	/**
	 * Initialize the component.
	 */
	private void init() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container content = this.getContentPane();
		board = new GamePanel(600, 500 , this);
		content.add(board,BorderLayout.CENTER);
	}
	
	/**
	 * get GamePanel of frame.
	 * @return GamePanel of this frame.
	 */
	public GamePanel getBoard(){
		return board;
	}

	/**
	 * run the component.
	 */
	public void run(){
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * @param p1Score score of player1.
	 * @param p2Score score of player2.
	 */
	public void updateScore(int p1Score , int p2Score){
		this.setTitle(p1Score + "     :     " + p2Score);
	}
}
