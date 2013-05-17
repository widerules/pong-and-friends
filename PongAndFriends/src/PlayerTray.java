import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Player hitable which have score and connection to socket controller.
 */
public class PlayerTray extends Hitable implements Observer ,Runnable{
	
	private int score = 0;
	private SocketReciever connector;
	private Color color;
	private int delay = 5;
	private int positionPercent = 50;
	private double maximumY;
	private double minimumY;

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param min
	 * @param max
	 * @param port 8000 for player1 and 9000 for player2.
	 */
	public PlayerTray(int x, int y, int width, int height ,double min , double max ,int port) {
		super(x-(width/2), y-(height/2), width, height);
		connector = new SocketReciever(port);
		connector.addObserver(this);
		color = Color.red;
		maximumY = max-getHeight();
		minimumY = min;
	}

	@Override
	public void reflexBall(Ball ball) {
		ball.setSpd_x(ball.getSpd_x()*-1);
		double angle = (ball.getY() - getCenterY())/this.getHeight();
		ball.setSpd_y((ball.getSpd_x()+5)*angle);
	}
	
	public void setBoundController(double max,double min){
		this.maximumY = max-getHeight();
		this.minimumY = min;
	}
	
	/**
	 * Set the percent of position from bound size in game(0-100).
	 * @param percent
	 */
	public void setPositionPerent(int percent){
		if(percent > 100) percent = 100;
		else if(percent < 0) percent = 0;
		positionPercent = percent;
	}
	
	/**
	 * @return the percent of position form bound size in game(0-100).
	 */
	public int getPositionPerent(){
		return positionPercent;
	}
	
	/**
	 * @return the percent of position convert from real point position of game.
	 */
	public double positionPercentToY(){
		return (positionPercent/100.0)*(maximumY-minimumY)+minimumY;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		int recieve = (Integer)arg1;
		if(recieve >= 0) this.setPositionPerent(recieve);
	}
	
	public int getCenterY(){
		return this.getY() + (this.getHeight()/2); 
	}
	
	public int getCenterX(){
		return this.getX() + (this.getWidth()/2); 
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public void run() {
		this.setY(this.getY() + (int)Math.round((positionPercentToY() - getY())/delay));
	}
	
	/**
	 * @return add player score by 1.
	 */
	public int addScore(){
		return ++score;
	}
	
	/**
	 * @return score of player.
	 */
	public int getScore(){
		return score;
	}

}
