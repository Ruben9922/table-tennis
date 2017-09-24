package uk.co.rubendougall.tabletennis;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
    private Court court;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Court Tennis");

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
        leftBat = new Bat(gc.getCanvas(), batSpacingX);
        rightBat = new Bat(gc.getCanvas(), canvas.getWidth() - batSpacingX);
        ball = new Ball(gc.getCanvas());
        court = new Court();
    }

    private static boolean checkForCollision(Shape shape1, Shape shape2) {
        return Shape.intersect(shape1, shape2).getBoundsInLocal().getWidth() != -1;
    }

    private void render(GraphicsContext gc, double delta) {
        Canvas canvas = gc.getCanvas();

        // Draw background
        gc.setFill(Color.grayRgb(50));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Check if on bat
        if (checkForCollision(ball.getShape(), leftBat.getShape())
                || checkForCollision(ball.getShape(), rightBat.getShape())) {
            ball.changeXDirection();
        }

        leftBat.update(delta, input, KeyCode.W, KeyCode.S);
        rightBat.update(delta, input, KeyCode.UP, KeyCode.DOWN);
        ball.update(delta);

        court.render(gc);
    }
}
