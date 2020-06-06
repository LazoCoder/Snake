package snake;

import java.awt.Color;
//replace method with method object
public abstract class ColorChanger {
	protected void changeRGB(Color fromRGB, Color toRGB) {
		int newRed = changeFromTo(fromRGB.getRed(),toRGB.getRed());
		int newGreen = changeFromTo(fromRGB.getGreen(), toRGB.getGreen());
		int newBlue = changeFromTo(fromRGB.getBlue(), toRGB.getBlue());
		fromRGB = new Color(newRed, newGreen, newBlue);
	}

	protected int changeFromTo(int from, int to) {
		int result = from;
		if (from > to)
			result = from - 1;
		else if (from < to)
			result = from + 1;
		return result;
	}
	public void changeColor(Color fromColor) {
		Color toColor=setToColor(fromColor);
		changeRGB(fromColor,toColor);
	}
	//Template method pattern
	protected abstract Color setToColor(Color fromColor);
}
