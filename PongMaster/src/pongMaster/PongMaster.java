package pongMaster;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Krzysztof Czyrkiewicz
 * Main PONG MASTER class.
 */
// --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml

public class PongMaster extends Application {
	public final static int gameXandYsize = 700;
	@Override
	public void start(Stage window) throws Exception {
		WelcomeScene welcomeScene = new WelcomeScene(window);
		window.setScene(welcomeScene.getScene());
		window.setResizable(false);
		window.setTitle("PONG MASTER");
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
