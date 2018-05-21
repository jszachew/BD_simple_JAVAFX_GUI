package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Controller controller = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("sample.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane,600,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("hurtownia");
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);

    }
}
