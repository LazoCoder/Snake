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
    
    // 7/27/2017
    // Add constant color changing background
    static Color green = new Color(36, 165, 107);
    static Color blue = new Color(42, 97, 203);
    static Color violet = new Color(150, 62, 238);
    static Color red = new Color(230, 61, 61);
    static Color orange = new Color(223, 150, 77);
    static Color yellow = new Color(230, 227, 67);
    
    static Color[] colors = {green, blue, violet, red, orange, yellow};
    
    static Color currentRGB = colors[0];
    
    static Color nextColorRGB;
    
    static boolean newColor = true;
    static int index = 0;
    static Theme theme = Theme.Dark;
    
    static void changeColor() {    
        if (newColor) {
            if (index < colors.length - 1) index++;
            else index = 0;
            nextColorRGB = colors[index];
            newColor = false;
        }
        
        boolean change = false;

        if (currentRGB.getRed() > nextColorRGB.getRed()) {
            currentRGB = new Color(currentRGB.getRed() - 1, currentRGB.getGreen(), currentRGB.getBlue()); 
            change = true;
        }
        else if (currentRGB.getRed() < nextColorRGB.getRed()) {
            currentRGB = new Color(currentRGB.getRed() + 1, currentRGB.getGreen(), currentRGB.getBlue()); 
            change = true;
        }
        if (currentRGB.getGreen() > nextColorRGB.getGreen()) {
            currentRGB = new Color(currentRGB.getRed(), currentRGB.getGreen() - 1, currentRGB.getBlue()); 
            change = true;
        }
        else if (currentRGB.getGreen() < nextColorRGB.getGreen()) {
            currentRGB = new Color(currentRGB.getRed(), currentRGB.getGreen() + 1, currentRGB.getBlue()); 
            change = true;
        }
        if (currentRGB.getBlue() > nextColorRGB.getBlue()) {
            currentRGB = new Color(currentRGB.getRed(), currentRGB.getGreen(), currentRGB.getBlue() - 1); 
            change = true;
        }
        else if (currentRGB.getBlue() < nextColorRGB.getBlue()) {
            currentRGB = new Color(currentRGB.getRed(), currentRGB.getGreen(), currentRGB.getBlue() + 1); 
            change = true;
        }

        if (!change) newColor = true;
        
        Properties.backgroundColor = currentRGB;
    }
    
    static Theme getTheme() {
        return theme;
    }
    
    static void Rainbow () {
        Properties.backgroundColor = colors[0];
        Properties.theme = Properties.Theme.Rainbow;
    }

    static void Dark () {
        Properties.backgroundColor = new Color(53, 53, 53);
        Properties.snakeColor = new Color(0, 254, 254);
        Properties.foodColor = new Color(211, 87, 45);
        Properties.theme = Properties.Theme.Dark;
    }

    static void Sky () {
        Properties.backgroundColor = new Color(25, 181, 254);
        Properties.snakeColor = new Color(255, 255, 255);
        Properties.foodColor = new Color(0, 119, 192);
        Properties.theme = Properties.Theme.Sky;
    }

    static void Mud () {
        Properties.backgroundColor = new Color(94, 44, 11);
        Properties.snakeColor = new Color(246, 196, 163);
        Properties.foodColor = new Color(211, 87, 45);
        Properties.theme = Properties.Theme.Mud;
    }

    static void Sand () {
        Properties.backgroundColor = new Color(253, 227, 167);
        Properties.snakeColor = new Color(142, 68, 173);
        Properties.foodColor = new Color(243, 156, 18);
        Properties.theme = Properties.Theme.Sand;
    }
    
    enum Theme {
        Rainbow,
        Dark,
        Sky,
        Mud,
        Sand
    }

}