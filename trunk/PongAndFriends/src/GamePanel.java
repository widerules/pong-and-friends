import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * GamePanel use to compute the animate of all game.
 */
public class GamePanel extends JPanel implements ActionListener{
	private GameFrame frame;
	private int startTime = 3000;
	private int hole = 80;
	private Ball ball;
	private Timer timer;
	private Timer timerStart;
	private List<Drawable> drawables = new ArrayList<Drawable>();
	private List<Runnable> runnables = new ArrayList<Runnable>();
	private List<Hitable> walls = new ArrayList<Hitable>();
	private PlayerTray player1;
	private PlayerTray player2;
	
	/**
	 * Constructor of GamePanel.
	 * @param width
	 * @param height
	 * @param frame
	 */
	public GamePanel(int width, int height ,GameFrame frame) {
		super();
		timerStart = new Timer(startTime, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
				timerStart.stop();
			}
		});
		
		
		this.frame = frame;
		this.setPreferredSize(new Dimension(width, height)); 
		this.setBackground(Color.black);
		ball = new Ball();
		timer = new Timer(10, this);
		ControlKeyListener controlListener = new ControlKeyListener();
		this.addKeyListener(controlListener);
		this.setFocusable(true);
		timer.addActionListener(controlListener);
		int wid = this.getPreferredSize().width;
		int hei = this.getPreferredSize().height;
		player1 = new PlayerTray(20, hei/2, 10, hole , 10 , hei -10 , 8888);
		player2 = new PlayerTray(wid-20, hei/2, 10, hole, 10 , hei -10 , 8889);
		init();
		openingFX();
		
	}
	
	/**
	 * initializing the game sound.
	 * */
	private void openingFX(){
		Clip clip;
		AudioInputStream stream;
	    AudioFormat format;
	    DataLine.Info info;
	    try{
	    	stream = AudioSystem.getAudioInputStream(new File("src/bg.wav"));
	    	format = stream.getFormat();
	    	info = new DataLine.Info(Clip.class, format);
	    	clip = (Clip) AudioSystem.getLine(info);
	    	clip.open(stream);
	    	clip.loop(Clip.LOOP_CONTINUOUSLY);
	    	//clip.stop();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * renew the game.
	 */
	public void newGame(){
		ball.restartSpd();
		stop();
		
		timerStart.start();
	}
	
	/**
	 * initialize gameplay
	 */
	public void init() {
		drawables.clear();
		runnables.clear();
		walls.clear();
		newGame();
		int wid = this.getPreferredSize().width;
		int hei = this.getPreferredSize().height;
		
		
		drawables.add(player1);
		drawables.add(player2);
		walls.add(player1);
		walls.add(player2);
		
		walls.add(new HorizontalWall(0, 0, wid, 10));
		walls.add(new HorizontalWall(0, hei-10, wid, 10));
		for(Hitable wall : walls) drawables.add(wall);
		drawables.add(ball);
		runnables.add(ball);
		runnables.add(player1);
		runnables.add(player2);
		ball.setX(wid/2);
		ball.setY(hei/2);
	}

	/**
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphic = (Graphics2D)g;
		
		for(Drawable drawable : drawables){
			graphic.setColor(drawable.getColor());
			graphic.draw(drawable.getShape());
			graphic.fill(drawable.getShape());
		}
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
        ball.setSpd_x(ball.getSpd_x() + Math.signum(ball.getSpd_x()) * 0.01);
	}
	
	/**
	 * @param e
	 */
	public void keyEvent(char e){
		switch(e){
		case 'w' :
			player1.setPositionPerent(player1.getPositionPerent() - 2);
			break;
		case 's' :
			player1.setPositionPerent(player1.getPositionPerent() + 2);
			break;
		case 'o' :
			player2.setPositionPerent(player2.getPositionPerent() - 2);
			break;
		case 'l' :
			player2.setPositionPerent(player2.getPositionPerent() + 2);
			break;
		}
	}
	
	/**
	 * Start game enterframe.
	 */
	public void start(){
		timer.start();
	}
	
	/**
	 * Stop game enterframe.
	 */
	public void stop(){
		timer.stop();
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Runnable runnable : runnables){
			runnable.run();
		}
		for(Hitable wall : walls){
			if(ball.getShape().intersects((Rectangle2D)wall.getShape())) wall.reflexBall(ball);
			while(ball.getShape().intersects((Rectangle2D)wall.getShape())) ball.run(); 
		}
		Dimension d = this.getPreferredSize();
		if(ball.getX()>d.width || ball.getX()<0) {
			if(ball.getX()>d.width) player1.addScore();
			else player2.addScore();
			init();
			newGame();
			startTime = 300;
			frame.updateScore(player1.getScore(), player2.getScore());
		}
		this.repaint();
	}
	
	public class ControlKeyListener extends KeyAdapter implements ActionListener{
		Set<Character> keyCode = new HashSet<Character>();
		
		@Override
		public void keyPressed(KeyEvent e) {
			keyCode.add(e.getKeyChar());
		}

		@Override
		public void keyReleased(KeyEvent e) { 
			keyCode.remove(e.getKeyChar());
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(Character c : keyCode){
				keyEvent(c);
			}
		}
	}

}
