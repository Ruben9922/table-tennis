package uk.co.rubendougall.tabletennis;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application {
    private double lastTime = System.nanoTime();
    private Input input = new Input();
    private Bat leftBat;
    private Bat rightBat;
    private Ball ball;

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
                render(gc, delta);

            }
        }.start();

        scene.setOnKeyPressed(input::handleKeyPressed);
        scene.setOnKeyReleased(input::handleKeyReleased);

        root.getChildren().addAll(canvas, leftBat.getShape(), rightBat.getShape(), ball.getShape());

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

        // TODO: Possibly remove setup method
        // Instantiate bats
        double batSpacingX = 30;
        leftBat = new Bat(gc, batSpacingX);
        rightBat = new Bat(gc, canvas.getWidth() - batSpacingX);
        ball = new Ball(gc);
    }

    private void render(GraphicsContext gc, double delta) {
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

        // Check if on bat
        if (Shape.intersect(ball.getShape(), leftBat.getShape()).getBoundsInLocal().getWidth() != -1
                || Shape.intersect(ball.getShape(), rightBat.getShape()).getBoundsInLocal().getWidth() != -1) {
            ball.changeXDirection();
        }

        leftBat.update(gc, delta, input, KeyCode.W, KeyCode.S);
        rightBat.update(gc, delta, input, KeyCode.UP, KeyCode.DOWN);
        ball.update(delta);
    }
}
