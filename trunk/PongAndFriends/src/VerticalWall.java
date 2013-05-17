import java.awt.Color;


/**
 * @author Krittayot Techasombooranakit 5510545976
 * @author Sikarin Lanamwong 5510546174
 * Wall hitable in vertical side.
 */
public class VerticalWall extends Hitable{
	private Color color;
	public VerticalWall(int x, int y, int width, int height){
		super(x, y, width, height);
		color = Color.yellow;
	}

	@Override
	public void reflexBall(Ball ball) {
		ball.setSpd_x(ball.getSpd_x()*-1);
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
