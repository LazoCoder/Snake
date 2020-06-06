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
	private Coordinate startXY;
	private Theme theme;
	private ColorChanger colorChanger;
	// ---------singleton pattern------------
	private static Properties properties;

	private Properties() {
		boardSize = new BoardSizeDefault();
		boardColor = new BoardColorDefault();
		startXY = new Coordinate(boardSize.getColumns()/2,boardSize.getRows()/2);
		theme = Theme.Dark;
		colorChanger=new ColorChangerRainbow();
	}

	public static Properties Instance() {
		if (properties == null)
			properties = new Properties();
		return properties;
	}
	//----------------------------------------
	public void changeBackGroundColor() {
		Color currentBackgroundColor = boardColor.getBackgroundColor();
		colorChanger.changeColor(currentBackgroundColor);
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
		return startXY.getX();
	}

	public int getStartY() {
		return startXY.getY();
	}
	public Color getBackgroundColor() {
		return boardColor.getBackgroundColor();
	}
	public void Rainbow() {
		boardColor.setBackgroundColor(ColorData.green);
		theme = Theme.Rainbow;
	}
	
	//Factory Method Pattern
	public void Dark() {
		boardColor = BoardColorFactory.getBoardColor(Theme.Dark);
		theme = Theme.Dark;
	}

	public void Sky() {
		boardColor = BoardColorFactory.getBoardColor(Theme.Sky);
		theme = Theme.Sky;
	}

	public void Mud() {
		boardColor = BoardColorFactory.getBoardColor(Theme.Mud);
		theme = Theme.Mud;
	}

	public void Sand() {
		boardColor = BoardColorFactory.getBoardColor(Theme.Sand);
		theme = Theme.Sand;
	}

}