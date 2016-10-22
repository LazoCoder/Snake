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

    private Square food;
    private Snake snake;
    private int score = 0;

    /**
     * Keep track of the last move so that the Snake cannot do 180 degree turns,
     * only 90 degree turns.
     */
    private Direction movement = Direction.DOWN;
    private Direction lastMove = movement;

    /**
     * Constructs the board.
     */
    GameBoard () {
        this.snake = new Snake();
        newFood();
        update();
    }

    /**
     * Move the Snake.
     */
    void update () {
        moveSnake();
    }

    /**
     * Creates food at a random location. Only one piece of food can be spawned at a time.
     */
    private void newFood () {
        Random rX = new Random();
        Random rY = new Random();
        food = new Square(
                Square.Entity.Food,
                rX.nextInt(Properties.BOARD_COLUMNS),
                rY.nextInt(Properties.BOARD_ROWS));

        // If food is spawned inside the snake, try spawning it elsewhere.
        if (snake.contains(food)) {
            newFood();
        }
    }

    /**
     * Sets the direction of the Snake to go left.
     */
    void directionLeft () {
        if (lastMove != Direction.RIGHT || getSnakeSize() == 1) {
            movement = Direction.LEFT;
        }
    }

    /**
     * Sets the direction of the Snake to go right.
     */
    void directionRight () {
        if (lastMove != Direction.LEFT || getSnakeSize() == 1) {
            movement = Direction.RIGHT;
        }
    }

    /**
     * Sets the direction of the Snake to go up.
     */
    void directionUp () {
        if (lastMove != Direction.DOWN || getSnakeSize() == 1) {
            movement = Direction.UP;
        }
    }

    /**
     * Sets the direction of the Snake to go down.
     */
    void directionDown () {
        if (lastMove != Direction.UP || getSnakeSize() == 1) {
            movement = Direction.DOWN;
        }
    }

    /**
     * Moves the Snake one square, according to its direction.
     */
    private void moveSnake () {

        if (movement == Direction.LEFT) {
            moveSnakeLeft();
        } else if (movement == Direction.RIGHT) {
            moveSnakeRight();
        } else if (movement == Direction.UP) {
            moveSnakeUp();
        } else if (movement == Direction.DOWN) {
            moveSnakeDown();
        }

        lastMove = movement;
    }

    private void moveSnakeLeft () {
        if (!snake.moveLeft()) { // Check to see if the Snake has run into itself.
            exit();
        }
        checkBounds();
        checkIfAteFood();
        movement = Direction.LEFT;
    }

    private void moveSnakeRight () {
        if (!snake.moveRight()) { // Check to see if the Snake has run into itself.
            exit();
        }
        checkBounds();
        checkIfAteFood();
        movement = Direction.RIGHT;
    }

    private void moveSnakeUp () {
        if (!snake.moveUp()) { // Check to see if the Snake has run into itself.
            exit();
        }
        checkBounds();
        checkIfAteFood();
        movement = Direction.UP;
    }

    private void moveSnakeDown () {
        if (!snake.moveDown()) { // Check to see if the Snake has run into itself.
            exit();
        }
        checkBounds();
        checkIfAteFood();
        movement = Direction.DOWN;
    }

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
            newFood();
        }
    }

    private int getSnakeSize () {
        return snake.getSize();
    }

    private void exit () {
        System.out.println("Final Score: " + getScore());
        System.exit(0);
    }

    int getScore () {
        return score;
    }

    private boolean isSnakeOnFood () {
        return snake.getHead().equals(food);
    }

    private void growSnake () {
        snake.grow();
        score += 10;
    }

    void paint (Graphics graphics) {

        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        paintSnake(g);
        paintFood(g);
    }

    private void paintSnake (Graphics2D g) {
        int x, y;
        int corner = Properties.SQUARE_SIZE / 3;

        for (Square sq : snake) {

            x = sq.getX() * Properties.SQUARE_SIZE;
            y = sq.getY() * Properties.SQUARE_SIZE;

            g.setColor(Properties.snakeColor);
            g.fillRoundRect(x + 1, y + 1, Properties.SQUARE_SIZE - 2,
                    Properties.SQUARE_SIZE - 2, corner, corner);

        }
    }

    private void paintFood (Graphics2D g) {
        int x = food.getX() * Properties.SQUARE_SIZE;
        int y = food.getY() * Properties.SQUARE_SIZE;
        int corner = Properties.SQUARE_SIZE / 3;

        g.setColor(Properties.foodColor);
        g.fillRoundRect(x + 1, y + 1, Properties.SQUARE_SIZE - 2,
                Properties.SQUARE_SIZE - 2, corner, corner);
    }

    @Override
    public String toString () {

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < Properties.BOARD_ROWS; y++) {
            for (int x = 0; x < Properties.BOARD_COLUMNS; x++) {
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
