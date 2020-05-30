package board;
import java.awt.*;
public abstract class BoardColor {
	protected Color backgroundColor;
	protected Color snakeColor;
	protected Color foodColor;
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public Color getSnakeColor() {
		return snakeColor;
	}
	public Color getFoodColor() {
		return foodColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public void setSnakeColor(Color snakeColor) {
		this.snakeColor = snakeColor;
	}
	public void setFoodColor(Color foodColor) {
		this.foodColor = foodColor;
	}
}
