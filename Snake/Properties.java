import java.awt.*;

/**
 * Contains the details and parameters of the game, as well as some presets.
 */
class Properties {

    // GameBoard.
    static final int BOARD_COLUMNS   = 40;
    static final int BOARD_ROWS      = 20;
    static final int SQUARE_SIZE     = 30;

    // Snake.
    static final int START_X         = BOARD_COLUMNS / 2;
    static final int START_Y         = BOARD_ROWS / 2;

    // Colors.
    static Color backgroundColor    = new Color(53, 53, 53);
    static Color snakeColor         = new Color(0, 255, 255);
    static Color foodColor          = new Color(211, 87, 45);

    static void Dark () {
        Properties.backgroundColor = new Color(53, 53, 53);
        Properties.snakeColor = new Color(0, 254, 254);
        Properties.foodColor = new Color(211, 87, 45);
    }

    static void Sky () {
        Properties.backgroundColor = new Color(25, 181, 254);
        Properties.snakeColor = new Color(255, 255, 255);
        Properties.foodColor = new Color(0, 119, 192);
    }

    static void Mud () {
        Properties.backgroundColor = new Color(94, 44, 11);
        Properties.snakeColor = new Color(246, 196, 163);
        Properties.foodColor = new Color(211, 87, 45);
    }

    static void Sand () {
        Properties.backgroundColor = new Color(253, 227, 167);
        Properties.snakeColor = new Color(142, 68, 173);
        Properties.foodColor = new Color(243, 156, 18);
    }

}