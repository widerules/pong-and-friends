import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.Constructor;

/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Ball of pong game is drawable and runable which use to run frame by frame.
 */
public class Ball implements Runnable,Drawable{
	private Color color;
	private final double radius = 10;
	private double spd_x;
	private double spd_y;
	private double x;
	private double y;
	
	/**
	 * Ball {@link Constructor}.
	 */
	public Ball() {
		double side = Math.random()-0.5;
		color = Color.green;
	}
	
	/**
	 * Renew Ball Speed.
	 */
	public void restartSpd(){
		spd_x = (Math.random()*1+1);//*(side/Math.abs(side));
		spd_y = Math.random()*3+2;
	}

	/**
	 * @return speen in x axis
	 */
	public double getSpd_x() {
		return spd_x;
	}

	/**
	 * @return speed in y axis
	 */
	public double getSpd_y() {
		return spd_y;
	}
	
	/**
	 * Set speed in x axis.
	 * @param spd_x speed in x axis.
	 */
	public void setSpd_x(double spd_x){
		this.spd_x = spd_x;
	}
	
	/**
	 * Set speed in y axis.
	 * @param spd_y speed in y axis.
	 */
	public void setSpd_y(double spd_y){
		this.spd_y = spd_y;
	}

	/**
	 * @return x is position in x axis
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x is position in x axis
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return y is position in y axis
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y is position in y axis
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Runable for loop the animation.
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		x += spd_x;
		y += spd_y;
	}

	/**
	 * @see Drawable#getShape()
	 */
	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(x-radius, y-radius, radius, radius);
	}

	/**
	 * @see Drawable#getColor()
	 */
	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * @see Drawable#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
	}
}
