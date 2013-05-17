import java.awt.Color;
import java.awt.Shape;


/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Drawable interface for pong panel drawing shape of drawable in game.
 */
public interface Drawable {
	
	/**
	 * @return Shape of {@link Drawable}.
	 */
	public Shape getShape();
	/**
	 * get Color that want panel to draw.
	 * @return Color that want panel to draw.
	 */
	public Color getColor();
	/**
	 * set color of drawable.
	 * @param color of drawable.
	 */
	public void setColor(Color color);
}
