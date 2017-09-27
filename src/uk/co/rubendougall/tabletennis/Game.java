package uk.co.rubendougall.tabletennis;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

class Game implements Screen {
    private Scene scene;
    private double lastTime = System.nanoTime();
    private Input input = new Input();
    private Bat leftBat;
    private Bat rightBat;
    private Ball ball;
    private Court court;
    private IntegerProperty leftScore = new SimpleIntegerProperty(0);
    private IntegerProperty rightScore = new SimpleIntegerProperty(0);

    Game() {
        Group root = new Group();
        scene = new Scene(root);
        Dimension2D dimensions = new Dimension2D(800, 500);

        final double batSpacingX = 30;
        leftBat = new Bat(dimensions, batSpacingX, input, KeyCode.W, KeyCode.S);
        rightBat = new Bat(dimensions, dimensions.getWidth() - batSpacingX, input, KeyCode.UP, KeyCode.DOWN);
        ball = new Ball(dimensions);
        court = new Court(dimensions);

        final Point2D textSpacing = new Point2D(10, 2);
        Text leftScoreLabel = new Text();
        leftScoreLabel.setFill(Color.grayRgb(255));
        leftScoreLabel.setFont(Font.getDefault());
        leftScoreLabel.setTextAlignment(TextAlignment.CENTER);
        leftScoreLabel.setTextOrigin(VPos.TOP);
        leftScoreLabel.textProperty().bind(leftScore.asString());
        Text rightScoreLabel = new Text();
        rightScoreLabel.setFill(Color.grayRgb(255));
        rightScoreLabel.setFont(Font.getDefault());
        rightScoreLabel.setTextAlignment(TextAlignment.CENTER);
        rightScoreLabel.setTextOrigin(VPos.TOP);
        rightScoreLabel.textProperty().bind(rightScore.asString());

        AnchorPane scoreAnchorPane = new AnchorPane(leftScoreLabel, rightScoreLabel);
        AnchorPane.setLeftAnchor(leftScoreLabel, textSpacing.getX());
        AnchorPane.setTopAnchor(leftScoreLabel, textSpacing.getY());
        AnchorPane.setRightAnchor(rightScoreLabel, textSpacing.getX());
        AnchorPane.setTopAnchor(rightScoreLabel, textSpacing.getY());
        scoreAnchorPane.prefWidthProperty().bind(scene.widthProperty());
        scoreAnchorPane.prefHeightProperty().bind(scene.heightProperty());

        Canvas canvas = new Canvas(dimensions.getWidth(), dimensions.getHeight());
        root.getChildren().addAll(canvas, leftBat.getShape(), rightBat.getShape(), ball.getShape(), court.getTopLine(),
                court.getBottomLine(), court.getLeftLine(), court.getRightLine(), court.getCentreLine(), scoreAnchorPane);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double delta = calculateDelta(currentTime);
                render(gc, delta);

            }
        }.start();

        scene.setOnKeyPressed(input::handleKeyPressed);
        scene.setOnKeyReleased(input::handleKeyReleased);
    }

    private static boolean checkForCollision(Shape shape1, Shape shape2) {
        return Shape.intersect(shape1, shape2).getBoundsInLocal().getWidth() != -1;
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    private double calculateDelta(long currentTime) {
        double delta = (currentTime - lastTime) / 1000000.0;
        lastTime = currentTime;
        return delta;
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

        // Check if on top or bottom edge
        if (checkForCollision(ball.getShape(), court.getTopLine())
                || checkForCollision(ball.getShape(), court.getBottomLine())) {
            ball.changeYDirection();
        }

        // Check for goal and increment relevant score
        if (checkForCollision(ball.getShape(), court.getLeftLine())) {
            ball.reset(false);
            rightScore.set(rightScore.getValue() + 1);
        }

        if (checkForCollision(ball.getShape(), court.getRightLine())) {
            ball.reset(true);
            leftScore.set(leftScore.getValue() + 1);
        }

        leftBat.update(delta);
        rightBat.update(delta);
        ball.update(delta);
    }
}
