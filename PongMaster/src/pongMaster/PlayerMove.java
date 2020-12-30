package pongMaster;

import java.util.concurrent.CountDownLatch;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that is responsible for moving a player rectangle.
 */

public class PlayerMove implements Runnable {
	private Player player;
	private Rectangle playerRectangle;
	private Scene gameScene;
	private final CountDownLatch countDownLatch;
	
	protected PlayerMove(Player player, Rectangle playerRectangle, Scene gameScene, CountDownLatch cdl) {
		this.player = player;
		this.playerRectangle = playerRectangle;
		this.gameScene = gameScene;
		countDownLatch = cdl;
	}
	@Override
	public void run() {
		gameScene.setOnMouseMoved(mouse -> {
			if(mouse.getX() >= player.getPlayerWidth() / 2 + 2 && mouse.getX() + player.getPlayerWidth() / 2 <= PongMaster.gameXandYsize - 2 && !GameScene.getPause()) {
				player.move(mouse.getX() - player.getPlayerWidth() / 2);
				playerRectangle.setX(player.getX());
			}
		});
		gameScene.setOnMouseDragged(drag -> {
			if(drag.getX() >= player.getPlayerWidth() / 2 + 2 && drag.getX() + player.getPlayerWidth() / 2 <= PongMaster.gameXandYsize - 2 && !GameScene.getPause()) {
				player.move(drag.getX() - player.getPlayerWidth() / 2);
				playerRectangle.setX(player.getX());
			}
		});	
		countDownLatch.countDown();
	}
}
