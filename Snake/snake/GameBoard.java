package snake;
import java.awt.*;
import java.util.Random;

/**
 * Represents the environment where the Snake moves a food spawns.
 * <br/>
 * There are some special rules as to how the Snake can move. If the Snake's size
 * is 1, it can move in any direction. If the Snake's size is greater than 1, it
 * cannot move 180 degrees. Example: if the Snake is moving right, it cannot
 * immediately change its direction to left because it would run into itself.
 */
class GameBoard  {
	private static GameBoard gameBoard;
    private Food food;
    private Snake snake;
    private int score = 0;
    private Properties properties = Properties.Instance();

    /**
     * Keep track of the last move so that the Snake cannot do 180 degree turns,
     * only 90 degree turns.
     */
    private Direction movement;
    private SnakeMoveBehavior snakeMoveBehavior;
    private Direction lastMove = movement;

    /**
     * Constructs the board.
     */
    GameBoard () {
    	gameBoard = this;
        this.snake = Snake.get_snake();
        this.food = new Food();
        this.snakeMoveBehavior = new DownBehavior();
        properties=Properties.Instance();
        update();
    }
    
    public static GameBoard get_board()
    {
    	if(gameBoard == null)
    	{
    		gameBoard = new GameBoard();
    	}
    	return gameBoard;
    }

    /**
     * Move the Snake.
     */
    void update () {
        moveSnake();
    }

    void set_food(Food food) {
    	this.food = food;
    }
    
    Food get_food() {
    	return food;
    }
    
    void set_movement(Direction direction) {
    	movement = direction;
    }
    
    void set_behavior(SnakeMoveBehavior snakeMoveBehavior) {
    	this.snakeMoveBehavior = snakeMoveBehavior;
    }

    /**
     * Sets the direction of the Snake to go left.
     */
    void directionLeft () {
        if (lastMove != Direction.RIGHT || getSnakeSize() == 1) {
            set_movement(Direction.LEFT);
            set_behavior(new LeftBehavior());
        }
    }

    /**
     * Sets the direction of the Snake to go right.
     */
    void directionRight () {
        if (lastMove != Direction.LEFT || getSnakeSize() == 1) {
            set_movement(Direction.RIGHT);
            set_behavior(new RightBehavior());
        }
    }

    /**
     * Sets the direction of the Snake to go up.
     */
    void directionUp () {
        if (lastMove != Direction.DOWN || getSnakeSize() == 1) {
        	set_movement(Direction.UP);
            set_behavior(new UpBehavior());
        }
    }

    /**
     * Sets the direction of the Snake to go down.
     */
    void directionDown () {
        if (lastMove != Direction.UP || getSnakeSize() == 1) {
        	set_movement(Direction.DOWN);
            set_behavior(new DownBehavior());
        }
    }

    /**
     * Moves the Snake one square, according to its direction.
     */
    private void moveSnake () {
    	snakeMoveBehavior.action();
        lastMove = movement;
    }

    private int getSnakeSize () {
        return snake.getSize();
    }

    int getScore() {
        return score;
    }
    void addScore(int score) {
    	this.score += score;
    }

    void paint (Graphics graphics) {

        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintSnake(g);
        paintFood(g);
    }

    private void paintSnake (Graphics2D g) {
        int x, y;
        int corner = properties.getSquareSize() / 3;

        for (Square sq : snake) {

            x = sq.getX() * properties.getSquareSize();
            y = sq.getY() * properties.getSquareSize();

            g.setColor(properties.getSnakeColor());
            g.fillRoundRect(x + 1, y + 1, properties.getSquareSize() - 2,
                    properties.getSquareSize() - 2, corner, corner);

        }
    }

    private void paintFood (Graphics2D g) {
        int x = food.get_X() * properties.getSquareSize();
        int y = food.get_Y() * properties.getSquareSize();
        int corner = properties.getSquareSize() / 3;

        g.setColor(properties.getFoodColor());
        g.fillRoundRect(x + 1, y + 1, properties.getSquareSize() - 2,
                properties.getSquareSize() - 2, corner, corner);
    }

    @Override
    public String toString () {

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < properties.getBoardRows(); y++) {
            for (int x = 0; x < properties.getBoardColumns(); x++) {
                Square sq = new Square(x, y);

                if (snake.contains(sq)) {
                    sb.append("S");
                } else if (food.equals(sq)) {
                    sb.append("F");
                } else {
                    sb.append("-");
                }

                sb.append(" ");

            }
            sb.append("\n");
        }

        return new String(sb);
    }

}
