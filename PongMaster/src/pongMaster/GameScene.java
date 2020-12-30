package pongMaster;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that draws game scene and implements game mechanics.
 */

public class GameScene {
	private Text score;
	private Text pause;
	private Rectangle playerRectangle;
	private Rectangle leftBorder;
	private Rectangle topBorder;
	private Rectangle rightBorder;
	private Circle ballCircle;
	private Scene gameScene;
	private Player player;
	private Ball ball;
	private Game game;
	private static boolean isPaused;
	
	protected GameScene(Stage window) {
		isPaused = false;
		player = new Player();
		ball = new Ball();
		
		score = new Text(Integer.toString(player.getScore()));
		score.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score.setStroke(Color.WHITE);
		score.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score.setLayoutY(PongMaster.gameXandYsize * 0.5);
		
		pause = new Text("PAUSE");
		pause.setFont(Font.font("Verdana", FontWeight.BOLD, 75));
		pause.setStroke(Color.WHITE);
		pause.setLayoutX((PongMaster.gameXandYsize - pause.prefWidth(-1)) / 2);
		pause.setLayoutY(PongMaster.gameXandYsize * 0.2);
		pause.setVisible(isPaused);
		
		playerRectangle = new Rectangle(player.getX(), player.getY(), player.getPlayerWidth(), player.getPlayerHeight());
		playerRectangle.setFill(Color.WHITE);
		
		leftBorder = new Rectangle(0, 0, 2, PongMaster.gameXandYsize);
		leftBorder.setFill(Color.WHITE);
		
		topBorder = new Rectangle(0, 0, PongMaster.gameXandYsize, 2);
		topBorder.setFill(Color.WHITE);
		
		rightBorder = new Rectangle(PongMaster.gameXandYsize - 2, 0, 2, PongMaster.gameXandYsize);
		rightBorder.setFill(Color.WHITE);
		
		ballCircle = new Circle(ball.getX(), ball.getY(), ball.getRadius(), Color.WHITE);
		
		Group gameLayout = new Group();
		gameLayout.getChildren().addAll(score, pause, playerRectangle, ballCircle, leftBorder, topBorder, rightBorder);	
		gameScene = new Scene(gameLayout,PongMaster.gameXandYsize,PongMaster.gameXandYsize);
		
		gameScene.setFill(Color.BLACK);
		gameScene.setCursor(Cursor.NONE);
		
		game = new Game(player, ball, ballCircle, playerRectangle, gameScene, score,window);
		game.play();
		
		gameScene.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.P){
				isPaused = !isPaused;
				pause.setVisible(isPaused);
				if(isPaused) {
					game.stop();
				} else {
					game.play();
				}
			}
		});
	}
	protected static boolean getPause()
	{
		return isPaused;
	}
	protected Scene getScene() {
		return gameScene;
	}
}
