package uk.co.rubendougall.tabletennis;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Table Tennis");

        game = new Game();
        primaryStage.setScene(game.getScene());

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
