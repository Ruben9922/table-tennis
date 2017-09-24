package uk.co.rubendougall.tabletennis;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

class Bat {
    private static final double BAT_LENGTH = 80;
    private static final double VELOCITY = 1.2;

    private GraphicsContext gc;
    private Point2D position;
    private Line shape = new Line();

    Bat(GraphicsContext gc, final double positionX) {
        this.gc = gc;

        Canvas canvas = gc.getCanvas();
        position = new Point2D(positionX, (canvas.getHeight() - Bat.BAT_LENGTH) / 2);

        shape.setStroke(Color.grayRgb(255));
    }

    Line getShape() {
        return shape;
    }

    void update(double delta, Input input, KeyCode moveUpCode, KeyCode moveDownCode) {
        Canvas canvas = gc.getCanvas();

        if (input.isKeyPressed(moveUpCode)) {
            position = position.subtract(0, VELOCITY * delta);
        } else if (input.isKeyPressed(moveDownCode)) {
            position = position.add(0, VELOCITY * delta);
        }

        // Clamp y-position
        double BAT_SPACING = 30;
        position = new Point2D(position.getX(), Utilities.constrain(position.getY(), BAT_SPACING, canvas.getHeight() - BAT_SPACING - BAT_LENGTH));

        shape.setStartX(position.getX());
        shape.setStartY(position.getY());
        shape.setEndX(position.getX());
        shape.setEndY(position.getY() + BAT_LENGTH);
    }
}
