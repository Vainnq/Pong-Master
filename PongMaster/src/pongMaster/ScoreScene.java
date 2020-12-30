package pongMaster;

import java.io.IOException;

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
 * Class that draws score scene and gets data from database.
 */

public class ScoreScene {
	private Text nick, nick1, nick2, nick3, nick4, nick5, nick6, nick7, nick8, nick9, nick10;
	private Text score, score1, score2, score3, score4, score5, score6, score7, score8, score9, score10; 
	private Text date, date1, date2, date3, date4, date5, date6, date7, date8, date9, date10;
	private Text back;
	private Scene scoreScene;
	
	protected ScoreScene(Stage window) throws IOException {
		ClientEvents.startConnection("127.0.0.1", 1905);
		String response = ClientEvents.sendMessage("R");
		String[] scores = response.split("x",30);
		
		// If score is empty make it a - sign
		for(int i = 0 ; i < 30 ; i++) {
			if(scores[i].isEmpty()) {
				scores[i] = "-";
			}
		}
		
		nick = new Text("NICK");
		nick.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick.setStroke(Color.WHITE);
		nick.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick.setLayoutY(PongMaster.gameXandYsize * 0.2);
		
		nick1 = new Text(scores[0]);
		nick1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick1.setStroke(Color.WHITE);
		nick1.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick1.setLayoutY(PongMaster.gameXandYsize * 0.25);

		nick2 = new Text(scores[3]);
		nick2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick2.setStroke(Color.WHITE);
		nick2.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick2.setLayoutY(PongMaster.gameXandYsize * 0.3);
		
		nick3 = new Text(scores[6]);
		nick3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick3.setStroke(Color.WHITE);
		nick3.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick3.setLayoutY(PongMaster.gameXandYsize * 0.35);
		
		nick4 = new Text(scores[9]);
		nick4.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick4.setStroke(Color.WHITE);
		nick4.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick4.setLayoutY(PongMaster.gameXandYsize * 0.4);
		
		nick5 = new Text(scores[12]);
		nick5.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick5.setStroke(Color.WHITE);
		nick5.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick5.setLayoutY(PongMaster.gameXandYsize * 0.45);
		
		nick6 = new Text(scores[15]);
		nick6.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick6.setStroke(Color.WHITE);
		nick6.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick6.setLayoutY(PongMaster.gameXandYsize * 0.5);
		
		nick7 = new Text(scores[18]);
		nick7.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick7.setStroke(Color.WHITE);
		nick7.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick7.setLayoutY(PongMaster.gameXandYsize * 0.55);
		
		nick8 = new Text(scores[21]);
		nick8.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick8.setStroke(Color.WHITE);
		nick8.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick8.setLayoutY(PongMaster.gameXandYsize * 0.6);
		
		nick9 = new Text(scores[24]);
		nick9.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick9.setStroke(Color.WHITE);
		nick9.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick9.setLayoutY(PongMaster.gameXandYsize * 0.65);
		
		nick10 = new Text(scores[27]);
		nick10.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		nick10.setStroke(Color.WHITE);
		nick10.setLayoutX((PongMaster.gameXandYsize - nick.prefWidth(-1)) / 4 - 75);
		nick10.setLayoutY(PongMaster.gameXandYsize * 0.7);
		
		score = new Text("SCORE");
		score.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score.setStroke(Color.WHITE);
		score.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score.setLayoutY(PongMaster.gameXandYsize * 0.2);
		
		score1 = new Text(scores[1]);
		score1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score1.setStroke(Color.WHITE);
		score1.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score1.setLayoutY(PongMaster.gameXandYsize * 0.25);
		
		score2 = new Text(scores[4]);
		score2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score2.setStroke(Color.WHITE);
		score2.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score2.setLayoutY(PongMaster.gameXandYsize * 0.3);
		
		score3 = new Text(scores[7]);
		score3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score3.setStroke(Color.WHITE);
		score3.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score3.setLayoutY(PongMaster.gameXandYsize * 0.35);
		
		score4 = new Text(scores[10]);
		score4.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score4.setStroke(Color.WHITE);
		score4.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score4.setLayoutY(PongMaster.gameXandYsize * 0.4);
		
		score5 = new Text(scores[13]);
		score5.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score5.setStroke(Color.WHITE);
		score5.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score5.setLayoutY(PongMaster.gameXandYsize * 0.45);
		
		score6 = new Text(scores[16]);
		score6.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score6.setStroke(Color.WHITE);
		score6.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score6.setLayoutY(PongMaster.gameXandYsize * 0.5);
		
		score7 = new Text(scores[19]);
		score7.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score7.setStroke(Color.WHITE);
		score7.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score7.setLayoutY(PongMaster.gameXandYsize * 0.55);
		
		score8 = new Text(scores[22]);
		score8.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score8.setStroke(Color.WHITE);
		score8.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score8.setLayoutY(PongMaster.gameXandYsize * 0.6);
		
		score9 = new Text(scores[25]);
		score9.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score9.setStroke(Color.WHITE);
		score9.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score9.setLayoutY(PongMaster.gameXandYsize * 0.65);
		
		score10 = new Text(scores[28]);
		score10.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		score10.setStroke(Color.WHITE);
		score10.setLayoutX((PongMaster.gameXandYsize - score.prefWidth(-1)) / 2);
		score10.setLayoutY(PongMaster.gameXandYsize * 0.7);
		
		date = new Text("DATE");
		date.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date.setStroke(Color.WHITE);
		date.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date.setLayoutY(PongMaster.gameXandYsize * 0.2);
		
		date1 = new Text(scores[2]);
		date1.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date1.setStroke(Color.WHITE);
		date1.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date1.setLayoutY(PongMaster.gameXandYsize * 0.25);
		
		date2 = new Text(scores[5]);
		date2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date2.setStroke(Color.WHITE);
		date2.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date2.setLayoutY(PongMaster.gameXandYsize * 0.3);
		
		date3 = new Text(scores[8]);
		date3.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date3.setStroke(Color.WHITE);
		date3.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date3.setLayoutY(PongMaster.gameXandYsize * 0.35);
		
		date4 = new Text(scores[11]);
		date4.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date4.setStroke(Color.WHITE);
		date4.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date4.setLayoutY(PongMaster.gameXandYsize * 0.4);
		
		date5 = new Text(scores[14]);
		date5.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date5.setStroke(Color.WHITE);
		date5.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date5.setLayoutY(PongMaster.gameXandYsize * 0.45);
		
		date6 = new Text(scores[17]);
		date6.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date6.setStroke(Color.WHITE);
		date6.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date6.setLayoutY(PongMaster.gameXandYsize * 0.5);
		
		date7 = new Text(scores[20]);
		date7.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date7.setStroke(Color.WHITE);
		date7.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date7.setLayoutY(PongMaster.gameXandYsize * 0.55);
		
		date8 = new Text(scores[23]);
		date8.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date8.setStroke(Color.WHITE);
		date8.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date8.setLayoutY(PongMaster.gameXandYsize * 0.6);
		
		date9 = new Text(scores[26]);
		date9.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date9.setStroke(Color.WHITE);
		date9.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date9.setLayoutY(PongMaster.gameXandYsize * 0.65);
		
		date10 = new Text(scores[29]);
		date10.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		date10.setStroke(Color.WHITE);
		date10.setLayoutX(PongMaster.gameXandYsize * 0.75 - date.prefWidth(-1) / 2);
		date10.setLayoutY(PongMaster.gameXandYsize * 0.7);
		
		back = new Text("S TO GO BACK");
		back.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
		back.setStroke(Color.WHITE);
		back.setLayoutX((PongMaster.gameXandYsize - back.prefWidth(-1)) / 2);
		back.setLayoutY(PongMaster.gameXandYsize * 0.9);
		
		Group scoreLayout = new Group();
		scoreLayout.getChildren().addAll(nick, nick1, nick2, nick3, nick4, nick5, nick6, nick7, nick8, nick9, nick10, score, score1, score2, score3, score4, score5, score6, score7, score8, score9, score10, date, date1, date2, date3, date4, date5, date6, date7, date8, date9, date10,  back);	
		scoreScene = new Scene(scoreLayout,PongMaster.gameXandYsize,PongMaster.gameXandYsize);
		
		scoreScene.setFill(Color.BLACK);
		scoreScene.setCursor(Cursor.NONE);
		
		// Next scene switch.
		scoreScene.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.S){
				try {
					ClientEvents.stopConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
				window.setScene(new WelcomeScene(window).getScene());
			}
		});
	}
	protected Scene getScene() {
		return scoreScene;
	}
}
