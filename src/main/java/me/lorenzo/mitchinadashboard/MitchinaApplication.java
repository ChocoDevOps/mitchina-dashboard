package me.lorenzo.mitchinadashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MitchinaApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MitchinaApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 315, 394);
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("Mitchina dashboard v0.1");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}