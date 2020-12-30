package pongMaster;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that implements game mechanics.
 */

public class Game {
	private Timeline timeline;
	private BallMove ballMove;
	private BallCollisions ballCollisions;
	private PlayerMove playerMove;
	private Stage window;
	private CountDownLatch countDownLatch;
	
	protected Game(Player player, Ball ball, Circle ballCircle, Rectangle playerRectangle, Scene gameScene, Text score, Stage window) {
		timeline = new Timeline(new KeyFrame(Duration.millis(10), arg0 -> {
			try {
				start(arg0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		ballMove = new BallMove(ball, ballCircle, countDownLatch);	
		ballCollisions = new BallCollisions(ball, player, score, countDownLatch);
		playerMove = new PlayerMove(player, playerRectangle, gameScene, countDownLatch);
		countDownLatch = new CountDownLatch(3);
		this.window = window;
	}
	protected void start(ActionEvent actionEvent) throws InterruptedException {
		runJobs(countDownLatch);
		countDownLatch.await(4, TimeUnit.MILLISECONDS);
		if(ballCollisions.getBallFall()) {
			timeline.stop();
			window.setScene(new GameOverScene(window, Integer.toString(ballCollisions.getScore())).getScene());
		}
	}
	private void runJobs(CountDownLatch countDownLatch) {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
        threadPool.schedule(playerMove, 0, TimeUnit.MILLISECONDS);
        threadPool.schedule(ballCollisions, 0, TimeUnit.MILLISECONDS);
        threadPool.schedule(ballMove, 3, TimeUnit.MILLISECONDS);
        threadPool.shutdown();
    }
	protected void stop() {
		timeline.stop();
	}
	protected void play() {
		timeline.play();
	}
}
