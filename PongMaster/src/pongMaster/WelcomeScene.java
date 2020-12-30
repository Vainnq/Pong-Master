package pongMaster;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that draws welcome scene which is a basic UI.
 */

public class WelcomeScene {
	private Text title;
	private Text controls1;
	private Text controls2;
	private Text pause;
	private Text rules;
	private Text scores;
	private Text end;
	private Scene welcomeScene;
	
	protected WelcomeScene(Stage window) {	
		title = new Text("PONG MASTER");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
		title.setStroke(Color.WHITE);
		title.setLayoutX((PongMaster.gameXandYsize - title.prefWidth(-1)) / 2);
		title.setLayoutY(PongMaster.gameXandYsize * 0.2);
		
		controls1 = new Text("USE MOUSE TO MOVE PADDLE");
		controls1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		controls1.setStroke(Color.WHITE);
		controls1.setLayoutX((PongMaster.gameXandYsize - controls1.prefWidth(-1)) / 2);
		controls1.setLayoutY(PongMaster.gameXandYsize * 0.4);
		
		pause = new Text("P TO PAUSE");
		pause.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		pause.setStroke(Color.WHITE);
		pause.setLayoutX((PongMaster.gameXandYsize - pause.prefWidth(-1)) / 2);
		pause.setLayoutY(PongMaster.gameXandYsize * 0.45);
		
		rules = new Text("BOUNCE THE BALL AS MANY TIMES AS YOU CAN");
		rules.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		rules.setStroke(Color.WHITE);
		rules.setLayoutX((PongMaster.gameXandYsize - rules.prefWidth(-1)) / 2);
		rules.setLayoutY(PongMaster.gameXandYsize * 0.5);
		
		controls2 = new Text("CLICK TO START");
		controls2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		controls2.setStroke(Color.WHITE);
		controls2.setLayoutX((PongMaster.gameXandYsize - controls2.prefWidth(-1)) / 2);
		controls2.setLayoutY(PongMaster.gameXandYsize * 0.7);
		
		scores = new Text("S TO VIEW BEST 10 SCORES");
		scores.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		scores.setStroke(Color.WHITE);
		scores.setLayoutX((PongMaster.gameXandYsize - scores.prefWidth(-1)) / 2);
		scores.setLayoutY(PongMaster.gameXandYsize * 0.9);
		
		end = new Text("Q TO QUIT");
		end.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		end.setStroke(Color.WHITE);
		end.setLayoutX((PongMaster.gameXandYsize - end.prefWidth(-1)) / 2);
		end.setLayoutY(PongMaster.gameXandYsize * 0.95);
		
		Group welcomeLayout = new Group();
		welcomeLayout.getChildren().addAll(title, controls1, controls2, pause, rules, scores, end);	
		welcomeScene = new Scene(welcomeLayout,PongMaster.gameXandYsize,PongMaster.gameXandYsize);
		
		welcomeScene.setFill(Color.BLACK);
		welcomeScene.setCursor(Cursor.NONE);
		
		// Next scene switches.
		welcomeScene.setOnMouseClicked(mouseClick -> window.setScene(new GameScene(window).getScene()));
		welcomeScene.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.Q){
				Platform.exit();
			}
			else if(key.getCode() == KeyCode.S){
				try {
					window.setScene(new ScoreScene(window).getScene());
				} catch (IOException e) {
					System.out.println("Cannot reach server.");
				}
			}
		});
	}
	protected Scene getScene() {
		return welcomeScene;
	}
}
