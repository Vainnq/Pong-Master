package pongMaster;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that reflects player - a rectangle.
 */

public class Player {
	private static final int playerWidth = 100;
	private static final int playerHeight = 15;
	private int playerScore;
	private double playerY = PongMaster.gameXandYsize - (playerHeight + 5);
	private double playerX;
	
	protected Player() {
		playerScore = 0;
		playerX = PongMaster.gameXandYsize / 2 - playerWidth / 2;
	}
	protected void move(double x) {
		playerX = x;
	}
	protected void point() {
		playerScore++;
	}
	protected double getX() {
		return playerX;
	}
	protected double getY() {
		return playerY;
	}
	protected int getScore() {
		return playerScore;
	}
	protected double getPlayerHeight() {
		return playerHeight;
	}
	protected double getPlayerWidth() {
		return playerWidth;
	}
}
