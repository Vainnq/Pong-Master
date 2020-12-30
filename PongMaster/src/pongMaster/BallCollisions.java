package pongMaster;

import java.util.concurrent.CountDownLatch;

import javafx.scene.text.Text;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that is responsible for handling ball collisions.
 */

public class BallCollisions implements Runnable {
	private Ball ball;
	private Player player;
	private Text score;
	private boolean ballFall;
	private boolean playerSideBounce;
	private final CountDownLatch countDownLatch;
	
	protected BallCollisions(Ball ball, Player player, Text score, CountDownLatch cdl) {
		this.ball = ball;
		this.player = player;
		this.score = score;
		ballFall = false;
		playerSideBounce = false;
		countDownLatch = cdl;
	}
	protected boolean getBallFall() {
		return ballFall;
	}
	protected int getScore() {
		return player.getScore();
	}
	@Override
	public void run() {
		// Bounce from play area top and player top and sides.
		if(ball.getY() - ball.getRadius() <= 2) {
			ball.changeYdirection();
		} else if(ball.getY() + ball.getRadius() <= player.getY() + player.getPlayerHeight() && ball.getY() >= player.getY() && ((ball.getX() <= player.getX() && ball.getX() >= player.getX() - ball.getRadius()) || (ball.getX() >= player.getX() + player.getPlayerWidth() && ball.getX() <= player.getX() + player.getPlayerWidth() + ball.getRadius()))) {
			// Bounce only one time from player side.
			if(!playerSideBounce) {
				ball.changeXdirection();
				playerSideBounce = true;
			}
		} else if(ball.getY() + ball.getRadius() <= player.getY() + ball.getYspeed() && ball.getY() + ball.getRadius() >= player.getY() && ball.getX() >= player.getX() - (ball.getRadius() * 0.75) && ball.getX() <= player.getX() + player.getPlayerWidth() + (ball.getRadius() * 0.75)) {
			ball.changeYdirection();
			// Player gets point and we refresh score text.
			player.point();
			score.setText(Integer.toString(player.getScore()));
			// Increase ball speed.
			ball.increaseSpeed();
			playerSideBounce = false;
		} 
		// Bounce from play area sides.
		if(ball.getX() - ball.getRadius() <= 2 || ball.getX() + ball.getRadius() >= PongMaster.gameXandYsize - 2) {
			ball.changeXdirection();
		}	
		// End game if player miss ball.
		if (ball.getY() >= PongMaster.gameXandYsize + ball.getRadius()) {
			ballFall = true;
		}
		countDownLatch.countDown();
	}
}
