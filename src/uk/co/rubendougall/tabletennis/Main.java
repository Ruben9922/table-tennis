package uk.co.rubendougall.tabletennis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage stage;
    private Game game;
    private Menu menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        stage.setTitle("Table Tennis");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        menu = loader.getController();
        menu.setMain(this);
        menu.setScene(new Scene(root));

        game = new Game();
        game.setMain(this);

        stage.setScene(menu.getScene());

        stage.setResizable(false);
        stage.show();
    }

    void switchToGame() {
        game.reset();
        game.start();
        stage.setScene(game.getScene());
    }
}
