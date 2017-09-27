package uk.co.rubendougall.tabletennis;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Bat {
    private static final double BAT_LENGTH = 80;
    private static final double SPEED = 0.7;

    private Dimension2D dimensions;
    private Point2D position;
    private Line shape = new Line();

    Bat(Dimension2D dimensions, final double positionX) {
        this.dimensions = dimensions;

        position = new Point2D(positionX, (dimensions.getHeight() - Bat.BAT_LENGTH) / 2);

        shape.setStroke(Color.grayRgb(255));
    }

    Line getShape() {
        return shape;
    }

    void update(double delta, Input input, KeyCode moveUpCode, KeyCode moveDownCode) {
        if (input.isKeyPressed(moveUpCode)) {
            position = position.subtract(0, SPEED * delta);
        } else if (input.isKeyPressed(moveDownCode)) {
            position = position.add(0, SPEED * delta);
        }

        // Clamp y-position
        final double batSpacing = 25;
        position = new Point2D(position.getX(), Utilities.constrain(position.getY(), batSpacing, dimensions.getHeight() - batSpacing - BAT_LENGTH));

        shape.setStartX(position.getX());
        shape.setStartY(position.getY());
        shape.setEndX(position.getX());
        shape.setEndY(position.getY() + BAT_LENGTH);
    }
}
