package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Runs a game of Snake. Uses the arrow keys to move the Snake. Click F1, F2,
 * F3, F4 or F5 to change the color.
 */
public class Window extends JFrame {

	private Engine engine;
	private Properties properties;

	private Window() {
		properties = Properties.Instance();
		engine = createEngine();
		setWindowProperties();
	}

	private Engine createEngine() {

		Container cp = getContentPane();
		GameBoard gameBoard = new GameBoard();
		Engine engine = new Engine(gameBoard);

		int canvasWidth = properties.getSquareSize() * properties.getBoardColumns();
		int canvasHeight = properties.getSquareSize() * properties.getBoardRows();
		engine.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

		addKeyListener(new MyKeyAdapter());

		cp.add(engine);

		return engine;
	}

	private void setWindowProperties() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Snake - Score: 0");
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);// Center window
	}

	private void startGame(Engine engine) {
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

			setBackground(properties.getBackgroundColor());
			gameBoard.paint(graphics);
		}

		public void run() {

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

				// 7/28/2017
				// If the rainbow theme is selected lets update the color
				if (properties.getTheme() == Theme.Rainbow)
					properties.changeBackGroundColor();

				repaint();
			}
		}

	}

	/**
	 * Sleep for 10 milliseconds.
	 */
	private void sleep() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent keyEvent) {

			if (!engine.running && keyEvent.getKeyCode() != KeyEvent.VK_F1 && keyEvent.getKeyCode() != KeyEvent.VK_F2
					&& keyEvent.getKeyCode() != KeyEvent.VK_F3 && keyEvent.getKeyCode() != KeyEvent.VK_F4
					&& keyEvent.getKeyCode() != KeyEvent.VK_F5) {
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
				properties.Dark();
				repaint();
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_F2) {
				properties.Sky();
				repaint();
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_F3) {
				properties.Mud();
				repaint();
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_F4) {
				properties.Sand();
				repaint();
			} else if (keyEvent.getKeyCode() == KeyEvent.VK_F5) {
				properties.Rainbow();
				repaint();
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Window());
	}
}