package snake;
public class UpBehavior extends CheckTemplate implements SnakeMoveBehavior{

	private Snake snake = Snake.get_snake();
	private GameBoard gameBoard = GameBoard.get_board();
	
	@Override
	public boolean checkMovement() {
		if (!snake.moveUp()) { // Check to see if the Snake has run into itself.
			return false;
		}
		return true;
	}
	
	@Override
	public void action() {
		check();
		gameBoard.set_movement(Direction.UP);
	}
}
