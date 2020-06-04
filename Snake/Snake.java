import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains a list of Square objects that represent the snake.
 * <br/>
 * The snake moves by taking the last square on its tail and
 * inserting it in front of the head, in the direction that
 * it is going, then deleting the tail.
 */
class Snake implements Iterable<Square> {

    private ArrayList<Square> snakeList;
    private int size = 1;
    private Square tail;

    /**
     * Construct the snake and places it in the center of the screen.
     */
    Snake () {
        this(Properties.START_X, Properties.START_Y);
    }

    /**
     * Construct the snake and place it at the specified location.
     * @param startX    the x coordinate of the location
     * @param startY    the y coordinate of the location
     */
    Snake (int startX, int startY) {
        snakeList = new ArrayList<>();
        snakeList.add(new Square(Entity.Snake, startX, startY));
    }

    /**
     * Get the size of the snake.
     * @return          size of the snake
     */
    int getSize () {
        return size;
    }

    /**
     * Direction the Snake left one Square.
     * @return  true if the Snake did not collide with itself
     */
    boolean moveLeft () {
        return move(Direction.LEFT);
    }

    /**
     * Direction the Snake right one Square.
     * @return  true if the Snake did not collide with itself
     */
    boolean moveRight () {
        return move(Direction.RIGHT);
    }

    /**
     * Direction the Snake up one Square.
     * @return  true if the Snake did not collide with itself
     */
    boolean moveUp () {
        return move(Direction.UP);
    }

    /**
     * Direction the Snake down one Square.
     * @return  true if the Snake did not collide with itself
     */
    boolean moveDown () {
        return move(Direction.DOWN);
    }

    /**
     * Direction the Snake in the specified direction.
     * @param direction  the direction to direction the Snake.
     * @return      true if the Snake did not collide with itself
     */
    private synchronized boolean move (Direction direction) {

        int xOffset = 0;
        int yOffset = 0;

        if (direction == Direction.LEFT) {
            xOffset = -1;
        } else if (direction == Direction.RIGHT) {
            xOffset = 1;
        } else if (direction == Direction.UP) {
            yOffset = -1;
        } else if (direction == Direction.DOWN) {
            yOffset = 1;
        }

        // Get a reference to the current head and get its coordinates.
        Square currentHead = getHead();
        int oldX = currentHead.getX();
        int oldY = currentHead.getY();

        // Create a new head, relative to the old one, in the appropriate direction.
        Square head = new Square(Entity.Snake, oldX + xOffset, oldY + yOffset);

        if (contains(head)) return false; // If snake collided with itself.
        snakeList.add(0, head); // Add the new head if no collision.
        removeTail();

        return true;
    }

    private synchronized void removeTail () {

        tail = snakeList.get(snakeList.size() - 1);

        if (snakeList.size() > size) {
            snakeList.remove(snakeList.size() - 1);
        }
    }

    /**
     * Get the last Square in the Snake.
     * @return  the tail of the Snake
     */
    Square getTail () {
        return tail; // The most recent square the snake was on that it is not on now.
    }

    /**
     * Get the first Square in the Snake.
     * @return  the head of the Snake
     */
    Square getHead () {
        return snakeList.get(0);
    }

    /**
     * Grows the snake by one tile.
     */
    void grow () {
        size++;
    }

    /**
     * Grows the snake by a specified number of tiles.
     * @param x     the number of tiles to grow the snake by
     */
    public void grow (int x) {
        size += x;
    }

    public synchronized Iterator<Square> iterator () {
        return snakeList.iterator();
    }

    /**
     * Check to see if the Snake contains a particular Square (if it is on a Square)
     * @param sq    the specified Square to check for
     * @return      true if the Square is part of the Snake
     */
    boolean contains (Square sq) {
        for (Square element : this) {
            if (element.equals(sq)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();

        for (Square sq : this) {
            sb.append(sq);
            sb.append("\n");
        }

        return new String(sb);
    }

}