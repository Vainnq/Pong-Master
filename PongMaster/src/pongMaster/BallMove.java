package pongMaster;

import java.util.concurrent.CountDownLatch;
import javafx.scene.shape.Circle;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that is responsible for moving a ball.
 */

public class BallMove implements Runnable {
	private Ball ball;
	private Circle ballCircle;
	private final CountDownLatch countDownLatch;
	
	protected BallMove(Ball ball, Circle ballCircle, CountDownLatch cdl) {
		this.ball = ball;
		this.ballCircle = ballCircle;
		countDownLatch = cdl;
	}

	@Override
	public void run() {
		ball.moveX(ball.getX() + ball.getXspeed());
		ball.moveY(ball.getY() + ball.getYspeed());
		ballCircle.setCenterX(ball.getX());
		ballCircle.setCenterY(ball.getY());
		countDownLatch.countDown();
	}
}
