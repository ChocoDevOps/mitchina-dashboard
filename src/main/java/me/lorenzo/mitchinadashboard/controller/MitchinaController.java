package me.lorenzo.mitchinadashboard.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.lorenzo.mitchinadashboard.MitchinaApplication;
import me.lorenzo.mitchinadashboard.callback.SentCallback;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class MitchinaController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Button fancyButton;
    @FXML
    private ImageView sadFace;
    @FXML
    private Label tosButton;
    @FXML
    private TextField messageTextField;
    @FXML
    private Button sendButton;

    private int stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = 0;

        fancyButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleClick(fancyButton);
            }
        });

        tosButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(MitchinaApplication.class.getResource("tos-view.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 389, 109);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setTitle("TOS MEME");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            }
        });

        sendButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String message = messageTextField.getText();
                sendMessage(message, new SentCallback() {
                    @Override
                    public void onSuccess() {
                        sendButton.setText("Message sent successfully");
                    }

                    @Override
                    public void onFailure() {
                        sendButton.setText("Failed to send message");
                    }
                });
            }
        });
    }

    private void handleClick(Button button) {
        switch (stage) {
            case 0:
                button.setText("You seem to be disrespectful :(");
                break;
            case 1:
                button.setText("Ouch");
                break;
            case 2:
                button.setText("Ok, i'm changing title :(");
                title.setText("Disrespectful dashboard");
                break;
            case 3:
                button.setText("Well, are you enjoying it?");
                sadFace.setVisible(true);
        }
        stage = stage + 1;
    }

    private void sendMessage(String message, SentCallback sentCallback) {
        new Thread(() -> {
            Socket socket = null;
            try {
                socket = new Socket("185.25.204.204", 9581);
                if(socket.isConnected()) {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    printWriter.println(message);
                    printWriter.flush();
                    Platform.runLater(sentCallback::onSuccess);
                }
            } catch (IOException e) {
                Platform.runLater(sentCallback::onFailure);
            }
        }).start();
    }
}