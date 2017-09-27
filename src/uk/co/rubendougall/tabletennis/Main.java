package uk.co.rubendougall.tabletennis;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private Game game;
    private Menu menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Table Tennis");

        menu = new Menu();
        primaryStage.setScene(menu.getScene());

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
