package grupp4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	public void init() {

	}

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Superski x3000 FIS");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Assets/skier.png")));
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		XML.encode(MainController.getSkierList());
	}

	public static void main(String[] args) {
		launch(args);
	}

}
