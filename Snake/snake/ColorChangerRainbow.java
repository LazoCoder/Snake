package snake;

import java.awt.Color;

public class ColorChangerRainbow extends ColorChanger {
	private int colorsIndex;
	private final static Color[] colors = { ColorData.green, ColorData.blue, ColorData.violet, ColorData.red, ColorData.orange,
			ColorData.yellow };
	public ColorChangerRainbow() {
		super();
		colorsIndex=0;
	}
	protected Color setToColor(Color fromColor) {
		Color result = colors[colorsIndex];
		boolean isSameColor = (fromColor.getRGB() == result.getRGB());
		// If the current color is completely changed to the next color, it can be
		// changed to another color.
		if (isSameColor) {
			if (colorsIndex < colors.length - 1)
				colorsIndex++;
			else
				colorsIndex = 0;
			result = colors[colorsIndex];
		}
		return result;
	}
}
