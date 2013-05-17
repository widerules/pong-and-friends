import java.awt.Color;
import java.awt.Graphics2D;


/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Wall hitable in horizontal side.
 */
public class HorizontalWall extends Hitable {
	private Color color;
	public HorizontalWall(int x, int y, int width, int height) {
		super(x, y, width, height);
		color = Color.yellow;
	}

	@Override
	public void reflexBall(Ball ball) {
		ball.setSpd_y(ball.getSpd_y()*-1);
	}
	
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

}
