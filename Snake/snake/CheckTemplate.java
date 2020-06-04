
public abstract class CheckTemplate {
	private Snake snake = Snake.get_snake();
	private GameBoard gameBoard = GameBoard.get_board();
	private Food food;
	
	public final void check() {
		food = gameBoard.get_food();	
		if(!checkMovement()) {
			exit();
		};
		checkBounds();
		checkIfAteFood();
	}
	
	public abstract boolean checkMovement();
	
	private void checkBounds () {
        Square sq = snake.getHead();

        boolean tooFarLeft = sq.getX() < 0;
        boolean tooFarRight = sq.getX() >= Properties.BOARD_COLUMNS;
        boolean tooFarUp = sq.getY() < 0;
        boolean tooFarDown = sq.getY() >= Properties.BOARD_ROWS;

        boolean outOfBounds = tooFarLeft || tooFarRight || tooFarUp || tooFarDown;

        if (outOfBounds) {
            exit();
        }
    }

    private void checkIfAteFood() {
        if (isSnakeOnFood()) {
            growSnake();
            gameBoard.set_food(new Food());
        }
    }
    
    private void exit () {
        System.out.println("Final Score: " + gameBoard.getScore());
        System.exit(0);
    }


    private boolean isSnakeOnFood () {
        return snake.getHead().equals(food.get_food());
    }

    private void growSnake () {
        snake.grow();
        gameBoard.addScore(10);
    }

}
