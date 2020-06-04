package snake;

import java.awt.Color;
//replace method with method object
public class ColorChanger {
	private static int colorsIndex=0;
	private final static Color[] colors = { ColorData.green, ColorData.blue, ColorData.violet, ColorData.red, ColorData.orange,
			ColorData.yellow };
	private static void changeRGB(Color fromRGB, Color toRGB) {
		int newRed = changeFromTo(fromRGB.getRed(), fromRGB.getRed());
		int newGreen = changeFromTo(fromRGB.getGreen(), toRGB.getGreen());
		int newBlue = changeFromTo(fromRGB.getBlue(), toRGB.getBlue());
		fromRGB = new Color(newRed, newGreen, newBlue);
	}

	private static int changeFromTo(int from, int to) {
		int result = from;
		if (from > to)
			result = from - 1;
		else if (from < to)
			result = from + 1;
		return result;
	}
	public static void changeColor(Color fromColor) {
		Color toColor = colors[colorsIndex];
		boolean isSameColor = (fromColor.getRGB() == toColor.getRGB());
		// If the current color is completely changed to the next color, it can be
		// changed to another color.
		if (isSameColor) {
			if (colorsIndex < colors.length - 1)
				colorsIndex++;
			else
				colorsIndex = 0;
			toColor = colors[colorsIndex];
		}
		changeRGB(fromColor, toColor);
	}
}
