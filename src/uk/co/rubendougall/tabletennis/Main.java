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
    private Bat leftBat;
    private Bat rightBat;
    private Input input = new Input();
    private double lastTime = System.nanoTime();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Table Tennis");

        Group root = new Group();
        Scene scene = new Scene(root);
        Canvas canvas = new Canvas(800, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        setup(gc);

        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double delta = calculateDelta(currentTime);
                draw(gc, delta);

            }
        }.start();

        scene.setOnKeyPressed(input::handleKeyPressed);
        scene.setOnKeyReleased(input::handleKeyReleased);

        root.getChildren().add(canvas);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double calculateDelta(long currentTime) {
        double delta = (currentTime - lastTime) / 1000000.0;
        lastTime = currentTime;
        return delta;
    }

    private void setup(GraphicsContext gc) {
        Canvas canvas = gc.getCanvas();

        // Instantiate bats
        double batSpacingX = 30;
        leftBat = new Bat(gc, batSpacingX);
        rightBat = new Bat(gc, canvas.getWidth() - batSpacingX);
    }

    private void draw(GraphicsContext gc, double delta) {
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

        leftBat.update(gc, delta, input);
        rightBat.update(gc, delta, input);

        leftBat.draw(gc);
        rightBat.draw(gc);
    }
}
