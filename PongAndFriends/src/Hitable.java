import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Abstract of drawable but ball can be hit which use to make pong's wall.
 */
public abstract class Hitable implements Drawable{
	private int pos_x;
	private int pos_y;
	private int width;
	private int height;
	/**
	 * Constructor of Hitable.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Hitable(int x , int y ,int width,int height) { 
		this.pos_x = x;
		this.pos_y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @param x is position in x axis.
	 */
	public void setX(int x){
		pos_x = x;
	}
	
	/**
	 * @param y is position in y axis.
	 */
	public void setY(int y){
		pos_y = y;
	}
	
	/**
	 * @return x is position in x axis.
	 */
	public int getX(){
		return pos_x;
	}
	
	/**
	 * @return y is position in y axis.
	 */
	public int getY(){
		return pos_y;
	}
	
	/**
	 * @param width of that hitable.
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * @param height of that hitable.
	 */
	public void setHeight(int height){
		this.height = height;
	}
	
	/**
	 * @return height of that hitable.
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * @return width of that hitable.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * @see Drawable#getShape()
	 */
	public Shape getShape(){
		//System.out.println(this.pos_x + "   "  + this.pos_y + "  :  " + this.width + "   " + this.height);
		return new Rectangle2D.Double(pos_x,pos_y,width,height);
	}
	
	/**
	 * make ball in param get bounce.
	 * @param ball
	 */
	public abstract void reflexBall(Ball ball);
}
