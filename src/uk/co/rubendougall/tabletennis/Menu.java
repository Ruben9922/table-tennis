package uk.co.rubendougall.tabletennis;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Menu implements Screen {
    private Scene scene;

    Menu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        scene = new Scene(root);
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
