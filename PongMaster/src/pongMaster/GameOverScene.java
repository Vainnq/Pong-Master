package pongMaster;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Class that draws game over scene and collects player nick and score to database.
 */

public class GameOverScene {
	private Text score;
	private Text gameOver;
	private Text back;
	private Text setNick;
	private TextField nick;
	private Scene gameOverScene;
	
	protected GameOverScene(Stage window, String playerScore) {
		gameOver = new Text("GAME OVER");
		gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 75));
		gameOver.setStroke(Color.WHITE);
		gameOver.setLayoutX((PongMaster.gameXandYsize - gameOver.prefWidth(-1)) / 2);
		gameOver.setLayoutY(PongMaster.gameXandYsize * 0.2);
		
		score = new Text("YOU GOT " + playerScore + " POINTS");
		score.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score.setStroke(Color.WHITE);
		score.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score.setLayoutY(PongMaster.gameXandYsize * 0.4);
		
		setNick = new Text("ENTER NICK");
		setNick.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		setNick.setStroke(Color.WHITE);
		setNick.setLayoutX((PongMaster.gameXandYsize - setNick.prefWidth(-1)) / 2);
		setNick.setLayoutY(PongMaster.gameXandYsize * 0.5);
		
		nick = new TextField("NICK");
		// Entering only upper case letters.
		nick.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick.setTextFormatter(new TextFormatter<>((change) -> {
		    change.setText(change.getText().toUpperCase());
		    return change;
		}));
		nick.setMaxWidth(220);
		nick.setAlignment(Pos.CENTER);
		addTextLimiter(nick, 9);
		nick.setLayoutX((PongMaster.gameXandYsize - nick.getMaxWidth()) / 2);
		nick.setLayoutY(PongMaster.gameXandYsize * 0.55);
		nick.setCursor(Cursor.NONE);
		
		back = new Text("CLICK TO GO BACK");
		back.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		back.setStroke(Color.WHITE);
		back.setLayoutX((PongMaster.gameXandYsize - back.prefWidth(-1)) / 2);
		back.setLayoutY(PongMaster.gameXandYsize * 0.9);
		
		Group gameOverLayout = new Group();
		gameOverLayout.getChildren().addAll(gameOver, score, setNick, nick, back);	
		gameOverScene = new Scene(gameOverLayout,PongMaster.gameXandYsize,PongMaster.gameXandYsize);
		
		gameOverScene.setFill(Color.BLACK);
		gameOverScene.setCursor(Cursor.NONE);
		
		// Next scene switch and saving scores.
		gameOverScene.setOnMouseClicked(mouseClick -> {
			try {
				String response = "";
				int connectionAttempts = 10;
				while(response.isEmpty() && connectionAttempts > 0) {
					String data = "Wx" + nick.getText() + "x" + playerScore + "x" + java.time.LocalDate.now();
					ClientEvents.startConnection("127.0.0.1", 1905);
					response = ClientEvents.sendMessage(data);
					System.out.println(response);
			        ClientEvents.stopConnection();
			        if(response.isEmpty()) {
			        	System.out.println("Scores not saved. Trying again.");
			        	connectionAttempts--;
			        }
				}
			} catch (IOException e) {
				System.out.println("Cannot reach server.");
			}
			window.setScene(new WelcomeScene(window).getScene());
		});
	}
	// Limit nick to 9 characters.
	public static void addTextLimiter(final TextField tf, final int maxLength) {
	    tf.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
	            if (tf.getText().length() > maxLength) {
	                String s = tf.getText().substring(0, maxLength);
	                tf.setText(s);
	            }
	        }
	    });
	}
	protected Scene getScene()
	{
		return gameOverScene;
	}
}
