package snake;

import java.awt.*;

import board.*;

/**
 * Contains the details and parameters of the game, as well as some presets.
 */
public class Properties {
	private BoardSize boardSize;
	private BoardColor boardColor;
	// Snake start position
	private int start_x;
	private int start_y;
	private Theme theme;
	
	// singleton pattern
	private static Properties properties;

	private Properties() {
		boardSize = new BoardSize_1();
		boardColor = new BoardColorDefault();
		start_x = boardSize.getColumns() / 2;
		start_y = boardSize.getRows() / 2;
		theme = Theme.Dark;
	}

	public static Properties Instance() {
		if (properties == null)
			properties = new Properties();
		return properties;
	}

	public void changeBackGroundColor() {
		Color currentBackgroundColor = boardColor.getBackgroundColor();
		ColorChanger.changeColor(currentBackgroundColor);
		boardColor.setBackgroundColor(currentBackgroundColor);
	}

	public Theme getTheme() {
		return theme;
	}

	public int getBoardColumns() {
		return boardSize.getColumns();
	}

	public int getBoardRows() {
		return boardSize.getRows();
	}

	public int getSquareSize() {
		return boardSize.getSquareSize();
	}

	public Color getSnakeColor() {
		return boardColor.getSnakeColor();
	}

	public Color getFoodColor() {
		return boardColor.getFoodColor();
	}

	public int getStartX() {
		return start_x;
	}

	public int getStartY() {
		return start_y;
	}
	public Color getBackgroundColor() {
		return boardColor.getBackgroundColor();
	}
	public void Rainbow() {
		boardColor.setBackgroundColor(ColorData.green);
		theme = Theme.Rainbow;
	}

	public void Dark() {
		boardColor = new BoardColorDark();
		theme = Theme.Dark;
	}

	public void Sky() {
		boardColor = new BoardColorSky();
		theme = Theme.Sky;
	}

	public void Mud() {
		boardColor = new BoardColorMud();
		theme = Theme.Mud;
	}

	public void Sand() {
		boardColor = new BoardColorSand();
		theme = Theme.Sand;
	}

}