import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Runs a game of Snake.
 * Uses the arrow keys to move the Snake.
 * Click F1, F2, F3 or F4 to change the color.
 */
public class Window extends JFrame {

    private Engine engine;

    private Window() {
        engine = createEngine();
        setWindowProperties();
    }

    private Engine createEngine () {

        Container cp = getContentPane();
        GameBoard gameBoard = new GameBoard();
        Engine engine = new Engine(gameBoard);

        int canvasWidth = Properties.SQUARE_SIZE * Properties.BOARD_COLUMNS;
        int canvasHeight = Properties.SQUARE_SIZE * Properties.BOARD_ROWS;
        engine.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        addKeyListener(new MyKeyAdapter());

        cp.add(engine);

        return engine;
    }

    private void setWindowProperties () {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake - Score: 0");
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void startGame (Engine engine) {
        Thread th = new Thread(engine);
        th.start();
    }

    /**
     * Contains the game loop.
     */
    private class Engine extends JPanel implements Runnable {

        private GameBoard gameBoard;
        private boolean running = false;

        private Engine(GameBoard gameBoard) {
            this.gameBoard = gameBoard;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            // Ensures that it will run smoothly on Linux.
            if (System.getProperty("os.name").equals("Linux")) {
                Toolkit.getDefaultToolkit().sync();
            }

            setBackground(Properties.backgroundColor);
            gameBoard.paint(graphics);
        }

        public void run () {

            long lastTime = System.nanoTime();
            double elapsedTime = 0.0;
            double FPS = 15.0;

            // Game loop.
            while (true) {

                long now = System.nanoTime();
                elapsedTime += ((now - lastTime) / 1_000_000_000.0) * FPS;
                lastTime = System.nanoTime();

                if (elapsedTime >= 1) {
                    gameBoard.update();
                    setTitle("Snake - Score: " + gameBoard.getScore());
                    elapsedTime--;
                }

                sleep();
                repaint();
            }
        }

    }

    /**
     * Sleep for 10 milliseconds.
     */
    private void sleep () {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent keyEvent) {

            if (!engine.running) {
                startGame(engine);
                engine.running = true;
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                engine.gameBoard.directionLeft();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                engine.gameBoard.directionRight();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                engine.gameBoard.directionUp();
            } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                engine.gameBoard.directionDown();
            }

            if (keyEvent.getKeyCode() == KeyEvent.VK_F1) {
                Properties.Dark();
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F2) {
                Properties.Sky();
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F3) {
                Properties.Mud();
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_F4) {
                Properties.Sand();
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Window());
    }
}