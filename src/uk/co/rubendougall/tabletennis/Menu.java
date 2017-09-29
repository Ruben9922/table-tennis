package uk.co.rubendougall.tabletennis;

import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.io.IOException;

public class Menu implements Screen {
    private Main main;
    private Scene scene;

    public Menu() throws IOException {
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void playButtonAction() {
        main.switchToGame();
    }
}
