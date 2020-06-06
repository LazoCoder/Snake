package board;

import snake.Theme;

public class BoardColorFactory {
	public static BoardColor getBoardColor(Theme theme) {
		BoardColor result;
		switch (theme) {
		case Dark:
			result = new BoardColorDark();
			break;
		case Sky:
			result = new BoardColorSky();
			break;
		case Mud:
			result = new BoardColorMud();
			break;
		case Sand:
			result = new BoardColorSand();
			break;
		default:
			result = new BoardColorDefault();
			break;
		}
		return result;
	}
}
