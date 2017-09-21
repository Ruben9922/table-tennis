package uk.co.rubendougall.tabletennis;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Table Tennis");

        Group root = new Group();
        Canvas canvas = new Canvas(800, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        setup(gc);

        final long startTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                double delta = (now - startTime) / 1000000000.0;
            }
        }.start();

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void setup(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();

        // Draw background
        gc.setFill(Color.grayRgb(50));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw court edge
        gc.setStroke(Color.grayRgb(140));
        Point2D edgeSpacing = new Point2D(20, 20);
        gc.strokeRect(edgeSpacing.getX(), edgeSpacing.getY(), canvas.getWidth() - (2 * edgeSpacing.getX()),
                canvas.getHeight() - (2 * edgeSpacing.getY()));

        // Draw centre line
        gc.setStroke(Color.grayRgb(140));
        double centreLineSpacing = 30;
        gc.strokeLine(canvas.getWidth() / 2, centreLineSpacing, canvas.getWidth() / 2,
                canvas.getHeight() - centreLineSpacing);
    }

    private void draw(GraphicsContext gc, double delta) {

    }
}
