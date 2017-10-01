package uk.co.rubendougall.tabletennis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Menu menu;
    private Game game;
    private Stage menuStage; // TODO: Put stages inside relevant classes
    private Stage gameStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set up menu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        menu = loader.getController();
        menu.setMain(this);
        menu.setScene(new Scene(root));

        // Set up game
        game = new Game();
        game.setMain(this);

        // Set up stages
        menuStage = primaryStage;
        menuStage.setScene(menu.getScene());
        menuStage.setTitle("Table Tennis");
        menuStage.setResizable(false);
        gameStage = new Stage();
        gameStage.setScene(game.getScene());
        gameStage.setTitle("Table Tennis");
        gameStage.setResizable(true);

        menuStage.show();
    }

    void switchToMenu() {
        gameStage.hide();
        menuStage.show();
    }

    void switchToGame() {
        menuStage.hide();
        game.reset();
        gameStage.show();
        game.start();
    }
}
