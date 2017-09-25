package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Bat {
    private static final double BAT_LENGTH = 80;
    private static final double VELOCITY = 0.7;

    private Canvas canvas;
    private Point2D position;
    private Line shape = new Line();

    Bat(Canvas canvas, final double positionX) {
        this.canvas = canvas;

        position = new Point2D(positionX, (canvas.getHeight() - Bat.BAT_LENGTH) / 2);

        shape.setStroke(Color.grayRgb(255));
    }

    Line getShape() {
        return shape;
    }

    void update(double delta, Input input, KeyCode moveUpCode, KeyCode moveDownCode) {
        if (input.isKeyPressed(moveUpCode)) {
            position = position.subtract(0, VELOCITY * delta);
        } else if (input.isKeyPressed(moveDownCode)) {
            position = position.add(0, VELOCITY * delta);
        }

        // Clamp y-position
        final double batSpacing = 25;
        position = new Point2D(position.getX(), Utilities.constrain(position.getY(), batSpacing, canvas.getHeight() - batSpacing - BAT_LENGTH));

        shape.setStartX(position.getX());
        shape.setStartY(position.getY());
        shape.setEndX(position.getX());
        shape.setEndY(position.getY() + BAT_LENGTH);
    }
}
